package uz.boom.core_project_jwt.entity.base;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * @author Jarvis on Sat 11:32. 08/04/23
 */

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @CreationTimestamp
//    @Column(name = "create_at", columnDefinition = "TIMESTAMP default NOW()")
    private LocalDateTime createAt;

    @LastModifiedDate
    @UpdateTimestamp
    private LocalDateTime updateAt;

    private Boolean deleted = false;

    private Boolean blocked = false;


}
