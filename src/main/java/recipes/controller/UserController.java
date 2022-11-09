package recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import recipes.model.User;
import recipes.service.UserService;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;


    @PostMapping("/api/register")
    public ResponseEntity register(@Validated @RequestBody User user) {
        if(userService.checkEmail(user.getEmail())) {
            System.out.println("Email already used: "+user.getEmail());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
            System.out.println("Saving new user: "+user.getEmail());
            System.out.println("User password: "+user.getPassword());
            user.setRole("ROLE_USER");
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.save(user);
            return new ResponseEntity(HttpStatus.OK);
        }

    }
}
