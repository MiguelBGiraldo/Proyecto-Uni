package co.edu.uniquindio.proyecto.controladores;


import co.edu.uniquindio.proyecto.dto.Mensaje.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.UsuarioValoracion.ValoracionDTO;
import co.edu.uniquindio.proyecto.services.interfaces.UsuarioValoracionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/valoracion")
public class UsuarioValoracionController {

    private final UsuarioValoracionService usuarioValoracion;

    @PostMapping("/crear")
    public ResponseEntity<MensajeDTO> registrarValoracion(@Valid @RequestBody ValoracionDTO valoracionDTO) throws Exception{
        usuarioValoracion.guardarValoracion(valoracionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(HttpStatus.CREATED,false,"Valoracion creada correctamente"));
    }

    @GetMapping("/obtener/{codigoUsuario}")
    public ResponseEntity<MensajeDTO> obtenerValoraciones(@Valid @PathVariable int codigoUsuario) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(HttpStatus.CREATED,false, usuarioValoracion.obtenerValoraciones(codigoUsuario)));
    }

    @GetMapping("/promedio/{codigoUsuario}")
    public ResponseEntity<MensajeDTO> obtenerPromedio(@Valid @PathVariable int codigoUsuario) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(HttpStatus.CREATED,false, usuarioValoracion.obtenerPromedio(codigoUsuario)));
    }


}
