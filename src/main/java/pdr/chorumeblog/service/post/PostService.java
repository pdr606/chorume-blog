package pdr.chorumeblog.service.post;


import org.springframework.data.domain.Pageable;
import pdr.chorumeblog.dto.PostDto;
import pdr.chorumeblog.dto.UserDto;
import pdr.chorumeblog.model.PostEntity;
import java.util.List;

public interface PostService {
    void createPost(String nickName, PostDto dto);
    PostEntity findPostById(Long id);
    List<PostDto> findAllPosts(Pageable pageable);
    List<PostDto> findAllPostsByNickName(String nickName);
    void acrescentLike(Long id, String nickName);
}
