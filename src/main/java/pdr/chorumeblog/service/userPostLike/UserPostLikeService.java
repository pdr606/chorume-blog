package pdr.chorumeblog.service.userPostLike;


import pdr.chorumeblog.dto.UserDto;
import pdr.chorumeblog.model.UserEntity;
import pdr.chorumeblog.model.UserPostLikeEntity;

public interface UserPostLikeService {
    void saveLike(UserPostLikeEntity entity);
}
