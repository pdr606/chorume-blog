package pdr.chorumeblog.controller;

import com.sun.net.httpserver.Headers;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import pdr.chorumeblog.config.groupsValidation.CreateUserValidation;
import pdr.chorumeblog.config.groupsValidation.DeleteUserValidation;
import pdr.chorumeblog.config.groupsValidation.UpdateUserValidation;
import pdr.chorumeblog.dto.UserDto;
import pdr.chorumeblog.mapper.user.UserMapper;
import pdr.chorumeblog.model.ImgurImageResponse;
import pdr.chorumeblog.service.user.UserService;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final String BEARER_TOKEN = "592422d9d76256a991026439f846022313fb1606";
    private final String IMGUR_API_URL = "https://api.imgur.com/3/image";

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Validated(value = CreateUserValidation.class) UserDto data) {
            userService.createUser(data);
    }

    @PostMapping(value = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadFile(@RequestPart("file") MultipartFile file) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("Authorization", "Bearer " + BEARER_TOKEN);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("image", new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        });

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<ImgurImageResponse> responseEntity = restTemplate.exchange(
                IMGUR_API_URL,
                HttpMethod.POST,
                requestEntity,
                ImgurImageResponse.class
        );

        String link = responseEntity.getBody().getData().getLink();


        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            System.out.println("Image uploaded successfully. Imgur response: " + responseEntity.getBody());
        } else {
            System.err.println("Failed to upload image. Imgur response: " + responseEntity.getBody());
        }
    }

    @GetMapping(value = "/{nickName}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getById(@PathVariable String nickName){
        return UserMapper.INSTANCE.toDto(userService.findUserByNickName(nickName));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UserDto update(@RequestBody @Validated(value = UpdateUserValidation.class) UserDto dto){
        return userService.updateUser(dto.nickName(), dto);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestBody @Validated(value = DeleteUserValidation.class) UserDto dto){
        userService.deleteUser(dto.nickName(), dto.password());
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> randomUsers(){
        return userService.findRandomUsers();
    }


}
