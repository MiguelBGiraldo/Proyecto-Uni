package co.edu.uniquindio.proyecto.controladores;


import co.edu.uniquindio.proyecto.dto.Mensaje.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.usuario.UsuarioDTO;
import co.edu.uniquindio.proyecto.dto.usuario.UsuarioResponseDTO;
import co.edu.uniquindio.proyecto.services.interfaces.UsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/usuarios")
public class UsuarioController {

    @Autowired
    private final UsuarioService usuarioService;

    @GetMapping("/{codigo}")
    public ResponseEntity<MensajeDTO> obtener(@PathVariable int codigo) throws Exception{

        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK,false,usuarioService.getUser(codigo)));
    }
    @DeleteMapping("/{codigo}")
    public ResponseEntity<MensajeDTO> eliminar(@PathVariable int codigo) throws Exception {
        usuarioService.deleteUser(codigo);
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK,false,"Eliminado correctamente"));
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<MensajeDTO> actualizar(@PathVariable int codigo, @Valid @RequestBody UsuarioDTO usuario) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK,false,usuarioService.updateUser(codigo,usuario)));
    }


}
