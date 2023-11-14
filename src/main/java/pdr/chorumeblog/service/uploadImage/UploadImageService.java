package pdr.chorumeblog.service.uploadImage;

import org.springframework.web.multipart.MultipartFile;
import pdr.chorumeblog.model.ImgurImageResponse;

import java.io.IOException;

public interface UploadImageService {

    ImgurImageResponse uploadImage(MultipartFile file) throws IOException;
}
