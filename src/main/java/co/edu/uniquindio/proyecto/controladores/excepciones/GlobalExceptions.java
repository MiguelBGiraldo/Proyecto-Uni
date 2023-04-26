package co.edu.uniquindio.proyecto.controladores.excepciones;


import co.edu.uniquindio.proyecto.dto.Mensaje.MensajeDTO;
import co.edu.uniquindio.proyecto.execpciones.AttributeException;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptions {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<MensajeDTO> badCredentialsException(BadCredentialsException e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body( new MensajeDTO(HttpStatus.UNAUTHORIZED,true,"Datos de autenticacion incorrectos"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MensajeDTO> generalException(Exception e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body( new MensajeDTO(HttpStatus.INTERNAL_SERVER_ERROR,true,e.getMessage()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<MensajeDTO> accessDeniedException(AccessDeniedException accessDeniedException){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body( new MensajeDTO(HttpStatus.FORBIDDEN,true,"No se puede acceder a este recurso"));
    }

    @ExceptionHandler(AttributeException.class)
    public ResponseEntity<MensajeDTO> throwAttributeException(AttributeException e){
        return ResponseEntity.badRequest().body( new MensajeDTO(HttpStatus.BAD_REQUEST,true,e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MensajeDTO> validationException(MethodArgumentNotValidException ex){

        List<String> messages = new ArrayList<>();

        BindingResult results = ex.getBindingResult();
        for (FieldError e: results.getFieldErrors()){
            messages.add(e.getField() + ": " + e.getDefaultMessage() );
        }
        return ResponseEntity.badRequest().body( new MensajeDTO(HttpStatus.BAD_REQUEST,true,messages.toString()));
    }






}
