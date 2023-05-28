package co.edu.uniquindio.proyecto.controladores;


import co.edu.uniquindio.proyecto.dto.Mensaje.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.sesion.SesionDTO;
import co.edu.uniquindio.proyecto.services.interfaces.CategoriaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/categorias")
public class CategoriasController {

    private final CategoriaService categoriaService ;
    @GetMapping("/listar")
    public ResponseEntity<MensajeDTO> listar(){
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK,false,categoriaService.obtenerCategorias()));
    }
}
