package pdr.chorumeblog.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pdr.chorumeblog.dto.ImgurImageResponseDto;
import pdr.chorumeblog.infra.security.token.TokenService;
import pdr.chorumeblog.service.post.PostService;
import pdr.chorumeblog.service.uploadImage.UploadImageService;
import pdr.chorumeblog.service.user.UserService;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/uploadFile")
public class UploadFileController {

    private final UploadImageService uploadImageService;
    private final UserService userService;
    private final TokenService tokenService;
    private final PostService postService;

    @PostMapping(value = "/user", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadFile(@RequestPart("file") MultipartFile file, Authentication authentication) throws IOException {
        String nickName = tokenService.getUserNickNameByToken(authentication);
        ImgurImageResponseDto response = uploadImageService.uploadImage(file);
        userService.updateProfilePicture(response.getData().getLink(), nickName);
    }

    @PostMapping(value = "/{postId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadFile(@RequestPart("file") MultipartFile file, Authentication authentication, @PathVariable Long postId) throws IOException {
        tokenService.getUserNickNameByToken(authentication);
        ImgurImageResponseDto response = uploadImageService.uploadImage(file);
        postService.updatePostPicture(response.getData().getLink(), postId);
    }


}
