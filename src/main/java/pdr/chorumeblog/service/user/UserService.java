package pdr.chorumeblog.service.user;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import pdr.chorumeblog.dto.UserDto;
import pdr.chorumeblog.model.UserEntity;

import java.util.List;
import java.util.UUID;

public interface UserService {
    void createUser(UserDto data);
    void deleteUser(String nickName);
    UserEntity findUserByNickName(String nickName);
    UserDto updateUser(UserDto dto, String nickName);
    void updateProfilePicture(String urlPhoto, String nickName);
    List<UserDto> findRandomUsers();
    UserDetails findUserDetailsByNickName(String nickName);
}
