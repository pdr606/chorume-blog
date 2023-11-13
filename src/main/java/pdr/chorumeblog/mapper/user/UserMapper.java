package pdr.chorumeblog.mapper.user;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import pdr.chorumeblog.dto.CommentDto;
import pdr.chorumeblog.dto.PostDto;
import pdr.chorumeblog.dto.UserDto;
import pdr.chorumeblog.model.CommentEntity;
import pdr.chorumeblog.model.PostEntity;
import pdr.chorumeblog.model.UserEntity;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    UserDto toDto(UserEntity entity);

    default List<UserDto> toDtoList(List<UserEntity> list){
        return list.stream()
                .map(UserDto::toDto)
                .toList();
    }

    void updateUserFromDto(UserDto dto, @MappingTarget UserEntity userEntity);
}
