package pdr.chorumeblog.service.post;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.*;
import pdr.chorumeblog.dto.PostDto;
import pdr.chorumeblog.exceptions.exceptions.NotFoundException;
import pdr.chorumeblog.infra.security.token.TokenService;
import pdr.chorumeblog.model.PostEntity;
import pdr.chorumeblog.repository.PostRepository;
import pdr.chorumeblog.service.user.UserService;
import pdr.chorumeblog.service.userPostLike.UserPostLikeService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.List;

import static org.mockito.Mockito.*;
import static pdr.chorumeblog.common.PostConstants.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static pdr.chorumeblog.common.PostConstants.*;
import static pdr.chorumeblog.common.UserConstants.*;
@ExtendWith(MockitoExtension.class)
class PostServiceImpTest {

    @Mock
    private  UserService userService;
    @Mock
    private  TokenService tokenService;
    @Mock
    private  PostRepository postRepository;
    @Mock
    private  UserPostLikeService userPostLikeService;

    @InjectMocks
    private  PostServiceImp postService;

    @Test
    void createPost_WithValidData_ReturnsPost() {
        when(tokenService.getUserNickNameByToken(any())).thenReturn(VALID_OUTPUT_ENTITY.getNickName());
        when(userService.findUserByNickName(VALID_OUTPUT_ENTITY.getNickName())).thenReturn(VALID_OUTPUT_ENTITY);

        assertThatCode(() -> postService.createPost(VALID_POST_DTO_ENTITY, null )).doesNotThrowAnyException();

    }

    @Test
    void findPostById_WithValidId_ReturnsPost() {
        when(postRepository.findById(any())).thenReturn(Optional.of(VALID_POST_ENTITY));

        PostEntity sut = postService.findPostById(1L);

        assertThat(sut).isEqualTo(VALID_POST_ENTITY);
    }

    @Test
    void findPostById_WithInvalidId_ThorwsException(){
        when(postRepository.findById(any())).thenThrow(NotFoundException.class);

        assertThatThrownBy(() -> postService.findPostById(1L)).isInstanceOf(NotFoundException.class);
    }

    @Test
    void findAllPosts_WithValidData_ReturnsPosts() {
        List<PostEntity> mockPosts = new ArrayList<>(){
            {
                add(VALID_POST_ENTITY);
                add(VALID_POST_ENTITY);
            }
        };

        when(postRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(mockPosts));

        List<PostDto> sut = postService.findAllPosts(PageRequest.of(0, 10));

        assertThat(sut).hasSize(1);
        assertThat(sut.get(0)).isEqualTo(VALID_POST_ENTITY);
    }

    @Test
    void findAllPostsByNickName_WithValidData_ReturnsPosts() {
        when(postRepository.findAllByUserNickName(any())).thenReturn(Collections.singletonList(VALID_POST_ENTITY));
        List<PostDto> sut = postService.findAllPostsByNickName(VALID_POST_ENTITY.getUser().getNickName());

        assertThat(sut).hasSize(1);
        assertThat(sut.get(0).user().nickName()).isEqualTo(VALID_POST_ENTITY.getUser().getNickName());
    }

    @Test
    void updatePostPicture_WithValidData_Success() {
        when(postRepository.getReferenceById(any())).thenReturn(VALID_POST_ENTITY);

        postService.updatePostPicture(URL_PHOTO_UPDATE, 1L);

        assertThat(VALID_POST_ENTITY.getPostPicture()).isEqualTo(URL_PHOTO_UPDATE);
    }

    @Test
    void acrescentLike_WithValidData_Success() {
        when(postRepository.findById(any())).thenReturn(Optional.of(VALID_POST_ENTITY));
        when(tokenService.getUserNickNameByToken(any())).thenReturn(VALID_POST_ENTITY.getUser().getNickName());
        when(userService.findUserByNickName(VALID_POST_ENTITY.getUser().getNickName())).thenReturn(VALID_INPUT_ENTITY);

        assertThatCode(() -> postService.acrescentLike(1L, null)).doesNotThrowAnyException();

        verify(postRepository, times(1)).save(VALID_POST_ENTITY);
    }
}