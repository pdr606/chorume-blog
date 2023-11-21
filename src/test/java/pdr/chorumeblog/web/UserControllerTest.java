package pdr.chorumeblog.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import pdr.chorumeblog.controller.UserController;
import pdr.chorumeblog.infra.security.token.TokenService;
import pdr.chorumeblog.service.user.UserServiceImp;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static pdr.chorumeblog.common.UserConstants.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(UserController.class)

public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserServiceImp userServiceImp;

    @MockBean
    private TokenService tokenService;
    @Test
    public void createUser_WithValidData_ReturnsCreated() throws Exception {

        mockMvc.perform(post("/api/v1/users").content(objectMapper.writeValueAsString(VALID_INPUT_DTO)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        assertThatThrownBy(() -> userServiceImp.createUser(VALID_INPUT_DTO)).doesNotThrowAnyException();
    }
}
