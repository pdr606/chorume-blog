package pdr.chorumeblog.mapper.comment;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import pdr.chorumeblog.dto.CommentDto;
import pdr.chorumeblog.dto.PostDto;
import pdr.chorumeblog.model.CommentEntity;
import pdr.chorumeblog.model.PostEntity;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);
    default List<CommentDto> toDtoList(List<CommentEntity> list){
        return list.stream()
                .map(CommentDto::toDto)
                .toList();
    }
}
