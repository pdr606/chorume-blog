package pdr.chorumeblog.service.user;

import org.springframework.stereotype.Service;
import pdr.chorumeblog.model.UserEntity;
import pdr.chorumeblog.repository.UserRepository;

import java.util.UUID;

@Service
public class UserServiceImp implements UserService {

    private UserRepository userRepository;
    @Override
    public void save(UserEntity data) {
        userRepository.save(data);
    }

    @Override
    public UserEntity getById(UUID id) {
        return userRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}
