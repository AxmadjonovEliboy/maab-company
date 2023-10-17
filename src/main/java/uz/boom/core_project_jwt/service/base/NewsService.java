package uz.boom.core_project_jwt.service.base;

import uz.boom.core_project_jwt.criteria.NewsCriteria;
import uz.boom.core_project_jwt.dto.news.NewsCreateDTO;
import uz.boom.core_project_jwt.dto.news.NewsDTO;

import java.util.List;

/**
 * @author Jarvis on Fri 10:31. 28/04/23
 */
public interface NewsService extends BaseService {

    Long create(NewsCreateDTO dto);

    List<NewsDTO> getAll();

    List<NewsDTO> getAllByCriteria(NewsCriteria criteria);

    NewsDTO get(Long id);

    Long update(NewsDTO dto);

    Long delete(Long id);
}
