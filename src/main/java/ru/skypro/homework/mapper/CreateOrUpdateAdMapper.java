package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.model.AdModel;

@Component
@Mapper
public interface CreateOrUpdateAdMapper {
    CreateOrUpdateAdMapper INSTANCE = Mappers.getMapper(CreateOrUpdateAdMapper.class);
  //  @Mapping(target = "pk", ignore = true)
  //  @Mapping(target = "createAt", ignore = true)
    AdModel toDto(CreateOrUpdateAd createOrUpdateAd );
    CreateOrUpdateAd toModel(AdModel adModel);
}


