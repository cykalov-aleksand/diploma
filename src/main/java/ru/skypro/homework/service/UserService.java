package ru.skypro.homework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.mapper.UpdateUserMapper;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.AvatarUserModel;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.repository.AvatarUserRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.impl.AuthServiceImpl;


import java.io.*;
import java.nio.file.Path;
import java.util.Objects;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AuthServiceImpl authService;
    private final AvatarUserRepository avatarUserRepository;
    private final AvatarComponent avatarComponent;

    public UserService(UserRepository userRepository, AuthServiceImpl authService, AvatarUserRepository avatarUserRepository, AvatarComponent avatarComponent) {
        this.userRepository = userRepository;
        this.authService = authService;
        this.avatarUserRepository = avatarUserRepository;
        this.avatarComponent = avatarComponent;
    }
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UpdateUserMapper updateUserMapper;

     public ResponseEntity<Void> passwordUpdates(NewPassword newPassword) {
        UserModel user = userRepository.findIdPassword(newPassword.getCurrentPassword().trim());
        if (user == null) {
            return ResponseEntity.status(403).build();
        }
        userRepository.updatePassword(newPassword.getNewPassword(), Objects.requireNonNull(user).getId());
        return ResponseEntity.ok().build();
    }


    public User getInformationAboutUser() {
        UserModel usernameAuthorised = userRepository.informationAboutUser(authService.usernameAuthorised());
        return userMapper.toModel(usernameAuthorised);
    }


    public UpdateUser updatingUserInformation(UpdateUser updateUser) {
        userRepository.updatingUserInformationAuthorised(updateUser.getFirstName(), updateUser.getLastName(),
                updateUser.getPhone(), authService.usernameAuthorised());
        return updateUserMapper.toModel(userRepository.informationAboutUser(authService.usernameAuthorised()));
            }


    public ResponseEntity<Void> updatingUsersAvatar(MultipartFile image) throws IOException {
        if (image.getSize() >= 1024 * 300) {
            return ResponseEntity.status(403).build();
        }
        UserModel userModel = userRepository.informationAboutUser(authService.usernameAuthorised());
        Path filePath=avatarComponent.saveAvatar("avatar/user",userModel.getId().toString(),image);
       Integer avatarUserModel=avatarUserRepository.findUserAvatar(userModel.getId());
         if(avatarUserModel!=null){
             avatarUserRepository.deleteLine(userModel.getId());
        }
        AvatarUserModel avatar=new AvatarUserModel();
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(image.getSize());
        avatar.setMediaType(image.getContentType());
        avatar.setData(avatarComponent.generateImagePreview(filePath));
        avatarUserRepository.save(avatar.getFilePath(), avatar.getFileSize(), avatar.getMediaType(),
                avatar.getData(),userModel.getId());
        userRepository.updateImage(filePath.toString(),authService.usernameAuthorised());
        return ResponseEntity.status(200).build();
    }

}
