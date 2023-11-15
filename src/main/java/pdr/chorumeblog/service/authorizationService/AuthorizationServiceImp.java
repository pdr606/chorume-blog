package pdr.chorumeblog.service.authorizationService;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pdr.chorumeblog.dto.TokenDto;
import pdr.chorumeblog.dto.UserDto;
import pdr.chorumeblog.infra.security.token.TokenService;
import pdr.chorumeblog.model.UserEntity;
import pdr.chorumeblog.service.user.UserService;

@AllArgsConstructor
@Service
public class AuthorizationServiceImp implements UserDetailsService, AuthorizationService {

    private final UserService userService;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;


    @Override
    public UserDetails loadUserByUsername(String nickName) throws UsernameNotFoundException {
        return userService.findUserDetailsByNickName(nickName);
    }

    @Override
    public TokenDto generateTokenDto(UserDto dto) {
        var userNamePassword = generateUsernamePassword(dto.nickName(), dto.password());
        var auth = authenticationManager.authenticate(userNamePassword);
        return new TokenDto(tokenService.generateToken((UserEntity) auth.getPrincipal()));
    }

    @Override
    public String encryptedPassword(String password){
        return new BCryptPasswordEncoder().encode(password);
    }

    @Override
    public UsernamePasswordAuthenticationToken generateUsernamePassword(String nickName, String password){
        return new UsernamePasswordAuthenticationToken(nickName, password);
    }
}
