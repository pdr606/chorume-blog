package pdr.chorumeblog.service.post;


import org.springframework.data.domain.Pageable;
import pdr.chorumeblog.dto.PostDto;
import pdr.chorumeblog.model.PostEntity;
import java.util.List;

public interface PostService {
    void createPost(String nickName, PostEntity post);
    PostEntity findPostById(Long id);
    List<PostDto> findAllPosts(Pageable pageable);
    List<PostDto> findAllPostsByNickName(String nickName);
}
