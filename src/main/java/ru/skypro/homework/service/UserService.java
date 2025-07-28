package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.NewPassword;
import ru.skypro.homework.model.UpdateUser;
import ru.skypro.homework.model.dto.User;

@Service
public class UserService{

    public String passwordUpdates(NewPassword newPassword) {
        return "";
    }


    public User getInformationAboutUser() {
        return null;
    }


    public UpdateUser updatingUserInformation(UpdateUser updateUser) {
        return null;
    }


    public void updatingUsersAvatar(MultipartFile avatar) {

    }
}
