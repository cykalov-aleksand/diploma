package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Login;
import ru.skypro.homework.model.RegisterUserModel;

@Component
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LoginMapper {
    LoginMapper INSTANCE = Mappers.getMapper(LoginMapper.class);
    @Mapping(target = "userName", source = "username")
    RegisterUserModel toDto(Login login );
    @Mapping(target = "username", source = "userName")
    Login toModel(RegisterUserModel userModel);
}


