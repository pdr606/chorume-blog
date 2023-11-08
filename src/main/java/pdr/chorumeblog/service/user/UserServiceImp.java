package pdr.chorumeblog.service.user;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import pdr.chorumeblog.dto.UserDto;
import pdr.chorumeblog.exceptions.exceptions.DuplicateException;
import pdr.chorumeblog.exceptions.exceptions.NotFoundException;
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
            UserEntity user = userRepository.findByNickName(nickName);
            if (user != null){
                return user;
            }
            throw new NotFoundException("User " + nickName + " not found.");
    }

    @Override
    public UserDto updateUser(String nickName, UserDto dto) {
        try {
            UserEntity entity = findUserByNickName(nickName);
            entity = userRepository.getReferenceById(entity.getId());
            if(dto.email().equals(entity.getEmail())){
                throw new DuplicateException("Email " + dto.email() + " is duplicated.");
            }
            if (dto.nickName().equals(entity.getNickName())){
                throw new DuplicateException("Nickname " + dto.email()+ " is duplicated.");
            }

            UserMapper.INSTANCE.updateUserFromDto(dto, entity);

            return UserMapper.INSTANCE.toDto(entity);

        } catch (DataIntegrityViolationException ex){
            throw new NotFoundException("User " + nickName + " not found.");
        }
    }

}
