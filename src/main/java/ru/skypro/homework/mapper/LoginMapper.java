package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Login;
import ru.skypro.homework.model.UserModel;

@Component
@Mapper
public interface LoginMapper {
    LoginMapper INSTANCE = Mappers.getMapper(LoginMapper.class);
   // @Mapping(target = "id", ignore = true)
   // @Mapping(target = "email", ignore = true)
   // @Mapping(target = "firstName", ignore = true)
   // @Mapping(target = "lastName", ignore = true)
   // @Mapping(target = "phone", ignore = true)
   // @Mapping(target = "image", ignore = true)
   // @Mapping(target = "role", ignore = true)
    UserModel toDto(Login login );
    Login toModel(UserModel userModel);
}


