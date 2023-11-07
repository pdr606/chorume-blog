package pdr.chorumeblog.service.post;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pdr.chorumeblog.model.PostEntity;
import pdr.chorumeblog.model.UserEntity;
import pdr.chorumeblog.repository.PostRepository;
import pdr.chorumeblog.service.user.UserService;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PostServiceImp implements PostService {

    private final UserService userService;

    private final PostRepository postRepository;
    @Override
    public void create(UUID userId, PostEntity post) {
        UserEntity user = userService.getById(userId);
        post.setUser(user);
        postRepository.save(post);
    }
}
