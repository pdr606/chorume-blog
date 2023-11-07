package pdr.chorumeblog.service.comment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pdr.chorumeblog.model.CommentEntity;
import pdr.chorumeblog.model.PostEntity;
import pdr.chorumeblog.model.UserEntity;
import pdr.chorumeblog.repository.CommentRepository;
import pdr.chorumeblog.repository.PostRepository;
import pdr.chorumeblog.service.post.PostService;
import pdr.chorumeblog.service.user.UserService;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CommentServiceImp implements CommentService {

    private final UserService userService;

    private final CommentRepository commentRepository;
    @Override
    public void create(UUID userId, CommentEntity comment) {
        UserEntity user = userService.getById(userId);
        comment.setUser(user);
        commentRepository.save(comment);
    }
}
