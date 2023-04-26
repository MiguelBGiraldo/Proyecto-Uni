package co.edu.uniquindio.proyecto.controladores;


import co.edu.uniquindio.proyecto.dto.Mensaje.MensajeDTO;
import co.edu.uniquindio.proyecto.services.implementacion.CloudinaryServicioImpl;
import co.edu.uniquindio.proyecto.services.interfaces.CloudinaryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;

@RestController
@RequestMapping("api/imagenes")
@AllArgsConstructor
public class ImagenesController {

    private CloudinaryService cloudinaryService;

    @PostMapping("/upload")
    public ResponseEntity<MensajeDTO> subirImagen(@RequestParam("file")MultipartFile file) throws Exception{
        instancia();
        File imagen = cloudinaryService.convertir(file);
        Map respuesta = cloudinaryService.subirImagen(imagen,"proyecto");
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK,false,respuesta));
    }

    public ResponseEntity<MensajeDTO> eliminarImagen(@PathVariable String id) throws Exception{
        instancia();
        Map respuesta = cloudinaryService.eliminarImagen(id);
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK,false,respuesta));
    }


    private void instancia(){
        cloudinaryService = new CloudinaryServicioImpl();
    }
}
