package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.model.RegisterUserModel;
import ru.skypro.homework.model.UserModel;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Mapping(target = "userName", source = "email")
    RegisterUserModel toDto(User user );
    UserModel toDo(User user);
    @Mapping(target = "email", source = "registerUserModel.userName")
    @Mapping(target="id",source = "registerUserModel.id")
    User toModel(RegisterUserModel registerUserModel,UserModel userModel);
}
