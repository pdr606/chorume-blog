package pdr.chorumeblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdr.chorumeblog.model.UserPostLikeEntity;

@Repository
public interface UserPostLikeRepository extends JpaRepository<UserPostLikeEntity, Long> {
}
