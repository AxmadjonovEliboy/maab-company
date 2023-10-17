package uz.boom.core_project_jwt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import org.hibernate.annotations.Where;
import uz.boom.core_project_jwt.entity.base.Auditable;

/**
 * @author Jarvis on Fri 10:04. 28/04/23
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Where(clause = "deleted=false")
public class Student extends Auditable {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @Column(name = "email", unique = true)
    private String email;

    private String englishProficiencyLevel;

    private String problemSolvingSkills;

    private String ITSkills;

    private String ITTools;
}
