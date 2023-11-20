package pdr.chorumeblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import pdr.chorumeblog.dto.UserDto;
import pdr.chorumeblog.model.UserEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    UserEntity findByNickName(String nickName);
    boolean existsByNickName(String nickName);
    @Query(value = "SELECT * FROM user_entity ORDER BY RAND() LIMIT 4", nativeQuery = true)
    List<UserEntity> findRandomUsers();

}
