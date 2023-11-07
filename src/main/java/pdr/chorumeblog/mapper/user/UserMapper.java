package pdr.chorumeblog.mapper.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import pdr.chorumeblog.dto.CommentDto;
import pdr.chorumeblog.dto.UserDto;
import pdr.chorumeblog.model.CommentEntity;
import pdr.chorumeblog.model.UserEntity;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    UserDto toDto(UserEntity entity);
}
