package lv.psanatovs.taskapi.controllers;

import lv.psanatovs.taskapi.entities.UserEntity;
import lv.psanatovs.taskapi.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepo userRepo;


    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserEntity user) {
        try {
            if (userRepo.findByUsername(user.getUsername()) != null) {
                // don't tell what exactly went wrong for now
                return ResponseEntity.badRequest().body("Something went wrong!");
            }

            userRepo.save(user);
            return ResponseEntity.ok("User successfully created!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong!");
        }
    }

    @GetMapping
    public ResponseEntity<?> getUsers() {
        try {
            return ResponseEntity.ok("Users: HTTP OK");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Users: HTTP Error!");
        }
    }
}
