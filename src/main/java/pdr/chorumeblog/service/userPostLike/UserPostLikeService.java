package pdr.chorumeblog.service.userPostLike;


import pdr.chorumeblog.dto.UserDto;
import pdr.chorumeblog.model.UserEntity;
import pdr.chorumeblog.model.UserPostLikeEntity;

import java.util.UUID;

public interface UserPostLikeService {
    boolean saveLike(UserPostLikeEntity entity);
}
