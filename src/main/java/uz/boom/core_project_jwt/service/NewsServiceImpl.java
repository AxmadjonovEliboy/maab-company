package uz.boom.core_project_jwt.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import uz.boom.core_project_jwt.criteria.NewsCriteria;
import uz.boom.core_project_jwt.dto.news.NewsCreateDTO;
import uz.boom.core_project_jwt.dto.news.NewsDTO;
import uz.boom.core_project_jwt.entity.Attachment;
import uz.boom.core_project_jwt.entity.News;
import uz.boom.core_project_jwt.exception.NotFoundException;
import uz.boom.core_project_jwt.mapper.NewsMapper;
import uz.boom.core_project_jwt.repository.AttachmentRepository;
import uz.boom.core_project_jwt.repository.NewsRepository;
import uz.boom.core_project_jwt.service.base.AbstractService;
import uz.boom.core_project_jwt.service.base.NewsService;

import java.util.List;

/**
 * @author Jarvis on Fri 10:32. 28/04/23
 */
@Service
public class NewsServiceImpl extends AbstractService<NewsRepository, NewsMapper> implements NewsService {


    private final AttachmentRepository attachmentRepository;

    public NewsServiceImpl(NewsRepository repository, @Qualifier("newsMapperImpl") NewsMapper mapper, AttachmentRepository attachmentRepository) {
        super(repository, mapper);
        this.attachmentRepository = attachmentRepository;
    }

    @Override
    public Long create(NewsCreateDTO dto) {
        News news = mapper.fromCreateDTO(dto);
        News savedNews = repository.save(news);
        return savedNews.getId();
    }

    @Override
    public List<NewsDTO> getAll() {
        List<News> allNews = repository.findAll();
        return mapper.toDTO(allNews);
    }


    @Override
    public List<NewsDTO> getAllByCriteria(NewsCriteria criteria) {
        List<News> news = repository.findNewsByCriteria(
                        criteria.getSize(),
                        criteria.getPage())
                .orElseThrow(() -> new NotFoundException("NEWS NOT FOUND !"));
        return mapper.toDTO(news);
    }

    @Override
    public NewsDTO get(Long id) {
        News news = repository.findById(id).orElseThrow(() -> new NotFoundException("THERE IS NO NEWS WITH THIS ID"));
        return mapper.toDTO(news);
    }

    @Override
    public Long update(NewsDTO dto) {
        News news = repository.findById(dto.getId()).orElseThrow(() -> new NotFoundException("THERE IS NO NEWS WITH THIS ID"));
        News updateDTO = mapper.fromUpdateDTO(dto, news);
        News savedNews = repository.save(updateDTO);
        return savedNews.getId();
    }

    @Override
    public Long delete(Long id) {
        News news = repository.findById(id).orElseThrow(() -> new NotFoundException("THERE IS NO NEWS WITH THIS ID"));
        repository.delete(news);
//        Attachment attachment = attachmentRepository.findById(news.getFileId()).orElseThrow(() -> new NotFoundException("NOT FOUND"));
//        attachmentRepository.delete(attachment);
        return news.getId();
    }


}
