package uz.boom.core_project_jwt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import uz.boom.core_project_jwt.exception.NotFoundException;

import java.util.Objects;

/**
 * @author Jarvis on Sat 11:30. 08/04/23
 */
@Getter
@AllArgsConstructor
public enum Role {

    SUPER_ADMIN("super_admin"),
    ADMIN("admin"),
    STUDENT("student"),
    EMPLOYEE("employee");

    private final String name;

    public static Role getByName(String roleName) {
        if (Objects.isNull(roleName)) throw new NotFoundException("FIELD NOT FOUND");
        for (Role value : Role.values()) {
            if (value.name.equalsIgnoreCase(roleName)) return value;
        }
        throw new NotFoundException("ROLE NOT FOUND");
    }
}
