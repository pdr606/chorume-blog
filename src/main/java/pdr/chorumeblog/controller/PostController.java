package pdr.chorumeblog.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pdr.chorumeblog.model.PostEntity;
import pdr.chorumeblog.model.UserEntity;
import pdr.chorumeblog.service.post.PostService;
import pdr.chorumeblog.service.user.UserService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/posts")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping(value = "/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public void create(@PathVariable UUID uuid,@RequestBody PostEntity data){
        postService.create(uuid, data);
    }


}
