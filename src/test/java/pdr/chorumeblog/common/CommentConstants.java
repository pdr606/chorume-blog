package pdr.chorumeblog.common;

import pdr.chorumeblog.dto.CommentDto;
import pdr.chorumeblog.model.CommentEntity;
import pdr.chorumeblog.model.utils.CreateAndUpdateEntity;

import java.time.LocalDateTime;
import static pdr.chorumeblog.common.UserConstants.*;
import static pdr.chorumeblog.common.PostConstants.*;
public class CommentConstants {

    public static final CommentEntity VALID_COMMENT_INPUT_ENTITY =
            CommentEntity.builder()
                    .content("Valid content")
                    .likes(0)
                    .dateTime(CreateAndUpdateEntity.builder()
                            .created(LocalDateTime.now())
                            .updated(LocalDateTime.now())
                            .build())
                    .user(VALID_INPUT_ENTITY)
                    .post(VALID_POST_ENTITY)
                    .build();

    public static final CommentDto VALID_COMMENT_INPUT_DTO =
            CommentDto.builder()
                    .content("Valid content")
                    .likes(0)
                    .dateTime(CreateAndUpdateEntity.builder()
                            .created(LocalDateTime.now())
                            .updated(LocalDateTime.now())
                            .build())
                    .user(VALID_INPUT_DTO)
                    .post(VALID_POST_DTO_ENTITY)
                    .build();
}
