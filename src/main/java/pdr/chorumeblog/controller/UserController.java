package pdr.chorumeblog.controller;

import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pdr.chorumeblog.config.groupsValidation.CreateUserValidation;
import pdr.chorumeblog.config.groupsValidation.DeleteUserValidation;
import pdr.chorumeblog.config.groupsValidation.UpdateUserValidation;
import pdr.chorumeblog.dto.UserDto;
import pdr.chorumeblog.mapper.user.UserMapper;
import pdr.chorumeblog.service.user.UserService;



@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Validated(value = CreateUserValidation.class) UserDto data){
        userService.createUser(data);
    }

    @GetMapping(value = "/{nickName}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getById(@PathVariable String nickName){
        return UserMapper.INSTANCE.toDto(userService.findUserByNickName(nickName));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UserDto update(@RequestBody @Validated(value = UpdateUserValidation.class) UserDto dto){
        return userService.updateUser(dto.nickName(), dto);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestBody @Validated(value = DeleteUserValidation.class) UserDto dto){
        userService.deleteUser(dto.nickName(), dto.password());
    }
}
