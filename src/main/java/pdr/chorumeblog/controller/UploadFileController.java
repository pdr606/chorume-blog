package pdr.chorumeblog.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pdr.chorumeblog.dto.ImgurImageResponseDto;
import pdr.chorumeblog.service.uploadImage.UploadImageService;
import pdr.chorumeblog.service.user.UserService;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/uploadFile")
public class UploadFileController {

    private final UploadImageService uploadImageService;
    private final UserService userService;

    @PostMapping(value = "/user/{nickName}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadFile(@PathVariable String nickName, @RequestPart("file") MultipartFile file) throws IOException {
        ImgurImageResponseDto response = uploadImageService.uploadImage(file);
        userService.updateProfilePicture(response.getData().getLink(), nickName);
    }
}
