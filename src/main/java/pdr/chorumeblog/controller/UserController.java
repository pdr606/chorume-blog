package pdr.chorumeblog.controller;

import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pdr.chorumeblog.model.UserEntity;
import pdr.chorumeblog.service.user.UserService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void save(@RequestBody UserEntity data){
        userService.save(data);
    }

    @GetMapping(value = "/uuid")
    @ResponseStatus(HttpStatus.OK)
    public UserEntity getById(@PathVariable UUID uuid){
        return userService.getById(uuid);
    }
}
