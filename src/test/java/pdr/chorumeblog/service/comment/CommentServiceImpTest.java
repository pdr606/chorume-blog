package pdr.chorumeblog.service.comment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pdr.chorumeblog.dto.CommentDto;
import pdr.chorumeblog.infra.security.token.TokenService;
import pdr.chorumeblog.model.CommentEntity;
import pdr.chorumeblog.repository.CommentRepository;
import pdr.chorumeblog.service.post.PostService;
import pdr.chorumeblog.service.user.UserService;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static pdr.chorumeblog.common.UserConstants.*;
import static pdr.chorumeblog.common.PostConstants.*;
import static pdr.chorumeblog.common.CommentConstants.*;

import java.util.ArrayList;
import java.util.List;
@ExtendWith(MockitoExtension.class)
class CommentServiceImpTest {
    @Mock
    private  UserService userService;
    @Mock
    private  PostService postService;
    @Mock
    private  CommentRepository commentRepository;
    @Mock
    private  TokenService tokenService;


    @InjectMocks
    private CommentServiceImp commentService;

    @Test
    void createComment_WithValidData_Success() {
        when(tokenService.getUserNickNameByToken(any())).thenReturn(VALID_OUTPUT_ENTITY.getNickName());
        when(userService.findUserByNickName(VALID_INPUT_DTO.nickName())).thenReturn(VALID_OUTPUT_ENTITY);
        when(postService.findPostById(any())).thenReturn(VALID_POST_ENTITY);

        assertThatCode(() -> commentService.createComment(null, VALID_COMMENT_INPUT_ENTITY, 1L)).doesNotThrowAnyException();
    }

    @Test
    void findAllCommentsOfUnicPost_WithValidId_ReturnsAllPosts() {
        List<CommentEntity> mockComments = new ArrayList<>(){
            {
                add(VALID_COMMENT_INPUT_ENTITY);
                add(VALID_COMMENT_INPUT_ENTITY);
            }
        };

        when(commentRepository.findAllByPostId(1L)).thenReturn(mockComments);

        List<CommentDto> sut = commentService.findAllCommentsOfUnicPost(1L);

        assertThat(sut).hasSize(2);
        assertThat(sut.get(0).user().nickName()).isEqualTo(VALID_COMMENT_INPUT_DTO.user().nickName());
        assertThat(sut.get(0).user().email()).isEqualTo(VALID_COMMENT_INPUT_DTO.user().email());
    }
}