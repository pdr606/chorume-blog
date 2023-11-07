package pdr.chorumeblog.service.post;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pdr.chorumeblog.dto.PostDto;
import pdr.chorumeblog.mapper.post.PostMapper;
import pdr.chorumeblog.model.PostEntity;
import pdr.chorumeblog.model.UserEntity;
import pdr.chorumeblog.repository.PostRepository;
import pdr.chorumeblog.service.user.UserService;

import java.util.List;

@Service
@AllArgsConstructor
public class PostServiceImp implements PostService {

    private final UserService userService;

    private final PostRepository postRepository;
    @Override
    public void createPost(String nickName, PostEntity post) {
        UserEntity user = userService.findUserByNickName(nickName);
        post.setUser(user);
        postRepository.save(post);
    }
    @Override
    public PostEntity findPostById(Long id) {
        return postRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<PostDto> findAllPosts(Pageable pageable) {
        if(pageable.getPageSize() > 30){
            pageable = PageRequest.of(pageable.getPageNumber(), 30, pageable.getSort());
        }
        return PostMapper.INSTANCE.toDtoList(postRepository.findAll(pageable).getContent());
    }

    @Override
    public List<PostDto> findAllPostsByNickName(String nickName) {
        return PostMapper.INSTANCE.toDtoList(postRepository.findAllByUserNickName(nickName));
    }
}
