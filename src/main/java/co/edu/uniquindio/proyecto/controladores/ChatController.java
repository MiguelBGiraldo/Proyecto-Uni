package co.edu.uniquindio.proyecto.controladores;


import co.edu.uniquindio.proyecto.dto.Mensaje.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.chat.ChatDTO;
import co.edu.uniquindio.proyecto.dto.mensajeChat.MensajeChatDTO;
import co.edu.uniquindio.proyecto.dto.producto.ProductoDTO;
import co.edu.uniquindio.proyecto.services.interfaces.ChatService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/chat")
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/crear")
    public ResponseEntity<MensajeDTO> registrarChat(@Valid @RequestBody ChatDTO chatDTO) throws Exception{
        chatService.crearChat(chatDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(HttpStatus.CREATED,false,"Chat creado correctamente"));
    }
    @GetMapping("/listar/{codigo}")
    public ResponseEntity<MensajeDTO> listarChats(@Valid @PathVariable int codigo) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(HttpStatus.CREATED,false,chatService.listarChats(codigo)));
    }

    @GetMapping("/listarMensaje/{codigo}")
    public ResponseEntity<MensajeDTO> listarMensajes(@Valid @PathVariable int codigo) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK,false,chatService.abrirMensajesChat(codigo)));
    }

    @PostMapping ("/enviarMensaje")
    public ResponseEntity<MensajeDTO> enviarMensaje(@Valid @RequestBody MensajeChatDTO mensajeChatDTO) throws Exception{
        chatService.enviarMensaje(mensajeChatDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(HttpStatus.CREATED,false,"El mensaje se envio correctamente"));
    }


    @DeleteMapping("/eliminarChat/{codigo}")
    public ResponseEntity<MensajeDTO> eliminarChat(@Valid @PathVariable int codigo) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK,false,chatService.eliminarChat(codigo)));
    }

}
