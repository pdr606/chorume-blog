package pdr.chorumeblog.service.authorizationService;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pdr.chorumeblog.service.user.UserService;

@AllArgsConstructor
@Service
public class AuthorizationService implements UserDetailsService {

    private final UserService userService;
    @Override
    public UserDetails loadUserByUsername(String nickName) throws UsernameNotFoundException {
        return userService.findUserDetailsByNickName(nickName);
    }

    public static String encryptedPassword(String password){
        return new BCryptPasswordEncoder().encode(password);
    }

}
