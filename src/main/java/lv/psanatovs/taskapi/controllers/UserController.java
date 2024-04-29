package lv.psanatovs.taskapi.controllers;

import lv.psanatovs.taskapi.entities.UserEntity;
import lv.psanatovs.taskapi.exceptions.UserAlreadyExistException;
import lv.psanatovs.taskapi.exceptions.UserNotFoundException;
import lv.psanatovs.taskapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserEntity user) {
        try {
            userService.createUser(user);

            return ResponseEntity.ok("User successfully created!");
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong!");
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        try {
            Iterable<UserEntity> users = userService.getAllUsers();
            users.forEach(System.out::println);
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Users: HTTP Error!");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            var user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("User: HTTP Error!");
        }
    }
}
