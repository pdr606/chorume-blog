package pdr.chorumeblog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
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
    @Column(unique = true, nullable = false)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String nickName;

    @Column(nullable = false, unique = true)
    private String email;

    private String profilePhoto;

    @Column(nullable = false)
    private String password;

    private String linkedin;

    private String github;

    private Integer likes;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PostEntity> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<CommentEntity> comments = new ArrayList<>();

    @ManyToMany
    private List<UserPostLikeEntity> userPostLikes = new ArrayList<>();

    @Embedded
    private CreateAndUpdateEntity dateTime;
    @PrePersist
    private void initializeCreateAndUpdate(){
        this.dateTime = new CreateAndUpdateEntity();
    }

}
