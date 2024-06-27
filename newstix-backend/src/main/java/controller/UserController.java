package controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import models.user.User;
import models.user.UserDTO;
import models.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User newUser = new User(user.getUsername(), user.getPassword());
        User registeredUser = userService.register(newUser);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        User loggedInUser = userService.login(user.getUsername(), user.getPassword());
        if (loggedInUser != null) {
            String token = Jwts.builder()
                    .setSubject(user.getUsername())
                    .signWith(SignatureAlgorithm.HS512, jwtSecret)
                    .compact();

            UserDTO userResponse = new UserDTO(loggedInUser.getId(), token);

            return ResponseEntity.ok(userResponse);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestBody Map<String, Long> payload) {
        Long id = payload.get("id");
        User loggedInUser = userService.getUserById(id);
        if (loggedInUser != null) {
            userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.OK).body("User deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
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

