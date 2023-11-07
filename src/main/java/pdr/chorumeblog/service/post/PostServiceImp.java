package pdr.chorumeblog.service.post;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pdr.chorumeblog.dto.PostDto;
import pdr.chorumeblog.dto.UserDto;
import pdr.chorumeblog.model.PostEntity;
import pdr.chorumeblog.model.UserEntity;
import pdr.chorumeblog.repository.PostRepository;
import pdr.chorumeblog.service.user.UserService;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PostServiceImp implements PostService {

    private final UserService userService;

    private final PostRepository postRepository;
    @Override
    public void create(String nickName, PostEntity post) {
        UserEntity user = userService.getByNickName(nickName);
        post.setUser(user);
        postRepository.save(post);
    }

    @Override
    public PostEntity findById(Long id) {
        return postRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<PostDto> findAll() {
        return null;
    }
}
