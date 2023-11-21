package pdr.chorumeblog.service.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import pdr.chorumeblog.common.UserConstants;
import pdr.chorumeblog.dto.UserDto;
import java.util.List;
import pdr.chorumeblog.exceptions.exceptions.NotFoundException;
import pdr.chorumeblog.infra.security.token.TokenService;
import pdr.chorumeblog.model.UserEntity;
import pdr.chorumeblog.repository.UserRepository;

import java.util.Collections;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static pdr.chorumeblog.common.UserConstants.*;


@ExtendWith(MockitoExtension.class)
class UserServiceImpTest {

    @Mock
    private UserRepository userRepository;
    private TokenService tokenService;
    @InjectMocks
    private UserServiceImp userService;

    @AfterEach
    public void cleanUp(){
        userRepository.deleteAll();
    }


    @Test
    void createUser_WithValid_ReturnUser() {
        when(userRepository.save(any())).thenReturn(VALID_OUTPUT_ENTITY);
        when(userRepository.findByNickName(VALID_INPUT_DTO.nickName())).thenReturn(VALID_OUTPUT_ENTITY);


        userService.createUser(VALID_INPUT_DTO);

        UserEntity user = userService.findUserByNickName(VALID_INPUT_DTO.nickName());

        assertThat(user.getNickName()).isEqualTo(VALID_INPUT_DTO.nickName());
    }

    @Test
    void deleteUser_WithSuccess_doesNotThrowAnyException() {
        when(userRepository.findByNickName(VALID_INPUT_ENTITY.getNickName())).thenReturn(VALID_OUTPUT_ENTITY);

        assertThatCode(() -> userService.deleteUser(VALID_INPUT_ENTITY.getNickName())).doesNotThrowAnyException();
    }

    @Test
    void deleteUser_WithUnexistingNickName_ThrowsException(){
        lenient().doThrow(new NotFoundException("User not found")).when(userRepository).delete(VALID_INPUT_ENTITY);

        assertThatThrownBy(() -> userService.deleteUser(VALID_INPUT_ENTITY.getNickName())).isInstanceOf(NotFoundException.class);
    }

    @Test
    void findUserByNickName_WithValidNickName_ReturnsUser() {
        when(userRepository.findByNickName("valid")).thenReturn(VALID_OUTPUT_ENTITY);

        UserEntity sut = userService.findUserByNickName("valid");

        assertThat(sut).isEqualTo(VALID_OUTPUT_ENTITY);
    }

    @Test
    void updateUser_WithValidData_ReturnsUser() {
        when(userRepository.findByNickName(any())).thenReturn(VALID_OUTPUT_ENTITY);
        when(userRepository.getReferenceById(any())).thenReturn(VALID_OUTPUT_ENTITY);
        when(userRepository.save(any())).thenReturn(VALID_INPUT_ENTITY);

        UserDto sut = userService.updateUser(VALID_INPUT_DTO_UPDATE, VALID_INPUT_ENTITY.getNickName());

        assertThat(sut).isNotNull();
        assertThat(sut.nickName()).isEqualTo(VALID_INPUT_DTO_UPDATE.nickName());
        assertThat(sut.email()).isEqualTo(VALID_INPUT_DTO_UPDATE.email());

    }


    @Test
    void updateProfilePicture_WithValidUrlPicture_doesNotThrowAnyException() {
        when(userRepository.findByNickName(any())).thenReturn(VALID_OUTPUT_ENTITY);

        userService.updateProfilePicture(URL_PHOTO_UPDATE, VALID_INPUT_DTO.nickName());

        assertThat(VALID_OUTPUT_ENTITY.getProfilePhoto()).isEqualTo(URL_PHOTO_UPDATE);

    }

    @Test
    void findRandomUsers_WithValid_ReturnsListOfUsers() {
        when(userRepository.findRandomUsers()).thenReturn(Collections.singletonList(VALID_INPUT_ENTITY));

        List<UserDto> list = userService.findRandomUsers();

        assertThat(list).hasSize(1);
    }

    @Test
    void findUserDetailsByNickName_WithValidNickName_ReturnsUserDetails() {
        when(userRepository.findByNickName(any())).thenReturn(VALID_OUTPUT_ENTITY);

        UserDetails sut = userService.findUserDetailsByNickName(VALID_INPUT_DTO.nickName());

        assertThat(sut).isEqualTo(VALID_USER_DETAILS);
    }
}