package pdr.chorumeblog.service.comment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pdr.chorumeblog.dto.CommentDto;
import pdr.chorumeblog.dto.UserDto;
import pdr.chorumeblog.mapper.comment.CommentMapper;
import pdr.chorumeblog.mapper.post.PostMapper;
import pdr.chorumeblog.model.CommentEntity;
import pdr.chorumeblog.model.PostEntity;
import pdr.chorumeblog.model.UserEntity;
import pdr.chorumeblog.repository.CommentRepository;
import pdr.chorumeblog.repository.PostRepository;
import pdr.chorumeblog.service.post.PostService;
import pdr.chorumeblog.service.user.UserService;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CommentServiceImp implements CommentService {

    private final UserService userService;

    private final PostService postService;

    private final CommentRepository commentRepository;
    @Override
    public void createComment(String nickName, CommentEntity comment, Long postId) {
        UserEntity user = userService.findUserByNickName(nickName);
        PostEntity post = postService.findPostById(postId);
        comment.setUser(user);
        comment.setPost(post);
        commentRepository.save(comment);
    }

    @Override
    public List<CommentDto> findAllCommentsOfUnicPost(Long id) {
        return CommentMapper.INSTANCE.toDtoList(commentRepository.findAllByPostId(id));
    }
}
