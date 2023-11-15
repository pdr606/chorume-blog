package pdr.chorumeblog.service.authorizationService;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import pdr.chorumeblog.dto.TokenDto;
import pdr.chorumeblog.dto.UserDto;

public interface AuthorizationService {

    TokenDto generateTokenDto(UserDto dto);
    String encryptedPassword(String password);
    UsernamePasswordAuthenticationToken generateUsernamePassword(String nickName, String password);
}
