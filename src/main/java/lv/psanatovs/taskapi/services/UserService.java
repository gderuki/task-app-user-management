package lv.psanatovs.taskapi.services;

import lv.psanatovs.taskapi.entities.UserEntity;
import lv.psanatovs.taskapi.exceptions.UserAlreadyExistException;
import lv.psanatovs.taskapi.exceptions.UserNotFoundException;
import lv.psanatovs.taskapi.models.UserModel;
import lv.psanatovs.taskapi.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public Long createUser(UserEntity user) {
        userRepo.findByUsername(user.getUsername())
                .ifPresent(existingUser -> {
                    throw new UserAlreadyExistException("User already exist!");
                });

        return userRepo.save(user).getId();
    }

    public List<UserModel> getAllUsers() {
        return StreamSupport.stream(userRepo.findAll().spliterator(), false)
                .map(UserModel::fromEntity)
                .collect(Collectors.toList());
    }

    public UserModel getUserById(Long id) throws UserNotFoundException {
        return userRepo.findById(id)
                .map(UserModel::fromEntity)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));
    }

    public void deleteUserById(Long id) {
        userRepo.findById(id)
                .ifPresentOrElse(
                        userRepo::delete,
                        () -> {
                            throw new UserNotFoundException("User not found!");
                        }
                );
    }
}
