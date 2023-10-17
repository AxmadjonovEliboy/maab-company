package uz.boom.core_project_jwt.dto.news;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import uz.boom.core_project_jwt.dto.base.GenericDTO;

/**
 * @author Jarvis on Mon 10:24. 01/05/23
 */

@Getter
@Setter
public class NewsDTO extends GenericDTO {

    private String newsContent;

    private String description;

    private Long fileId;

    public NewsDTO(@NotBlank Long id, String newsContent, String description, Long fileId) {
        super(id);
        this.newsContent = newsContent;
        this.description = description;
        this.fileId = fileId;
    }
}
