package uz.boom.core_project_jwt.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.boom.core_project_jwt.controller.base.AbstractController;
import uz.boom.core_project_jwt.criteria.NewsCriteria;
import uz.boom.core_project_jwt.dto.news.NewsCreateDTO;
import uz.boom.core_project_jwt.dto.news.NewsDTO;
import uz.boom.core_project_jwt.response.DataDTO;
import uz.boom.core_project_jwt.service.NewsServiceImpl;

import java.util.List;

/**
 * @author Jarvis on Fri 10:36. 28/04/23
 */
@RestController
@Slf4j
public class NewsController extends AbstractController<NewsServiceImpl> {
    public NewsController(NewsServiceImpl service) {
        super(service);
    }


    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @PostMapping(value = PATH + "/news/create")
    public ResponseEntity<DataDTO<Long>> create(@RequestBody NewsCreateDTO dto) {
        log.info("REST:  NewsCreateDTO : {}  ", dto);
        return ResponseEntity.ok(
                new DataDTO<>(service.create(dto)));
    }

    @GetMapping(value = PATH + "/news/getAll")
    public ResponseEntity<DataDTO<List<NewsDTO>>> getAll() {
        log.info("REST:  List Of News : getAll() ");
        return ResponseEntity.ok(
                new DataDTO<>(service.getAll()));
    }

    @GetMapping(value = PATH + "/news/pagination")
    public ResponseEntity<DataDTO<List<NewsDTO>>> getAllByCriteria(@RequestParam("size") Integer size,
                                                                   @RequestParam("page") Integer page) {
        log.info("REST:  List Of News : getAllByCriteria() ");
        NewsCriteria criteria = new NewsCriteria();
        criteria.setSize(size);
        criteria.setPage(page);
        return ResponseEntity.ok(
                new DataDTO<>(service.getAllByCriteria(criteria)));
    }


    @GetMapping(value = PATH + "/news/get/{id}")
    public ResponseEntity<DataDTO<NewsDTO>> get(@PathVariable(name = "id") Long id) {
        log.info("REST:  News : get() ");
        return ResponseEntity.ok(
                new DataDTO<>(service.get(id)));
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @PutMapping(value = PATH + "/news/update")
    public ResponseEntity<DataDTO<Long>> update(@RequestBody NewsDTO dto) {
        log.info("REST: Update : NewsUpdateDTO : {} ", dto);
        return ResponseEntity.ok(
                new DataDTO<>(service.update(dto)));
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @DeleteMapping(value = PATH + "/news/{id}")
    public ResponseEntity<DataDTO<Long>> delete(@PathVariable(name = "id") Long id) {
        log.info("REST:  Delete News by : {}  ", id);
        return ResponseEntity.ok(
                new DataDTO<>(service.delete(id)));
    }

}
