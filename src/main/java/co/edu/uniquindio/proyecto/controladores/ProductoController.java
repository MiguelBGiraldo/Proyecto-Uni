package co.edu.uniquindio.proyecto.controladores;


import co.edu.uniquindio.proyecto.dto.Mensaje.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.producto.ProductoDTO;
import co.edu.uniquindio.proyecto.dto.usuario.UsuarioDTO;
import co.edu.uniquindio.proyecto.modelo.Categoria;
import co.edu.uniquindio.proyecto.modelo.Estado;
import co.edu.uniquindio.proyecto.services.interfaces.ProductoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/productos")
public class ProductoController {

    private final ProductoService productoService;

    @PostMapping("/registro")
    public ResponseEntity<MensajeDTO> registrarProducto(@Valid @RequestBody ProductoDTO productoDTO) throws Exception{
        productoService.createProducto(productoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(HttpStatus.CREATED,false,"Producto creado correctamente"));
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<MensajeDTO> actualizarProducto(@PathVariable int codigo, @Valid @RequestBody ProductoDTO producto) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK,false,productoService.updateProducto(codigo,producto)));
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<MensajeDTO> eliminarProducto(@PathVariable int codigo) throws Exception {
        productoService.deleteProducto(codigo);
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK,false,"Eliminado correctamente"));
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<MensajeDTO> obtenerProducto(@PathVariable int codigo) throws Exception{

        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK,false,productoService.getProduct(codigo)));
    }

    @PutMapping("/actualizar/{codigo}/{unidades}")
    public ResponseEntity<MensajeDTO> actualizarProductoCantidad(@PathVariable int codigo, @Valid @PathVariable int unidades) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK,false,productoService.updateByAmount(codigo,unidades)));
    }

    @PutMapping("/actEstado/{codigo}/{estado}")
    public ResponseEntity<MensajeDTO> actualizarProductoEstado(@PathVariable int codigo, @Valid @PathVariable Estado estado) throws Exception{
        System.out.println("Estado: " + estado);
        productoService.actualizarEstado(codigo,estado);
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK,false,"Se actualizó el estado correctamente"));
    }

    @GetMapping("/obtenerDetalle/{codigo}")
    public ResponseEntity<MensajeDTO> obtenerProductoDetalle(@PathVariable int codigo) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK,false,productoService.listarProductoDetalle(codigo)));
    }

    @GetMapping("/listarCategoria/{categoria}")
    public ResponseEntity<MensajeDTO> listarProductosCategoria(@PathVariable Categoria categoria) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK,false,productoService.listarProductosCategoria(categoria)));
    }

    @GetMapping("/listarEstado/{estado}")
    public ResponseEntity<MensajeDTO> listarProductosEstado(@PathVariable Estado estado) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK,false,productoService.listarProductosPorEstado(estado)));
    }

    @GetMapping("/favoritos/{codigo}")
    public ResponseEntity<MensajeDTO> listarProductosFavoritos(@PathVariable int codigo) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(new co.edu.uniquindio.proyecto.dto.Mensaje.MensajeDTO(HttpStatus.OK,false,productoService.listarProductosFavoritos(codigo)));
    }

    @GetMapping("/usuario/{codigo}")
    public ResponseEntity<MensajeDTO> listarProductosUsuarios(@PathVariable int codigo) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(new co.edu.uniquindio.proyecto.dto.Mensaje.MensajeDTO(HttpStatus.OK,false,productoService.listarProductosUsuario(codigo)));
    }

    @GetMapping("/guardarFavorito/{codigoUsuario}/{codigo}")
    public ResponseEntity<MensajeDTO> guardarProductoFavorito(@PathVariable int codigoUsuario,@PathVariable int codigo) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(new co.edu.uniquindio.proyecto.dto.Mensaje.MensajeDTO(HttpStatus.OK,false,productoService.guardarProductoFavorito(codigoUsuario,codigo)));
    }

    @DeleteMapping("/eliminarFavorito/{codigo}/{codigoUsuario}")
    public ResponseEntity<MensajeDTO> eliminarProductoFavorito(@PathVariable int codigo, @PathVariable int codigoUsuario) throws Exception {
        productoService.eliminarProductoFavorito(codigoUsuario,codigo);
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK,false,"Eliminado correctamente"));
    }

    @GetMapping("/nombre/{name}")
    public ResponseEntity<MensajeDTO> listarProductosNombre(@PathVariable String name) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(new co.edu.uniquindio.proyecto.dto.Mensaje.MensajeDTO(HttpStatus.OK,false,productoService.listarProductosNombre(name)));
    }

    @GetMapping("/precio/{minimo}/{maximo}")
    public ResponseEntity<MensajeDTO> listarProductosPrecio(@PathVariable float minimo,@PathVariable float maximo) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(new co.edu.uniquindio.proyecto.dto.Mensaje.MensajeDTO(HttpStatus.OK,false,productoService.listarProductosPrecio(minimo,maximo)));
    }


}
