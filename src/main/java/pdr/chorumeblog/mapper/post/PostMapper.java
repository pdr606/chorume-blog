package pdr.chorumeblog.mapper.post;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import pdr.chorumeblog.dto.PostDto;
import pdr.chorumeblog.model.PostEntity;
import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);
    default List<PostDto> toDtoList(List<PostEntity> list){
        return list.stream()
                .map(PostDto::toDto)
                .toList();
    }
}
