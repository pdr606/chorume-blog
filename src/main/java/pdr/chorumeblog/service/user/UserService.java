package pdr.chorumeblog.service.user;


import pdr.chorumeblog.dto.UserDto;
import pdr.chorumeblog.model.UserEntity;

import java.util.UUID;

public interface UserService {

    void createUser(UserDto data);
    UserEntity findUserByNickName(String nickName);
}
