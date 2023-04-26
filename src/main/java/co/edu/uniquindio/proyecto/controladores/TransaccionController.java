package co.edu.uniquindio.proyecto.controladores;


import co.edu.uniquindio.proyecto.dto.Mensaje.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.Transaccion.TransaccionDTO;
import co.edu.uniquindio.proyecto.dto.producto.ProductoDTO;
import co.edu.uniquindio.proyecto.services.interfaces.TransaccionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/transaccion")
public class TransaccionController {

    private final TransaccionService transaccionService;
    @PostMapping("/registro")
    public ResponseEntity<MensajeDTO> registrarCompra(@Valid @RequestBody TransaccionDTO transaccionDTO) throws Exception{
        transaccionService.creteCompra(transaccionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(HttpStatus.CREATED,false,"Compra realizada con éxisto"));
    }

    @GetMapping("/lista/{codigoUsuario}")
    public ResponseEntity<MensajeDTO> obtenerCompras(@Valid @PathVariable int codigoUsuario) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK,false,transaccionService.usarListCompra(codigoUsuario)));
    }

    @GetMapping("/obtener/{codigo}")
    public ResponseEntity<MensajeDTO> obtenerCompra(@Valid @PathVariable int codigo) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK,false,transaccionService.obtenerDetalleTransaccion(codigo)));
    }
}
