package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.model.CommentModel;

@Component
@Mapper
public interface CreateOrUpdateCommentMapper {
    CreateOrUpdateCommentMapper INSTANCE = Mappers.getMapper(CreateOrUpdateCommentMapper.class);
  //      @Mapping(target = "pk", ignore = true)
  //  @Mapping(target = "createAt", ignore = true)
    CommentModel toDto(CreateOrUpdateComment createOrUpdateComment );
    CreateOrUpdateComment toModel(CommentModel commentModel);
}




