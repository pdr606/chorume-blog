package pdr.chorumeblog.service.user;


import pdr.chorumeblog.model.UserEntity;
import java.util.UUID;

public interface UserService {

    void save(UserEntity data);
    UserEntity getById(UUID id);
}
