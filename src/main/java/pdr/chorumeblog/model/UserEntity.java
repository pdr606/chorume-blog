package pdr.chorumeblog.model;

import jakarta.persistence.*;
import lombok.*;
import pdr.chorumeblog.model.utils.CreateAndUpdateEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Table(name = "TB_USER")
@Entity
@Builder
@AllArgsConstructor
@ToString(exclude = {"posts", "comments"})
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String nickName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String profilePhoto;

    @Column(nullable = false)
    private String password;

    private String linkedin;

    private String github;

    private Integer likes;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<PostEntity> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<CommentEntity> comments = new ArrayList<>();

    @Embedded
    private CreateAndUpdateEntity dateTime;

    @PrePersist
    private void initializeCreateAndUpdate(){
        this.dateTime = new CreateAndUpdateEntity();
    }
}
