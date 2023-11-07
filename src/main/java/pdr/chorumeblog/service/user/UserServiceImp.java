package pdr.chorumeblog.service.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pdr.chorumeblog.dto.UserDto;
import pdr.chorumeblog.model.UserEntity;
import pdr.chorumeblog.repository.UserRepository;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {

    private UserRepository userRepository;
    @Override
    public void create(UserDto dto) {
        userRepository.save(UserDto.toEntity(dto));
    }

    @Override
    public UserEntity getByNickName(String nickName) {
        return userRepository.findByNickName(nickName);
    }

}
