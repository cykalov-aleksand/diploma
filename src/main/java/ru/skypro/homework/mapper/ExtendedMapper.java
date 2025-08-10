package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.model.UserModel;

@Component
@Mapper(componentModel = "spring")
public interface ExtendedMapper {
    ExtendedMapper INSTANCE = Mappers.getMapper(ExtendedMapper.class);
    UserModel toDto(ExtendedAd extendedAd );
    ExtendedAd toModel(UserModel userModel);
}


