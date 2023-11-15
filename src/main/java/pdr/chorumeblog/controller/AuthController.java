package pdr.chorumeblog.controller;


import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pdr.chorumeblog.config.groupsValidation.CreateUserValidation;
import pdr.chorumeblog.config.groupsValidation.LoginUserValidation;
import pdr.chorumeblog.dto.TokenDto;
import pdr.chorumeblog.dto.UserDto;
import pdr.chorumeblog.service.authorizationService.AuthorizationService;
import pdr.chorumeblog.service.user.UserService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private UserService userService;
    private AuthorizationService authorizationService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody @Validated(value = CreateUserValidation.class) UserDto data) {
        userService.createUser(data);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public TokenDto login(@RequestBody @Validated(value = LoginUserValidation.class) UserDto data){
        return authorizationService.generateTokenDto(data);
    }
}
