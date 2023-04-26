package co.edu.uniquindio.proyecto.controladores;


import co.edu.uniquindio.proyecto.dto.Mensaje.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.comentario.ComentarioDTO;
import co.edu.uniquindio.proyecto.dto.producto.ProductoDTO;
import co.edu.uniquindio.proyecto.services.implementacion.ComentarioServicioImpl;
import co.edu.uniquindio.proyecto.services.interfaces.ComentarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/comentarios")
public class ComentarioController {

    private final ComentarioService comentarioServicio;
    @PostMapping("/crear")
    public ResponseEntity<MensajeDTO> registrarProducto(@Valid @RequestBody ComentarioDTO comentarioDTO) throws Exception{
        comentarioServicio.createComentarios(comentarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(HttpStatus.CREATED,false,"comentario agregado exitosamente"));
    }

    @GetMapping("/listar/{codigo}")
    public ResponseEntity<MensajeDTO> listarProductos(@Valid @PathVariable int codigo) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK,false,comentarioServicio.comentariosList(codigo)));
    }

}
