package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.Mensaje.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.Moderador.ModeradorDTO;
import co.edu.uniquindio.proyecto.dto.producto.ProductoDTO;
import co.edu.uniquindio.proyecto.repositorios.ProductoModeradorRepo;
import co.edu.uniquindio.proyecto.services.interfaces.ModeradorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/moderador")
public class ModeradorController {


    private final ModeradorService moderadorService;

    @PostMapping("/registro")
    public ResponseEntity<MensajeDTO> registrarAccion(@Valid @RequestBody ModeradorDTO moderadorDTO) throws Exception{
        moderadorService.registrarActividad(moderadorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(HttpStatus.CREATED,false,"Acción realizada correctamente"));
    }
    @GetMapping("/productos")
    public ResponseEntity<MensajeDTO> registrarObtenerProductos() throws Exception{

        return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(HttpStatus.CREATED,false,moderadorService.listarProductos()));
    }

    @GetMapping("/usuarios")
    public ResponseEntity<MensajeDTO> registrarObtenerUsuarios() throws Exception{

        return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(HttpStatus.CREATED,false,moderadorService.listarUsuarios()));
    }


}
