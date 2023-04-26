package co.edu.uniquindio.proyecto.controladores;


import co.edu.uniquindio.proyecto.dto.Mensaje.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.sesion.SesionDTO;
import co.edu.uniquindio.proyecto.dto.usuario.UsuarioDTO;
import co.edu.uniquindio.proyecto.services.interfaces.SesionService;
import co.edu.uniquindio.proyecto.services.interfaces.UsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthController {

    private final UsuarioService usuarioService;
    private final SesionService sesionService;

    @PostMapping("/login")
    public ResponseEntity<MensajeDTO> login(@Valid @RequestBody SesionDTO loginUser){
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK,false,sesionService.login(loginUser)));
    }

    @PostMapping("/registro")
    public ResponseEntity<MensajeDTO> registrarCliente(@Valid @RequestBody UsuarioDTO usuario) throws Exception{
        usuarioService.CreateUser(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(HttpStatus.CREATED,false,"Cliente creado correctamente"));
    }
}
