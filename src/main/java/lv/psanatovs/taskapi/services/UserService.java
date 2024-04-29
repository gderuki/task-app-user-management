package lv.psanatovs.taskapi.services;

import lv.psanatovs.taskapi.entities.UserEntity;
import lv.psanatovs.taskapi.exceptions.UserAlreadyExistException;
import lv.psanatovs.taskapi.exceptions.UserNotFoundException;
import lv.psanatovs.taskapi.models.UserModel;
import lv.psanatovs.taskapi.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public UserEntity createUser(UserEntity user) throws UserAlreadyExistException {
        Optional<UserEntity> existingUser = Optional.ofNullable(userRepo.findByUsername(user.getUsername()));

        if (existingUser.isPresent()) {
            throw new UserAlreadyExistException("User already exist!");
        }

        return userRepo.save(user);
    }

    public Iterable<UserEntity> getAllUsers() {
        return userRepo.findAll();
    }

    public UserModel getUserById(Long id) throws UserNotFoundException {
        return userRepo.findById(id)
                .map(UserModel::fromEntity)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));
    }
}
