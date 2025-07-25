package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.NewPassword;
import ru.skypro.homework.model.UpdateUser;
import ru.skypro.homework.model.dto.User;

public interface UserServices {
    ResponseEntity<?> passwordUpdates(NewPassword newPassword);
    ResponseEntity<User>getInformationAboutUser();
    ResponseEntity<UpdateUser>updatingUserInformation(UpdateUser updateUser);
    void updatingUsersAvatar(MultipartFile avatar);
}
