package ru.skypro.homework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.mapper.NewPasswordMapper;
import ru.skypro.homework.mapper.UpdateUserMapper;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.AvatarUserModel;
import ru.skypro.homework.model.RegisterUserModel;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.repository.AvatarUserRepository;
import ru.skypro.homework.repository.RegisterUserRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.impl.AuthServiceImpl;


import java.io.*;
import java.nio.file.Path;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AuthServiceImpl authService;
    private final AvatarUserRepository avatarUserRepository;
    private final AvatarComponent avatarComponent;
    private final RegisterUserRepository registerUserRepository;

    public UserService(UserRepository userRepository, AuthServiceImpl authService,
                       AvatarUserRepository avatarUserRepository, AvatarComponent avatarComponent,
                       RegisterUserRepository registerUserRepository) {
        this.userRepository = userRepository;
        this.authService = authService;
        this.avatarUserRepository = avatarUserRepository;
        this.avatarComponent = avatarComponent;
        this.registerUserRepository = registerUserRepository;
    }

    @Autowired
    private UserMapper userMapper;
   @Autowired
   private UpdateUserMapper updateUserMapper;
    @Autowired
    private NewPasswordMapper newPasswordMapper;


     public ResponseEntity<Void> passwordUpdates(NewPassword newPassword) {
         RegisterUserModel reversNewPasswordMapper=newPasswordMapper.toDto(newPassword);
          RegisterUserModel registerUser = registerUserRepository.findIdPassword(reversNewPasswordMapper.getPassword().trim());
          if (registerUser == null) {
            return ResponseEntity.status(403).build();
        }
        registerUserRepository.updatePassword(reversNewPasswordMapper.getNewPassword(), authService.usernameAuthorised());
        return ResponseEntity.ok().build();
    }


    public User getInformationAboutUser() {
        RegisterUserModel userNameAuthorised = registerUserRepository.informationAboutUser(authService.usernameAuthorised());
        System.err.println(userNameAuthorised);
        UserModel userNameInformation=userRepository.informationAboutIdRegister(userNameAuthorised.getId());
        System.err.println(userNameInformation);
        System.out.println(userNameAuthorised);
        return userMapper.toModel(userNameAuthorised,userNameInformation);
    }


    public UpdateUser updatingUserInformation(UpdateUser updateUser) {
UserModel reverseUpdateUser= updateUserMapper.toDto(updateUser);
        System.err.println(registerUserRepository.informationAboutUser(authService.usernameAuthorised()).getId());
        userRepository.updatingUserInformationAuthorised(reverseUpdateUser.getFirstName(), reverseUpdateUser.getLastName(),
                reverseUpdateUser.getPhone(), registerUserRepository.informationAboutUser(authService.usernameAuthorised()).getId());
        UpdateUser ss= updateUserMapper.toModel(userRepository.informationAboutUser(registerUserRepository.informationAboutUser(authService.usernameAuthorised()).getId()));
        System.err.println(ss);
            return ss;
     }


    public ResponseEntity<Void> updatingUsersAvatar(MultipartFile image) throws IOException {
        if (image.getSize() >= 1024 * 300) {
            return ResponseEntity.status(403).build();
        }
        UserModel userModel = userRepository.informationAboutUser(registerUserRepository.informationAboutUser(authService.usernameAuthorised()).getId());
        Path filePath=avatarComponent.saveAvatar("avatar/user",userModel.getPpk().toString(),image);
       Integer avatarUserModel=avatarUserRepository.findUserAvatar(userModel.getPpk());
         if(avatarUserModel!=null){
             avatarUserRepository.deleteLine(userModel.getPpk());
        }
        AvatarUserModel avatar=new AvatarUserModel();
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(image.getSize());
        avatar.setMediaType(image.getContentType());
        avatar.setData(avatarComponent.generateImagePreview(filePath));
        avatarUserRepository.save(avatar.getFilePath(), avatar.getFileSize(), avatar.getMediaType(),
                avatar.getData(),userModel.getPpk());
        userRepository.updateImage(filePath.toString(),registerUserRepository.informationAboutUser(authService.usernameAuthorised()).getId());
        return ResponseEntity.status(200).build();
    }

}


