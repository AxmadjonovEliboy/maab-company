package uz.boom.core_project_jwt.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import uz.boom.core_project_jwt.dto.base.GenericDTO;

/**
 * @author Jarvis on Sat 11:14. 08/04/23
 */
@Getter
@Setter
@ToString
public class AuthUserDTO extends GenericDTO {

    private String fullName;
    private String email;
    private String phoneNumber;
    private String role;
    private String status;

    public AuthUserDTO(@NotBlank Long id, String fullName, String email, String phoneNumber, String role, String status) {
        super(id);
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.status = status;
    }

}
