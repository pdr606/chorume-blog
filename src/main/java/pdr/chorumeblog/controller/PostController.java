package pdr.chorumeblog.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pdr.chorumeblog.config.security.SecurityConfiguration;
import pdr.chorumeblog.dto.PostDto;
import pdr.chorumeblog.dto.UserDto;
import pdr.chorumeblog.infra.security.token.TokenService;
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

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestBody @Validated PostDto dto, Authentication authentication){
        postService.createPost(dto, authentication);
    }

    @GetMapping(value = "/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public PostDto findById(@PathVariable @Validated Long postId){
        return PostDto.toDto(postService.findPostById(postId));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PostDto> findAll(
            @PageableDefault(direction = Sort.Direction.ASC, page = 0, size = 30)Pageable pageable
            ){
        return postService.findAllPosts(pageable);
    }
    @GetMapping(value = "/user/{nickName}")
    public List<PostDto> findAllByNickName(@PathVariable String nickName){
        return postService.findAllPostsByNickName(nickName);
    }

    @PostMapping(value = "/{postId}")
    public void likePost(@PathVariable Long postId, Authentication authentication){
        postService.acrescentLike(postId, authentication);
    }
}
