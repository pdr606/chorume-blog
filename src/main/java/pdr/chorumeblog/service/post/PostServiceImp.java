package pdr.chorumeblog.service.post;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pdr.chorumeblog.dto.PostDto;
import pdr.chorumeblog.dto.UserDto;
import pdr.chorumeblog.exceptions.exceptions.NotFoundException;
import pdr.chorumeblog.mapper.post.PostMapper;
import pdr.chorumeblog.model.PostEntity;
import pdr.chorumeblog.model.UserEntity;
import pdr.chorumeblog.model.UserPostLikeEntity;
import pdr.chorumeblog.repository.PostRepository;
import pdr.chorumeblog.service.user.UserService;
import pdr.chorumeblog.service.userPostLike.UserPostLikeService;

import java.util.List;

@Service
@AllArgsConstructor
public class PostServiceImp implements PostService {

    private final UserService userService;

    private final PostRepository postRepository;
    private final UserPostLikeService userPostLikeService;
    @Override
    public void createPost(String nickName, PostDto dto) {
        UserEntity user = userService.findUserByNickName(nickName);
        PostEntity post = PostDto.toEntity(dto);
        post.setUser(user);
        post.setLikes(0);
        postRepository.save(post);
    }
    @Override
    public PostEntity findPostById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new NotFoundException("Post of id " + id + " not found."));
    }

    @Override
    public List<PostDto> findAllPosts(Pageable pageable) {
        if(pageable.getPageSize() > 30){
            pageable = PageRequest.of(pageable.getPageNumber(), 30, pageable.getSort());
        }
        List<PostEntity> posts = postRepository.findAll(pageable).getContent();
        posts.stream().filter(postEntity -> userPostLikeService.checkIfUserLike(postEntity) == true)
                .forEach(post -> post.setUserLiked(true));

        return PostMapper.INSTANCE.toDtoList(posts);
    }

    @Override
    public List<PostDto> findAllPostsByNickName(String nickName) {
        UserEntity user = userService.findUserByNickName(nickName);
        return PostMapper.INSTANCE.toDtoList(postRepository.findAllByUserNickName(nickName));
    }

    @Override
    public void acrescentLike(Long id, String nickName) {
        PostEntity post = findPostById(id);
        UserEntity user = userService.findUserByNickName(nickName);
        if(userPostLikeService.saveLike(UserPostLikeEntity.builder().user(user).post(post).build())){
            post.setLikes(post.getLikes() + 1);
            postRepository.save(post);
            return;
        }
        post.setLikes(post.getLikes() - 1);
        postRepository.save(post);
    }
}
