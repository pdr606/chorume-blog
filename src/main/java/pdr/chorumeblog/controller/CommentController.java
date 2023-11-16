package pdr.chorumeblog.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pdr.chorumeblog.dto.CommentDto;
import pdr.chorumeblog.model.CommentEntity;
import pdr.chorumeblog.model.PostEntity;
import pdr.chorumeblog.service.comment.CommentService;
import pdr.chorumeblog.service.post.PostService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/comments")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping(value = "/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public void create(Authentication authentication, @PathVariable Long postId, @RequestBody CommentEntity data){
        commentService.createComment(authentication, data, postId);
    }

    @GetMapping(value = "/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> findAll(@PathVariable Long commentId){
        return commentService.findAllCommentsOfUnicPost(commentId);
    }

}
