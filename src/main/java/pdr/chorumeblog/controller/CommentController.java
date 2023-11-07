package pdr.chorumeblog.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pdr.chorumeblog.model.CommentEntity;
import pdr.chorumeblog.model.PostEntity;
import pdr.chorumeblog.service.comment.CommentService;
import pdr.chorumeblog.service.post.PostService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/comments")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping(value = "/{nickName}/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public void create(@PathVariable String nickName, @PathVariable Long postId,@RequestBody CommentEntity data){
        commentService.createComment(nickName, data, postId);
    }


}
