package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.model.UserModel;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    //@Mapping(target = "userName", ignore = true)
    //@Mapping(target = "password", ignore = true)
    UserModel toDto(User user );
    //@Mapping(target = "userName", ignore = true)
    //@Mapping(target = "password", ignore = true)
    User toModel(UserModel userModel);
}
