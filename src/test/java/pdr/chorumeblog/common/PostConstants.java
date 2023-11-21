package pdr.chorumeblog.common;

import pdr.chorumeblog.dto.PostDto;
import pdr.chorumeblog.model.PostEntity;
import pdr.chorumeblog.model.UserEntity;
import pdr.chorumeblog.model.utils.CreateAndUpdateEntity;

import java.time.LocalDateTime;

import static pdr.chorumeblog.common.UserConstants.*;
public class PostConstants {

    public static final PostEntity VALID_POST_ENTITY =
            PostEntity.builder()
                    .title("Valid title")
                    .content("Valid content")
                    .user(VALID_INPUT_ENTITY)
                    .likes(0)
                    .postPicture("Valid post picture")
                    .dateTime(CreateAndUpdateEntity.builder()
                            .updated(LocalDateTime.now())
                            .created(LocalDateTime.now())
                            .build())
                    .build();

    public static final PostDto VALID_POST_DTO_ENTITY =
            PostDto.builder()
                    .title("Valid title")
                    .content("Valid content")
                    .postPicture("Valid post picture")
                    .user(VALID_INPUT_DTO)
                    .likes(0)
                    .dateTime(CreateAndUpdateEntity.builder()
                            .updated(LocalDateTime.now())
                            .created(LocalDateTime.now())
                            .build())
                    .build();
}
