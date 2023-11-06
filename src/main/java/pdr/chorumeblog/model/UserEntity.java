package pdr.chorumeblog.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@Table(name = "TB_USER")
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nickName;
    private String email;
    private String profilePhoto;
    private String password;
    private String linkedin;
    private String github;
    private Integer likes;
}
