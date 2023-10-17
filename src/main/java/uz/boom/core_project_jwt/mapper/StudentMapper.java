package uz.boom.core_project_jwt.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;
import uz.boom.core_project_jwt.dto.student.StudentDTO;
import uz.boom.core_project_jwt.dto.student.StudentRegisterDTO;
import uz.boom.core_project_jwt.entity.Student;
import uz.boom.core_project_jwt.mapper.base.BaseMapper;

import java.util.List;

/**
 * @author Jarvis on Fri 10:25. 28/04/23
 */
@Component
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface StudentMapper extends BaseMapper {


    List<StudentDTO> toDTO(List<Student> students);

    StudentDTO toDTO(Student student);

    Student fromRegisterDTO(StudentRegisterDTO registerDto);

    Student fromUpdateDTO(StudentDTO updateDTO, @MappingTarget Student target);

}
