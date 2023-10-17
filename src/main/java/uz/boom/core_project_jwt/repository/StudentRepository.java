package uz.boom.core_project_jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.boom.core_project_jwt.entity.News;
import uz.boom.core_project_jwt.entity.Student;
import uz.boom.core_project_jwt.repository.base.BaseRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Jarvis on Fri 10:22. 28/04/23
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, BaseRepository {

   Optional<Student> findByPhoneNumberOrEmail(String phoneNumber, String email);
//   select * from news" +
//           " order by news.id LIMIT :size OFFSET ( :size * :page - :size )
   @Query(value = "select * from student  where student.deleted != '1'" +
           " order by student.id LIMIT :size OFFSET ( :size * :page - :size ) ", nativeQuery = true)
   Optional<List<Student>> findStudentByCriteria(@Param("size") Integer limit, @Param("page") Integer page);




}
