package pdr.chorumeblog.service.comment;


import org.springframework.security.core.Authentication;
import pdr.chorumeblog.dto.CommentDto;
import pdr.chorumeblog.model.CommentEntity;
import pdr.chorumeblog.model.PostEntity;

import java.util.List;
import java.util.UUID;

public interface CommentService {
    void createComment(Authentication authentication, CommentEntity comment, Long postId);
    List<CommentDto> findAllCommentsOfUnicPost(Long id);
}
