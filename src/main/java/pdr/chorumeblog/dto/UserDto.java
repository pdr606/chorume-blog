package pdr.chorumeblog.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import pdr.chorumeblog.config.groupsValidation.CreateUserValidation;
import pdr.chorumeblog.model.CommentEntity;
import pdr.chorumeblog.model.PostEntity;
import pdr.chorumeblog.model.UserEntity;
import pdr.chorumeblog.model.utils.CreateAndUpdateEntity;

import java.util.List;
import java.util.UUID;

@Builder
public record UserDto(

        @JsonInclude(JsonInclude.Include.NON_NULL)
        UUID id,
        @NotEmpty(message = "NickName is required", groups = CreateUserValidation.class)
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String nickName,

        @NotEmpty(message = "E-mail is required", groups = CreateUserValidation.class)
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String email,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        String profilePhoto,

        @NotEmpty(message = "Password is required", groups = CreateUserValidation.class)
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String password,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        String linkedin,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        String github,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        Integer likes,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        CreateAndUpdateEntity dateTime

)
{
    public static UserEntity toEntity(UserDto dto){
        return UserEntity.builder()
                .nickName(dto.nickName())
                .email(dto.email())
                .profilePhoto(dto.profilePhoto())
                .password(dto.password())
                .linkedin(dto.linkedin())
                .github(dto.github())
                .likes(dto.likes())
                .build();
    }

    public static UserDto toDto(UserEntity data){
        return UserDto.builder()
                .id(data.getId())
                .nickName(data.getNickName())
                .email(data.getEmail())
                .profilePhoto(data.getProfilePhoto())
                .linkedin(data.getLinkedin())
                .github(data.getGithub())
                .likes(data.getLikes())
                .dateTime(CreateAndUpdateEntity.builder()
                        .created(data.getDateTime().getCreated())
                        .updated(data.getDateTime().getUpdated())
                        .build())
                .build();
    }
}
