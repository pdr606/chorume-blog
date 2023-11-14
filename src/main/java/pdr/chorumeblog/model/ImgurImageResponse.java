package pdr.chorumeblog.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class ImgurImageResponse {
    private Data data;
    public static class Data {
        private String link;

        public String getLink() {
            return this.link;
        }
    }
}
