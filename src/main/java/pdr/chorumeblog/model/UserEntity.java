package pdr.chorumeblog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pdr.chorumeblog.model.utils.CreateAndUpdateEntity;

import java.util.UUID;

@Data
@NoArgsConstructor
@Table(name = "TB_USER")
@Entity
@Builder
@AllArgsConstructor
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

    @Embedded
    private CreateAndUpdateEntity dateTime;

    @PrePersist
    private void initializeCreateAndUpdate(){
        this.dateTime = new CreateAndUpdateEntity();
    }
}
