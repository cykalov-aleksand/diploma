package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.repository.UserRepository;

import java.io.IOException;

@Service
public class UserService{
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String passwordUpdates(NewPassword newPassword) {
        return "";
    }


    public User getInformationAboutUser() {
        return null;
    }


    public UpdateUser updatingUserInformation(UpdateUser updateUser) {
        return null;
    }


    public ResponseEntity<Void> updatingUsersAvatar(MultipartFile avatar)throws IOException {
return ResponseEntity.status(200).build();
    }
}
