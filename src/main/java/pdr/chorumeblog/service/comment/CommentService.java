package pdr.chorumeblog.service.comment;


import pdr.chorumeblog.model.CommentEntity;
import pdr.chorumeblog.model.PostEntity;

import java.util.UUID;

public interface CommentService {
    void create(UUID userId, CommentEntity comment);
}
