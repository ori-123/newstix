package controller;

import models.user.User;
import models.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User registeredUser = userService.register(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User user) {
        User loggedInUser = userService.login(user.getUsername(), user.getPassword());
        if (loggedInUser != null) {
            return new ResponseEntity<>(loggedInUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{userId}/preferences")
    public ResponseEntity<User> updateUserPreferences(@PathVariable Long userId, @RequestBody User updatedUser) {
        User user = userService.getUserById(userId);
        if (user != null) {
            user.setLanguage(updatedUser.getLanguage());
            user.setCountry(updatedUser.getCountry());
            user.setCategory(updatedUser.getCategory());
            user.setTimeframe(updatedUser.getTimeframe());

            User savedUser = userService.saveUser(user);
            return new ResponseEntity<>(savedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{userId}/addRole")
    public ResponseEntity<User> addRoleToUser(@PathVariable Long userId, @RequestParam UserRole role) {
        userService.addRoleFor(userId, role);
        User updatedUser = userService.getUserById(userId);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
}

