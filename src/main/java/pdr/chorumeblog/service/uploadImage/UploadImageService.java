package pdr.chorumeblog.service.uploadImage;

import org.springframework.web.multipart.MultipartFile;
import pdr.chorumeblog.dto.ImgurImageResponseDto;

import java.io.IOException;

public interface UploadImageService {

    ImgurImageResponseDto uploadImage(MultipartFile file) throws IOException;
}
