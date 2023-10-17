package uz.boom.core_project_jwt.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.boom.core_project_jwt.controller.base.AbstractController;
import uz.boom.core_project_jwt.criteria.StudentCriteria;
import uz.boom.core_project_jwt.dto.student.StudentDTO;
import uz.boom.core_project_jwt.dto.student.StudentRegisterDTO;
import uz.boom.core_project_jwt.response.DataDTO;
import uz.boom.core_project_jwt.service.StudentServiceImpl;

import java.util.List;

/**
 * @author Jarvis on Fri 10:34. 28/04/23
 */
@RestController
@Slf4j
public class StudentController extends AbstractController<StudentServiceImpl> {
    public StudentController(StudentServiceImpl service) {
        super(service);
    }

    @PostMapping(value = PATH + "/student/register")
    public ResponseEntity<DataDTO<Long>> register(@RequestBody StudentRegisterDTO dto) {
        log.info("REST:  StudentRegisterDTO : {}  ", dto);
        return ResponseEntity.ok(
                new DataDTO<>(service.register(dto)));
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @GetMapping(value = PATH + "/students")
    public ResponseEntity<DataDTO<List<StudentDTO>>> getAll() {
        log.info("REST:  List Of Students : getAll() ");
        return ResponseEntity.ok(
                new DataDTO<>(service.getAll()));
    }


    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @GetMapping(value = PATH + "/students/pagination")
    public ResponseEntity<DataDTO<List<StudentDTO>>> getAllByCriteria(@RequestParam("size") Integer size,
                                                                      @RequestParam("page") Integer page) {
        log.info("REST:  List Of Student : getAllByCriteria() ");
        StudentCriteria criteria = new StudentCriteria();
        criteria.setSize(size);
        criteria.setPage(page);
        return ResponseEntity.ok(
                new DataDTO<>(service.getAllByCriteria(criteria)));
    }


    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @GetMapping(value = PATH + "/student/{id}")
    public ResponseEntity<DataDTO<StudentDTO>> get(@PathVariable(name = "id") Long id) {
        log.info("REST:  Student : get() ");
        return ResponseEntity.ok(
                new DataDTO<>(service.get(id)));
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @DeleteMapping(value = PATH + "/student/{id}")
    public ResponseEntity<DataDTO<Long>> delete(@PathVariable(name = "id") Long id) {
        log.info("REST:  Delete User by : {}  ", id);
        return ResponseEntity.ok(
                new DataDTO<>(service.delete(id)));
    }

}
