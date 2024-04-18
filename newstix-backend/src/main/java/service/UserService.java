package service;

import models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(User user) {
        //TODO
        return userRepository.save(user);
    }

    public User login(String username, String password) {
        //TODO
        return userRepository.find(username, password);
    }

    public User getUserById(Long userId) {
        //TODO
        return userRepository.findById(userId).orElse(null);
    }

    public User saveUser(User user) {
        //TODO
        return userRepository.save(user);
    }
}
