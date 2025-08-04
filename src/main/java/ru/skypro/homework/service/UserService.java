package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.exception.ForbiddenException;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.impl.AuthServiceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserService{
    private final UserRepository userRepository;
    private final AuthServiceImpl authService;

    public UserService(UserRepository userRepository, AuthServiceImpl authService) {
        this.userRepository = userRepository;
        this.authService = authService;
    }

    public ResponseEntity<Void> passwordUpdates(NewPassword newPassword) {
        UserModel user=userRepository.findIdPassword(newPassword.getCurrentPassword().trim());
        if(user==null){
return ResponseEntity.status(403).build();
        }
userRepository.updatePassword(newPassword.getNewPassword(), Objects.requireNonNull(user).getId());
    return ResponseEntity.ok().build();
    }


    public User getInformationAboutUser() {
        UserModel usernameAuthorised =userRepository.InformationAboutUser(authService.usernameAuthorised);

        return new User(usernameAuthorised.getId(),usernameAuthorised.getEmail(), usernameAuthorised.getFirstName(),
                usernameAuthorised.getLastName(), usernameAuthorised.getPhone(), usernameAuthorised.getImage(),usernameAuthorised.getRole() );
    }


    public UpdateUser updatingUserInformation(UpdateUser updateUser) {
        return null;
    }


    public ResponseEntity<Void> updatingUsersAvatar(MultipartFile avatar)throws IOException {
return ResponseEntity.status(200).build();
    }
    }
