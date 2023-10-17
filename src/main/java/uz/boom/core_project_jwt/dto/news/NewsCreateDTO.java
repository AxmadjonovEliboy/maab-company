package uz.boom.core_project_jwt.dto.news;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.boom.core_project_jwt.dto.base.BaseDTO;

/**
 * @author Jarvis on Mon 10:23. 01/05/23
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewsCreateDTO implements BaseDTO {


    private String newsContent;

    private String description;

    private Long fileId;


}
