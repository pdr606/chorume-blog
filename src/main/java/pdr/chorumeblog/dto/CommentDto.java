package pdr.chorumeblog.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import pdr.chorumeblog.config.groupsValidation.CreateCommentValidation;
import pdr.chorumeblog.config.groupsValidation.CreateUserValidation;
import pdr.chorumeblog.model.CommentEntity;
import pdr.chorumeblog.model.UserEntity;
import pdr.chorumeblog.model.utils.CreateAndUpdateEntity;

import java.util.UUID;

@Builder
public record CommentDto(

        @JsonInclude(JsonInclude.Include.NON_NULL)
        Long id,
        @NotEmpty(message = "Content is required", groups = CreateCommentValidation.class)
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String content,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        Integer likes,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        CreateAndUpdateEntity dateTime,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        UserDto user,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        PostDto post

)
{
    public static CommentEntity toEntity(CommentDto dto){
        return CommentEntity.builder()
                .content(dto.content())
                .likes(dto.likes())
                .build();
    }

    public static CommentDto toDto(CommentEntity data){
        return CommentDto.builder()
                .id(data.getId())
                .content(data.getContent())
                .likes(data.getLikes())
                .dateTime(CreateAndUpdateEntity.builder()
                        .created(data.getDateTime().getCreated())
                        .updated(data.getDateTime().getUpdated())
                        .build())
                .user(UserDto
                        .builder()
                        .nickName(data.getUser().getNickName())
                        .profilePhoto(data.getUser().getProfilePhoto())
                        .likes(data.getLikes())
                        .build())
                .build();
    }
}
