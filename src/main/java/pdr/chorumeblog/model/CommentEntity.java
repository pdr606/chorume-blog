package pdr.chorumeblog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pdr.chorumeblog.model.utils.CreateAndUpdateEntity;

@Data
@NoArgsConstructor
@Table(name = "TB_COMMENT")
@Entity
@Builder
@AllArgsConstructor
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String content;

    private Integer likes;

    @Embedded
    private CreateAndUpdateEntity dateTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostEntity post;

    @PrePersist
    private void initializeCreateAndUpdate(){
        this.dateTime = new CreateAndUpdateEntity();
    }
}
