package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.model.UserModel;

@Mapper(componentModel = "spring")
@Component
public interface UpdateUserMapper {
    UpdateUserMapper INSTANCE = Mappers.getMapper(UpdateUserMapper.class);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userName", ignore = true)
   @Mapping(target = "password", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "role", ignore = true)
    UserModel toDto(UpdateUser updateUser );
    UpdateUser toModel(UserModel userModel);
}
