package recipes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recipes.model.User;
import recipes.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Boolean checkEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
