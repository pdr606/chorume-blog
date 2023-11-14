package pdr.chorumeblog.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class ImgurImageResponseDto {
    private Data data;
    public static class Data {
        private String link;

        public String getLink() {
            return this.link;
        }
    }
}
