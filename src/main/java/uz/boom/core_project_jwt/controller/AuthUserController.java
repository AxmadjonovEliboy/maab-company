package uz.boom.core_project_jwt.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.boom.core_project_jwt.controller.base.AbstractController;
import uz.boom.core_project_jwt.criteria.AuthCriteria;
import uz.boom.core_project_jwt.dto.auth.*;
import uz.boom.core_project_jwt.response.DataDTO;
import uz.boom.core_project_jwt.service.AuthUserServiceImpl;

import java.io.IOException;
import java.util.List;


/**
 * @author Jarvis on Sat 11:14. 08/04/23
 */
@RestController
@Slf4j
public class AuthUserController extends AbstractController<AuthUserServiceImpl> {

    public AuthUserController(AuthUserServiceImpl service) {
        super(service);
    }

    @PostMapping(value = PATH + "/auth/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DataDTO<SessionDTO>> login(@RequestBody AuthLoginDTO dto) {
        log.info("REST:  AuthLoginDTO : {}  ", dto);
        return ResponseEntity.ok(
                new DataDTO<>(service.login(dto)));
    }

    @GetMapping(value = PATH + "/refresh/token")
    public ResponseEntity<DataDTO<SessionDTO>> refreshToken(HttpServletRequest request){
        return ResponseEntity.ok(
                new DataDTO<>(service.refreshToken(request)));

    }


    @PostMapping(value = PATH + "/auth/create")
    public ResponseEntity<DataDTO<Long>> create(@RequestBody AuthUserCreateDTO dto) {
        log.info("REST:  AuthUserCreateDTO : {}  ", dto);
        return ResponseEntity.ok(
                new DataDTO<>(service.create(dto)));
    }


    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @GetMapping(value = PATH + "/auth/users")
    public ResponseEntity<DataDTO<List<AuthUserDTO>>> getAll() {
        log.info("REST:  List Of Users : getAll() ");
        return ResponseEntity.ok(
                new DataDTO<>(service.getAll()));
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PostMapping(value = PATH + "/auth/pagination")
    public ResponseEntity<DataDTO<List<AuthUserDTO>>> getAllByCriteria(@RequestParam("size") Integer size,
                                                                       @RequestParam("page") Integer page) {
        log.info("REST: List of user by AuthCriteria : size: {}; page: {}  ", size, page);
        AuthCriteria criteria = new AuthCriteria();
        criteria.setSize(size);
        criteria.setPage(page);
        return ResponseEntity.ok(
                new DataDTO<>(service.getAllByCriteria(criteria)));
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @GetMapping(value = PATH + "/auth/{id}")
    public ResponseEntity<DataDTO<AuthUserDTO>> get(@PathVariable(name = "id") Long userId) {
        log.info("REST:  Get User by id : {}  ", userId);
        return ResponseEntity.ok(
                new DataDTO<>(service.get(userId)));
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @PutMapping(value = PATH + "/auth")
    public ResponseEntity<DataDTO<Long>> update(@RequestBody AuthUserUpdateDTO dto) {
        log.info("REST:  AuthUserUpdate : {}  ", dto);
        return ResponseEntity.ok(
                new DataDTO<>(service.update(dto)));
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @DeleteMapping(value = PATH + "/auth/{id}")
    public ResponseEntity<DataDTO<Long>> delete(@PathVariable(name = "id") Long id) {
        log.info("REST:  Delete User by : {}  ", id);
        return ResponseEntity.ok(
                new DataDTO<>(service.delete(id)));
    }
}
