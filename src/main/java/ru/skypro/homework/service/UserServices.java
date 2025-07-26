package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.NewPassword;
import ru.skypro.homework.model.UpdateUser;
import ru.skypro.homework.model.dto.User;

public interface UserServices {
    String passwordUpdates(NewPassword newPassword);
    User getInformationAboutUser();
    UpdateUser updatingUserInformation(UpdateUser updateUser);
    void updatingUsersAvatar(MultipartFile avatar);
}
