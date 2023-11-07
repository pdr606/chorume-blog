package pdr.chorumeblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdr.chorumeblog.model.CommentEntity;
import pdr.chorumeblog.model.PostEntity;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

    List<PostEntity> findAllByUserNickName(String nickName);
}
