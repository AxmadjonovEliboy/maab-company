package uz.boom.core_project_jwt.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import uz.boom.core_project_jwt.criteria.StudentCriteria;
import uz.boom.core_project_jwt.dto.student.StudentDTO;
import uz.boom.core_project_jwt.dto.student.StudentRegisterDTO;
import uz.boom.core_project_jwt.entity.Student;
import uz.boom.core_project_jwt.exception.BadRequestException;
import uz.boom.core_project_jwt.exception.NotFoundException;
import uz.boom.core_project_jwt.mapper.StudentMapper;
import uz.boom.core_project_jwt.repository.StudentRepository;
import uz.boom.core_project_jwt.service.base.AbstractService;
import uz.boom.core_project_jwt.service.base.StudentService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Jarvis on Fri 10:27. 28/04/23
 */
@Service
public class StudentServiceImpl extends AbstractService<StudentRepository, StudentMapper> implements StudentService {

    public StudentServiceImpl(StudentRepository repository, @Qualifier("studentMapperImpl") StudentMapper mapper) {
        super(repository, mapper);

    }

    @Override
    public Long register(StudentRegisterDTO dto) {
        Optional<Student> savedAnyStudent = repository.findByPhoneNumberOrEmail(dto.getPhoneNumber(), dto.getEmail());
        if (savedAnyStudent.isPresent()) {
            throw new BadRequestException("THIS EMAIL OR PHONE NUMBER ALREADY EXIST");
        }
        Student student = mapper.fromRegisterDTO(dto);
        Student savedStudent = repository.save(student);
        return savedStudent.getId();
    }

    @Override
    public List<StudentDTO> getAll() {
        List<Student> savedStudents = repository.findAll();
        return mapper.toDTO(savedStudents);
    }

    @Override
    public List<StudentDTO> getAllByCriteria(StudentCriteria criteria) {
        List<Student> students = repository.findStudentByCriteria(
                        criteria.getSize(),
                        criteria.getPage())
                .orElseThrow(() -> new NotFoundException("STUDENT NOT FOUND !"));
        return mapper.toDTO(students);
    }

    @Override
    public StudentDTO get(Long id) {
        Student student = repository.findById(id).orElseThrow(() -> new NotFoundException("THERE IS NO STUDENT WITH THIS ID"));
        return mapper.toDTO(student);
    }

    @Override
    public Long delete(Long id) {
        Student student = repository.findById(id).orElseThrow(() -> new NotFoundException("THERE IS NO STUDENT WITH THIS ID"));
        student.setDeleted(Boolean.TRUE);
        String uuid = UUID.randomUUID().toString();
        String phoneNumber = student.getPhoneNumber() + uuid;
        String email = student.getEmail() + uuid;
        student.setPhoneNumber(phoneNumber);
        student.setEmail(email);
        Student saved = repository.save(student);
        return saved.getId();
    }
}
