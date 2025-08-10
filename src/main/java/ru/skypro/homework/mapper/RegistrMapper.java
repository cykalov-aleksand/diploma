package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.model.UserModel;

@Component
@Mapper
public interface RegistrMapper {
         RegistrMapper INSTANCE = Mappers.getMapper(RegistrMapper.class);
       // @Mapping(target = "id", ignore = true)
        //@Mapping(target = "email", ignore = true)
        UserModel toDto(Register register );
        Register toModel(UserModel userModel);
}
