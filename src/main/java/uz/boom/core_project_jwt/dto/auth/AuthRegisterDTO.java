package uz.boom.core_project_jwt.dto.auth;

import lombok.*;
import uz.boom.core_project_jwt.dto.base.BaseDTO;

/**
 * @author Jarvis on Sat 13:20. 08/04/23
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthRegisterDTO implements BaseDTO {

    private String fullName;
    private String email;
    private String password;
    private String phoneNumber;
}
