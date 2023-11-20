package pdr.chorumeblog.service.user;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pdr.chorumeblog.dto.UserDto;
import pdr.chorumeblog.exceptions.exceptions.DuplicateException;
import pdr.chorumeblog.exceptions.exceptions.NotFoundException;
import pdr.chorumeblog.infra.security.token.TokenService;
import pdr.chorumeblog.mapper.user.UserMapper;
import pdr.chorumeblog.model.UserEntity;
import pdr.chorumeblog.repository.UserRepository;
import pdr.chorumeblog.service.authorizationService.AuthorizationService;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {

    private UserRepository userRepository;
    private TokenService tokenService;
    @Override
    public void createUser(UserDto dto) {
        if(!userRepository.existsByNickName(dto.nickName())){
            UserEntity userEntity = UserDto.toEntity(dto);
            userEntity.setPassword(AuthorizationService.encryptedPassword(
                    dto.password()
            ));
            userEntity.setLikes(0);
            userRepository.save(userEntity);
        }

    }

    @Override
    public void deleteUser(String nickName) {
        UserEntity entity = findUserByNickName(nickName);
        userRepository.delete(entity);
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
     public UserDto updateUser(UserDto dto, String nickName) {
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
            userRepository.save(entity);

            return UserMapper.INSTANCE.toDto(entity);

        } catch (DataIntegrityViolationException ex){
            throw new NotFoundException("User " + nickName + " not found.");
        }
    }

    @Override
    public void updateProfilePicture(String urlPhoto, String nickName) {
        UserEntity userEntity = findUserByNickName(nickName);
        userEntity.setProfilePhoto(urlPhoto);
        userRepository.save(userEntity);
    }

    @Override
    public List<UserDto> findRandomUsers() {
        return UserMapper.INSTANCE.toDtoList(userRepository.findRandomUsers());
    }

    @Override
    public UserDetails findUserDetailsByNickName(String nickName) {
        return userRepository.findByNickName(nickName);
    }

}
