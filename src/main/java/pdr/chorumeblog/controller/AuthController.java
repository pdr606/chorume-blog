package pdr.chorumeblog.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pdr.chorumeblog.config.groupsValidation.CreateUserValidation;
import pdr.chorumeblog.config.groupsValidation.LoginUserValidation;
import pdr.chorumeblog.dto.TokenDto;
import pdr.chorumeblog.dto.UserDto;
import pdr.chorumeblog.infra.security.token.TokenService;
import pdr.chorumeblog.model.UserEntity;
import pdr.chorumeblog.service.authorizationService.AuthorizationService;
import pdr.chorumeblog.service.user.UserService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private UserService userService;
    private TokenService tokenService;
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody @Validated(value = CreateUserValidation.class) UserDto data) {
        userService.createUser(data);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public TokenDto login(@RequestBody @Validated(value = LoginUserValidation.class) UserDto data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        return new TokenDto(tokenService.generateToken((UserEntity) auth.getPrincipal()));
    }
}
