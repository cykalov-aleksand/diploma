package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.mapper.NewPasswordMapper;
import ru.skypro.homework.mapper.RegisterMapper;
import ru.skypro.homework.model.RegisterUserModel;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.repository.RegisterUserRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserDetailsManager manager;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final RegisterUserRepository registerUserRepository;

    public AuthServiceImpl(UserDetailsManager manager, PasswordEncoder encoder, UserRepository userRepository, RegisterUserRepository registerUserRepository) {
        this.manager = manager;
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.registerUserRepository = registerUserRepository;
    }

    @Autowired
    RegisterMapper registerMapper;
    @Autowired
    NewPasswordMapper newPasswordMapper;

    @Override
    public boolean login(String userName, String password) {
        if (!manager.userExists(userName)) {
            return false;
        }
        if (registerUserRepository.availabilityInDatabase(userName, password) == 0) {
            userRepository.saveUserPassword(userName, password);
        }
        UserDetails userDetails = manager.loadUserByUsername(userName);
        //usernameAuthorised=userName;
        return encoder.matches(password, userDetails.getPassword());
    }

    @Override
    public boolean register(Register register) {
        RegisterUserModel reversRegister = registerMapper.registerToDto(register);
        System.err.println(reversRegister);
        if (manager.userExists(reversRegister.getUserName())) {
            return false;
        }
        manager.createUser(
                User.builder()
                        .passwordEncoder(this.encoder::encode)
                        .password(reversRegister.getPassword())
                        .username(reversRegister.getUserName())
                        .roles(reversRegister.getRole().name())
                        .build());
        registerUserRepository.saveRegister(reversRegister.getRole(), reversRegister.getPassword(), reversRegister.getUserName());
        int id = registerUserRepository.informationIdToParameter(reversRegister.getUserName(), reversRegister.getPassword());
        userRepository.saveRule(id, register.getFirstName(),
                register.getLastName(), register.getPhone());
        return true;
    }

    public String usernameAuthorised() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            System.err.println(userDetails.getUsername().toString());
            return userDetails.getUsername();
        }
        return null;
    }

}
