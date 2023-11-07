package pdr.chorumeblog.service.post;


import pdr.chorumeblog.dto.PostDto;
import pdr.chorumeblog.model.PostEntity;
import java.util.List;

public interface PostService {
    void create(String nickName, PostEntity post);
    PostEntity findById(Long id);
    List<PostDto> findAll();
}
