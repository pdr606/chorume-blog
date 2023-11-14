package pdr.chorumeblog.service.uploadImage;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import pdr.chorumeblog.model.ImgurImageResponse;

import java.io.IOException;

@Service
public class UploadImageServiceImpl implements UploadImageService {

    @Value("${api.imgur.token}")
    private String imgurToken;

    @Value("${api.imgur.upload.url}")
    private String imgurUploadUrl;

    @Override
    public ImgurImageResponse uploadImage(MultipartFile file) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = configureHeaders();

        MultiValueMap<String, Object> body = configureBody(file);

        ImgurImageResponse imgurImageResponse = upload(restTemplate, headers, body);
        return imgurImageResponse;
    }

    private HttpHeaders configureHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("Authorization", "Bearer " + imgurToken);
        return headers;
    }

    private MultiValueMap<String, Object> configureBody(MultipartFile file) throws IOException {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("image", new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        });
        return body;
    }

    private ImgurImageResponse upload(RestTemplate restTemplate, HttpHeaders headers, MultiValueMap<String, Object> body) {
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<ImgurImageResponse> responseEntity = restTemplate.exchange(
                imgurUploadUrl,
                HttpMethod.POST,
                requestEntity,
                ImgurImageResponse.class
        );

        return responseEntity.getBody();
    }
}
