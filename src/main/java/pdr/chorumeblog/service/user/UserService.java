package pdr.chorumeblog.service.user;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import pdr.chorumeblog.dto.UserDto;
import pdr.chorumeblog.model.UserEntity;

import java.util.List;
import java.util.UUID;

public interface UserService {
    void createUser(UserDto data);
    void deleteUser(Authentication authentication);
    UserEntity findUserByNickName(String nickName);
    UserDto updateUser(Authentication authentication, UserDto data);
    void updateProfilePicture(String urlPhoto, String nickName);
    List<UserDto> findRandomUsers();
    UserDetails findUserDetailsByNickName(String nickName);
}
