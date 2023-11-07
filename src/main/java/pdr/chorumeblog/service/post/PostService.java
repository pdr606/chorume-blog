package pdr.chorumeblog.service.post;


import pdr.chorumeblog.model.PostEntity;
import pdr.chorumeblog.model.UserEntity;

import java.util.UUID;

public interface PostService {
    void create(UUID userId, PostEntity post);
}
