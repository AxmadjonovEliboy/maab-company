package uz.boom.core_project_jwt.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;
import uz.boom.core_project_jwt.dto.auth.AuthRegisterDTO;
import uz.boom.core_project_jwt.dto.auth.AuthUserCreateDTO;
import uz.boom.core_project_jwt.dto.auth.AuthUserDTO;
import uz.boom.core_project_jwt.dto.auth.AuthUserUpdateDTO;
import uz.boom.core_project_jwt.entity.AuthUser;
import uz.boom.core_project_jwt.mapper.base.BaseMapper;

import java.util.List;

/**
 * @author Jarvis on Sat 11:14. 08/04/23
 */
@Component
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AuthUserMapper extends BaseMapper {

    List<AuthUserDTO> toDTO(List<AuthUser> users);

    AuthUserDTO toDTO(AuthUser user);

    AuthUser fromCreateDTO(AuthUserCreateDTO createDTO);

    AuthUser fromRegisterDTO(AuthRegisterDTO registerDto);

    AuthUser fromUpdateDTO(AuthUserUpdateDTO updateDTO, @MappingTarget AuthUser target);

}
