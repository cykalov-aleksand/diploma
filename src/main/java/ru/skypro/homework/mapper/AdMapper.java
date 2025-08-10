package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.model.AdModel;

@Component
@Mapper
public interface AdMapper {
    AdModel toDto(Ad ad );
    Ad toModel(AdModel adModel);
}


