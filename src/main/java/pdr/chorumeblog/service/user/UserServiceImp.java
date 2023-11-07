package pdr.chorumeblog.service.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pdr.chorumeblog.dto.UserDto;
import pdr.chorumeblog.mapper.user.UserMapper;
import pdr.chorumeblog.model.UserEntity;
import pdr.chorumeblog.repository.UserRepository;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {

    private UserRepository userRepository;
    @Override
    public void createUser(UserDto dto) {
        userRepository.save(UserDto.toEntity(dto));
    }

    @Override
    public void deleteUser(String nickName, String password) {
        UserEntity entity = findUserByNickName(nickName);
        if(password.equals(entity.getPassword())){
            userRepository.delete(entity);
        }
    }

    @Override
    public UserEntity findUserByNickName(String nickName) {
        return userRepository.findByNickName(nickName);
    }

    @Override
    public UserDto updateUser(String nickName, UserDto dto) {
        try {
            UserEntity entity = findUserByNickName(nickName);
            if(dto.email().equals(entity.getEmail())){
                throw new RuntimeException();
            }
            UserMapper.INSTANCE.updateUserFromDto(dto, entity);

            return UserMapper.INSTANCE.toDto(entity);

        } catch (RuntimeException ex){
            throw new RuntimeException();
        }
    }

}
