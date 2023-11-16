package pdr.chorumeblog.service.post;


import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import pdr.chorumeblog.dto.PostDto;
import pdr.chorumeblog.dto.UserDto;
import pdr.chorumeblog.model.PostEntity;
import java.util.List;

public interface PostService {
    void createPost(PostDto dto, Authentication authentication);
    PostEntity findPostById(Long id);
    List<PostDto> findAllPosts(Pageable pageable);
    List<PostDto> findAllPostsByNickName(String nickName);

    void updatePostPicture(String urlPhoto, Long postId);
    void acrescentLike(Long id, Authentication authentication);
}
