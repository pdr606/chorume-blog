package pdr.chorumeblog.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pdr.chorumeblog.model.utils.CreateAndUpdateEntity;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Table(name = "TB_POST")
@Entity
@Builder
@AllArgsConstructor
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private boolean userLiked;

    private Integer likes;

    private String postPicture;

    @Embedded
    private CreateAndUpdateEntity dateTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CommentEntity> comments = new ArrayList<>();

    @PrePersist
    private void initializeCreateAndUpdate(){
        this.dateTime = new CreateAndUpdateEntity();
    }
}
