package pdr.chorumeblog.controller;

import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import pdr.chorumeblog.config.groupsValidation.CreateUserValidation;
import pdr.chorumeblog.config.groupsValidation.DeleteUserValidation;
import pdr.chorumeblog.config.groupsValidation.UpdateUserValidation;
import pdr.chorumeblog.dto.UserDto;
import pdr.chorumeblog.infra.security.token.TokenService;
import pdr.chorumeblog.mapper.user.UserMapper;
import pdr.chorumeblog.dto.ImgurImageResponseDto;
import pdr.chorumeblog.service.user.UserService;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final TokenService tokenService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Validated(value = CreateUserValidation.class) UserDto data) {
            userService.createUser(data);
    }

    @GetMapping(value = "/{nickName}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getByNickName(@PathVariable String nickName){
        return UserMapper.INSTANCE.toDto(userService.findUserByNickName(nickName));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UserDto update(@RequestBody @Validated(value = UpdateUserValidation.class) UserDto dto, Authentication authentication){
        String nickName = tokenService.getUserNickNameByToken(authentication);
        return userService.updateUser(dto, nickName);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void delete(Authentication authentication){
        String nickName = tokenService.getUserNickNameByToken(authentication);
        userService.deleteUser(nickName);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> randomUsers(){
        return userService.findRandomUsers();
    }


}
