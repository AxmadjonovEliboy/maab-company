package uz.boom.core_project_jwt.service.base;

import uz.boom.core_project_jwt.criteria.StudentCriteria;
import uz.boom.core_project_jwt.dto.student.StudentDTO;
import uz.boom.core_project_jwt.dto.student.StudentRegisterDTO;

import java.util.List;

/**
 * @author Jarvis on Fri 10:26. 28/04/23
 */
public interface StudentService extends BaseService {


    Long register(StudentRegisterDTO dto);

    List<StudentDTO> getAll();

    List<StudentDTO> getAllByCriteria(StudentCriteria criteria);

    StudentDTO get(Long id);

    Long delete(Long id);

}
