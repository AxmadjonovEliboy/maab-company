package uz.boom.core_project_jwt.service.base;

import uz.boom.core_project_jwt.criteria.AuthCriteria;
import uz.boom.core_project_jwt.dto.auth.*;

import java.util.List;

/**
 * @author Jarvis on Mon 16:10. 10/04/23
 */
public interface AuthUserService extends BaseService {

    /**
     * Get SessionDto
     *
     * @param dto AuthLoginDto
     * @return DataDto
     * @throws RuntimeException if login is wrong
     * @apiNote security config written  3.0.0 v
     */
    SessionDTO login(AuthLoginDTO dto);


    /**
     * Get Long
     *
     * @param dto AuthUserCreateDTO
     * @return DataDto
     * @throws RuntimeException if email is exist
     * @apiNote security config written  3.0.0 v
     */
    Long create(AuthUserCreateDTO dto);



    /**
     * Get AuthUserDto
     *
     * @return DataDto
     * @throws RuntimeException if there is no token
     * @apiNote security config written  3.0.0 v
     */
    List<AuthUserDTO> getAll();

    List<AuthUserDTO> getAllByCriteria(AuthCriteria criteria);


    AuthUserDTO get(Long id);

    Long update(AuthUserUpdateDTO dto);


    Long delete(Long id);


//    /**
//     * Get Long
//     *
//     * @param dto AuthRegisterDto
//     * @return DataDto
//     * @throws RuntimeException if email is exist
//     * @apiNote security config written  3.0.0 v
//     */
//    Long register(AuthRegisterDTO dto);


}
