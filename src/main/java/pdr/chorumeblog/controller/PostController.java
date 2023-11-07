package pdr.chorumeblog.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pdr.chorumeblog.dto.PostDto;
import pdr.chorumeblog.dto.UserDto;
import pdr.chorumeblog.model.PostEntity;
import pdr.chorumeblog.model.UserEntity;
import pdr.chorumeblog.service.post.PostService;
import pdr.chorumeblog.service.user.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/posts")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping(value = "/{nickName}")
    @ResponseStatus(HttpStatus.OK)
    public void create(@PathVariable @Validated String nickName,@RequestBody @Validated PostEntity data){
        postService.create(nickName, data);
    }
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostDto findById(@PathVariable @Validated Long id){
        return PostDto.toDto(postService.findById(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PostDto> findAll(){
        return postService.findAll();
    }
}
