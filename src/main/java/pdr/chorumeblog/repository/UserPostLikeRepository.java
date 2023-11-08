package pdr.chorumeblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdr.chorumeblog.model.PostEntity;
import pdr.chorumeblog.model.UserEntity;
import pdr.chorumeblog.model.UserPostLikeEntity;

import java.util.UUID;

@Repository
public interface UserPostLikeRepository extends JpaRepository<UserPostLikeEntity, Long> {
    UserPostLikeEntity findByUserAndPost(UserEntity userEntity, PostEntity postEntity);
}
