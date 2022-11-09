package recipes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import recipes.model.User;
import recipes.repository.UserRepository;
import recipes.security.UserDetailsImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;


    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if(user == null) {
            throw new UsernameNotFoundException("Not found: " + email);
        }
        System.out.println("UserDetailsImpl for email: "+email);
        return new UserDetailsImpl(user);
    }

}
