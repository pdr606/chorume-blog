package pdr.chorumeblog.service.authorizationService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import pdr.chorumeblog.service.user.UserService;
import static org.assertj.core.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;
import static pdr.chorumeblog.common.UserConstants.*;
@ExtendWith(MockitoExtension.class)
class AuthorizationServiceTest {

    @Mock
    private UserService userService;
    @InjectMocks
    private AuthorizationService authorizationService;
    @Test
    void loadUserByUsername_WithValidNickName_ReturnsUserDetails() {
        assertThatCode(() -> authorizationService.loadUserByUsername(VALID_INPUT_DTO.nickName())).doesNotThrowAnyException();
    }

    @Test
    void encryptedPassword_WithValidData_ReturnSuccess() {

        String ecryptedPassword = AuthorizationService.encryptedPassword(VALID_INPUT_DTO.password());

        assertThat(ecryptedPassword).isNotNull();
    }
}