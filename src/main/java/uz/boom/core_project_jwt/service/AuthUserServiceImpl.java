package uz.boom.core_project_jwt.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.boom.core_project_jwt.criteria.AuthCriteria;
import uz.boom.core_project_jwt.dto.auth.*;
import uz.boom.core_project_jwt.entity.AuthUser;
import uz.boom.core_project_jwt.enums.Role;
import uz.boom.core_project_jwt.exception.BadRequestException;
import uz.boom.core_project_jwt.exception.NotFoundException;
import uz.boom.core_project_jwt.mapper.AuthUserMapper;
import uz.boom.core_project_jwt.repository.AuthUserRepository;
import uz.boom.core_project_jwt.security.jwt.JwtService;
import uz.boom.core_project_jwt.service.base.AbstractService;
import uz.boom.core_project_jwt.service.base.AuthUserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * @author Jarvis on Sat 11:14. 08/04/23
 */
@Service
public class AuthUserServiceImpl extends AbstractService<AuthUserRepository, AuthUserMapper> implements AuthUserService {

    private final AuthenticationManager authenticationManager;
    private final AuthUserRepository repository;
    private final JwtService jwtService;
    private final PasswordEncoder encoder;


    public AuthUserServiceImpl(AuthUserRepository repository,
                               @Qualifier("authUserMapperImpl") AuthUserMapper mapper,
                               AuthenticationManager authenticationManager,
                               AuthUserRepository repository1,
                               JwtService jwtService, PasswordEncoder encoder) {
        super(repository, mapper);
        this.authenticationManager = authenticationManager;
        this.repository = repository1;
        this.jwtService = jwtService;
        this.encoder = encoder;
    }


    @Override
    public SessionDTO login(AuthLoginDTO dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getEmail(),
                        dto.getPassword()
                )
        );
        AuthUser user = repository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new NotFoundException("USER NOT FOUND !!!"));


        return jwtService.createSessionDto(user);
    }


    public SessionDTO refreshToken(HttpServletRequest request) {

        final String authorizationHeader = request.getHeader(AUTHORIZATION);
        final String userEmail;
        final String refresh_token;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            refresh_token = authorizationHeader.substring("Bearer ".length());
            userEmail = jwtService.extractUsername(refresh_token);
            AuthUser user = repository.findByEmail(userEmail)
                    .orElseThrow(() -> new NotFoundException("USER NOT FOUND !!!"));
            return jwtService.createSessionDto(user);

        } else {
            throw new RuntimeException("THERE IS A PROBLEM WITH THE REFRESH TOKEN");
        }
    }


    @Override
    public Long create(AuthUserCreateDTO dto) {
        Optional<AuthUser> savedAuthUser = repository.findByPhoneNumberOrEmail(dto.getPhoneNumber(), dto.getEmail());
        if (savedAuthUser.isPresent()) {
            throw new BadRequestException("THIS EMAIL OR PHONE NUMBER ALREADY EXIST");
        }
        AuthUser authUser = mapper.fromCreateDTO(dto);
        authUser.setRole(Role.ADMIN);
        authUser.setPassword(encoder.encode(dto.getPassword()));
        authUser.setStatus(Boolean.TRUE);
        AuthUser savedUser = repository.save(authUser);
        return savedUser.getId();
    }

    @Override
    public List<AuthUserDTO> getAll() {
        List<AuthUser> savedUsers = repository.findAll();
        return mapper.toDTO(savedUsers);
    }

    @Override
    public List<AuthUserDTO> getAllByCriteria(AuthCriteria criteria) {
        List<AuthUser> authUsers = repository.findAuthUserByCriteria(
                        criteria.getSize(),
                        criteria.getPage())
                .orElseThrow(() -> new NotFoundException("USER NOT FOUND !"));
        return mapper.toDTO(authUsers);
    }

    @Override
    public AuthUserDTO get(Long id) {
        AuthUser savedUser = repository.findById(id).orElseThrow(() -> new NotFoundException("USER NOT FOUND!"));
        return mapper.toDTO(savedUser);
    }

    @Override
    public Long update(AuthUserUpdateDTO dto) {
        AuthUser savedAuthUser = repository.findById(dto.getId()).orElseThrow(() -> new NotFoundException("USER NOT FOUND!"));
        AuthUser authUser = mapper.fromUpdateDTO(dto, savedAuthUser);
        AuthUser savedUser = repository.save(authUser);
        return savedUser.getId();
    }

    @Override
    public Long delete(Long id) {
        AuthUser authUser = repository.findById(id).orElseThrow(() -> new NotFoundException("USER NOT FOUND!"));
        authUser.setDeleted(Boolean.TRUE);
        String uuid = UUID.randomUUID().toString();
        String authUserEmail = authUser.getEmail() + uuid;
        String phoneNumber = authUser.getPhoneNumber() + uuid;
        authUser.setEmail(authUserEmail);
        authUser.setPhoneNumber(phoneNumber);
        AuthUser savedUser = repository.save(authUser);
        return savedUser.getId();
    }


}
