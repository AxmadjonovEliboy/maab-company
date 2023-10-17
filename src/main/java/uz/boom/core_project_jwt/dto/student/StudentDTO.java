package uz.boom.core_project_jwt.dto.student;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import uz.boom.core_project_jwt.dto.base.GenericDTO;

/**
 * @author Jarvis on Mon 14:18. 10/04/23
 */

@Getter
@Setter
@ToString
public class StudentDTO extends GenericDTO {

    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String englishProficiencyLevel;
    private String problemSolvingSkills;
    private String ITSkills;
    private String ITTools;


    public StudentDTO(@NotBlank Long id,
                      String name,
                      String surname,
                      String phoneNumber,
                      String email,
                      String englishProficiencyLevel,
                      String problemSolvingSkills,
                      String ITSkills,
                      String ITTools) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.englishProficiencyLevel = englishProficiencyLevel;
        this.problemSolvingSkills = problemSolvingSkills;
        this.ITSkills = ITSkills;
        this.ITTools = ITTools;
    }
}
