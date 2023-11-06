package pdr.chorumeblog.model.utils;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Embeddable
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CreateAndUpdateEntity {
    @CreatedDate
    @Column(name = "created_at")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime created;

    @LastModifiedDate
    @Column(name = "updated_at")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime updated;

    @PrePersist
    private void setCreated() {
        this.created = LocalDateTime.now();
    }

    @PreUpdate
    private void setUpdated() {
        this.updated = LocalDateTime.now();
    }
}
