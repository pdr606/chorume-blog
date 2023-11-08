package pdr.chorumeblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdr.chorumeblog.dto.UserDto;
import pdr.chorumeblog.model.UserEntity;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    UserEntity findByNickName(String nickName);

}
