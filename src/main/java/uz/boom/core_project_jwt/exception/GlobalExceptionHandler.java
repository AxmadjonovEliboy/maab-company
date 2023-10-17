package uz.boom.core_project_jwt.exception;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import uz.boom.core_project_jwt.response.AppErrorDTO;
import uz.boom.core_project_jwt.response.DataDTO;

import java.sql.SQLException;

/**
 * @author Jarvis on Tue 10:57. 11/04/23
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  @NonNull HttpHeaders headers,
                                                                  @NonNull HttpStatusCode status,
                                                                  @NonNull WebRequest request) {
        StringBuilder message = new StringBuilder();

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            message.append("Cause : -> ").append(fieldError.getDefaultMessage()).append("\n");
        }
        AppErrorDTO appErrorDto = new AppErrorDTO(message.toString(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(new DataDTO<>(appErrorDto), headers, status);

    }

    @ExceptionHandler(value = {SQLException.class, RuntimeException.class})
    public ResponseEntity<DataDTO<AppErrorDTO>> handle500(RuntimeException e, WebRequest request) {
        return new ResponseEntity<>
                (new DataDTO<>(new AppErrorDTO(e.getMessage(), request, HttpStatus.INTERNAL_SERVER_ERROR)),HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<DataDTO<AppErrorDTO>> handle400(BadRequestException e, WebRequest request) {
        return new ResponseEntity<>
                (new DataDTO<>(new AppErrorDTO(e.getMessage(), request, HttpStatus.BAD_REQUEST)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<DataDTO<AppErrorDTO>> handle404(NotFoundException e, WebRequest request) {
        return new ResponseEntity<>
                (new DataDTO<>(new AppErrorDTO(e.getMessage(), request, HttpStatus.BAD_REQUEST)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ValidatorException.class})
    public ResponseEntity<DataDTO<AppErrorDTO>> handle401(ValidatorException e, WebRequest request) {
        return new ResponseEntity<>
                (new DataDTO<>(new AppErrorDTO(e.getMessage(), request, HttpStatus.BAD_REQUEST)), HttpStatus.BAD_REQUEST);
    }

}
