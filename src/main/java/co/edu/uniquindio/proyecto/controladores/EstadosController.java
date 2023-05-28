package co.edu.uniquindio.proyecto.controladores;


import co.edu.uniquindio.proyecto.dto.Mensaje.MensajeDTO;
import co.edu.uniquindio.proyecto.modelo.Estado;
import co.edu.uniquindio.proyecto.services.interfaces.CategoriaService;
import co.edu.uniquindio.proyecto.services.interfaces.EstadoService;
import co.edu.uniquindio.proyecto.services.interfaces.ProductoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/estados")
public class EstadosController {

    private final EstadoService estadoService ;
    private final ProductoService productoService;
    @GetMapping("/listar")
    public ResponseEntity<MensajeDTO> listar(){
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK,false,estadoService.obtenerEstados()));
    }

    @GetMapping("/listarEstado/{estado}")
    public ResponseEntity<MensajeDTO> listarProductosEstado(@PathVariable Estado estado) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK,false,productoService.listarProductosPorEstado(estado)));
    }
}
