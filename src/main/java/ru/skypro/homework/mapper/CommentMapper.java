package ru.skypro.homework.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.model.AdModel;
import ru.skypro.homework.model.CommentModel;

@Component
@Mapper(componentModel = "spring",uses = UserMapper.class)
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);
   // @Mapping(target = "pk", ignore = true)
    CommentModel toDto(Comment comment);
    Comment toModel(CommentModel commentModel);

    Ad toModel(AdModel variable);
}
