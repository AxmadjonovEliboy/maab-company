package uz.boom.core_project_jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.boom.core_project_jwt.entity.AuthUser;
import uz.boom.core_project_jwt.repository.base.BaseRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Jarvis on Sat 11:15. 08/04/23
 */
@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, Long>, BaseRepository {

    Optional<AuthUser> findByEmail(String email);

    Optional<AuthUser> findByPhoneNumberOrEmail(String phoneNumber, String email);

    @Query(value = "select * from auth_user where auth_user.deleted != '1'" +
            " order by auth_user.id LIMIT :size OFFSET ( :size * :page - :size ) ", nativeQuery = true)
    Optional<List<AuthUser>> findAuthUserByCriteria(@Param("size") Integer limit,@Param("page") Integer page);

}
