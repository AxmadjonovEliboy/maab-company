package uz.boom.core_project_jwt.dto.student;

import lombok.*;

/**
 * @author Jarvis on Fri 11:49. 28/04/23
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentRegisterDTO {

    private String name;

    private String surname;

    private String phoneNumber;

    private String email;

    private String englishProficiencyLevel;

    private String problemSolvingSkills;

    private String ITSkills;

    private String ITTools;


}
