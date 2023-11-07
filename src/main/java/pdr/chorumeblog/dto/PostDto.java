package pdr.chorumeblog.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import pdr.chorumeblog.config.groupsValidation.CreatePostValidation;
import pdr.chorumeblog.config.groupsValidation.CreateUserValidation;
import pdr.chorumeblog.model.PostEntity;
import pdr.chorumeblog.model.UserEntity;
import pdr.chorumeblog.model.utils.CreateAndUpdateEntity;

import java.util.UUID;

@Builder
public record PostDto(

        @JsonInclude(JsonInclude.Include.NON_NULL)
        Long id,
        @NotEmpty(message = "Title is required", groups = CreatePostValidation.class)
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String title,

        @NotEmpty(message = "E-mail is required", groups = CreateUserValidation.class)
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String content,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        Integer likes,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        CreateAndUpdateEntity dateTime,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        UserDto user

)
{
    public static PostEntity toEntity(PostDto dto){
        return PostEntity.builder()
                .title(dto.title())
                .content(dto.content())
                .likes(dto.likes())
                .build();
    }

    public static PostDto toDto(PostEntity data){
        return PostDto.builder()
                .id(data.getId())
                .title(data.getTitle())
                .content(data.getContent())
                .likes(data.getLikes())
                .dateTime(CreateAndUpdateEntity.builder()
                        .created(data.getDateTime().getCreated())
                        .updated(data.getDateTime().getUpdated())
                        .build())
                .user(UserDto.builder()
                        .nickName(data.getUser().getNickName())
                        .build())
                .build();
    }
}
