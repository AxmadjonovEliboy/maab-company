package uz.boom.core_project_jwt.entity;

import jakarta.persistence.Entity;
import lombok.*;
import org.hibernate.annotations.Where;
import uz.boom.core_project_jwt.entity.base.Auditable;

/**
 * @author Jarvis on Fri 10:10. 28/04/23
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Where(clause = "deleted=false")
public class News extends Auditable {

    private String newsContent;

    private String description;

    private Long fileId;

}
