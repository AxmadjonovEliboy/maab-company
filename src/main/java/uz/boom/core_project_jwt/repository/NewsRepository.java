package uz.boom.core_project_jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.boom.core_project_jwt.entity.AuthUser;
import uz.boom.core_project_jwt.entity.News;
import uz.boom.core_project_jwt.repository.base.BaseRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Jarvis on Fri 10:30. 28/04/23
 */
@Repository
public interface NewsRepository extends JpaRepository<News, Long>, BaseRepository {


    @Query(value = "select * from news " +
            " order by news.id LIMIT :size OFFSET ( :size * :page - :size ) ", nativeQuery = true)
    Optional<List<News>> findNewsByCriteria(@Param("size") Integer limit, @Param("page") Integer page);


}
