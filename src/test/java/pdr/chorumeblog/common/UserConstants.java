package pdr.chorumeblog.common;

import org.springframework.security.core.userdetails.UserDetails;
import pdr.chorumeblog.dto.UserDto;
import pdr.chorumeblog.model.UserEntity;
import pdr.chorumeblog.model.utils.CreateAndUpdateEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserConstants {

    public static final UserDto VALID_INPUT_DTO =
            UserDto.builder()
                    .nickName("Valid nickName")
                    .email("Valid email")
                    .password("Valid password")
                    .profilePhoto("Valid profile picture")
                    .build();

    public static final UserDto VALID_INPUT_DTO_UPDATE =
            UserDto.builder()
                    .nickName("Valid nickName update")
                    .email("Valid email update")
                    .password("Valid password update")
                    .profilePhoto("Valid profile picture update")
                    .build();


    public static final UserEntity VALID_INPUT_ENTITY =
            UserEntity.builder()
                    .nickName("Valid nickName")
                    .email("Valid email")
                    .password("Valid password")
                    .profilePhoto("Valid profile picture")
                    .dateTime(CreateAndUpdateEntity.builder()
                            .created(LocalDateTime.now())
                            .updated(LocalDateTime.now())
                            .build())
                    .build();

    public static final UserEntity VALID_OUTPUT_ENTITY =
            UserEntity.builder()
                    .nickName("Valid nickName")
                    .email("Valid email")
                    .password("Valid password")
                    .profilePhoto("Valid profile picture")
                    .dateTime(CreateAndUpdateEntity.builder()
                            .created(LocalDateTime.now())
                            .updated(LocalDateTime.now())
                            .build())
                    .build();

    public static final UserDetails VALID_USER_DETAILS =
            UserEntity.builder()
                    .nickName("Valid nickName")
                    .email("Valid email")
                    .password("Valid password")
                    .profilePhoto("Valid profile picture")
                    .dateTime(CreateAndUpdateEntity.builder()
                            .created(LocalDateTime.now())
                            .updated(LocalDateTime.now())
                            .build())
                    .build();


    public static final String URL_PHOTO_UPDATE = "http://updateprofile";
}
