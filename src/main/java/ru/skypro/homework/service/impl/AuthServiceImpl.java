package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserDetailsManager manager;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    public AuthServiceImpl(UserDetailsManager manager, PasswordEncoder encoder, UserRepository userRepository) {
        this.manager = manager;
        this.encoder = encoder;
        this.userRepository = userRepository;
    }

    @Override
    public boolean login(String userName, String password) {
        if (!manager.userExists(userName)) {
            return false;
        }
if(userRepository.availabilityInDatabase(userName,password)==0){
    userRepository.saveUserPassword(userName,password);
}
        UserDetails userDetails = manager.loadUserByUsername(userName);
        usernameAuthorised=userName;
        return encoder.matches(password, userDetails.getPassword());
    }

    @Override
    public boolean register(Register register) {
              if (manager.userExists(register.getUsername())) {
            return false;
        }
        manager.createUser(
                User.builder()
                        .passwordEncoder(this.encoder::encode)
                        .password(register.getPassword())
                        .username(register.getUsername())
                        .roles(register.getRole().name())
                        .build());
        System.out.println(register.getRole().name());
        userRepository.saveRule(register.getPassword(),register.getFirstName(), register.getLastName(), register.getPhone(), register.getRole().name());
               return true;
    }
public String usernameAuthorised;

}
