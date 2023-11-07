package pdr.chorumeblog.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import pdr.chorumeblog.dto.PostDto;
import pdr.chorumeblog.model.PostEntity;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);
    List<PostDto> toDtoList(List<PostEntity> list);
}
