package uz.boom.core_project_jwt.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;
import uz.boom.core_project_jwt.dto.news.NewsCreateDTO;
import uz.boom.core_project_jwt.dto.news.NewsDTO;
import uz.boom.core_project_jwt.entity.News;
import uz.boom.core_project_jwt.mapper.base.BaseMapper;

import java.util.List;

/**
 * @author Jarvis on Fri 10:30. 28/04/23
 */
@Component
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface NewsMapper extends BaseMapper {

    List<NewsDTO> toDTO(List<News> news);

    NewsDTO toDTO(News news);

    News fromCreateDTO(NewsCreateDTO createDto);

    News fromUpdateDTO(NewsDTO updateDTO, @MappingTarget News target);
}
