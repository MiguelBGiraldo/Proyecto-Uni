package co.edu.uniquindio.proyecto.dto.chat;


import co.edu.uniquindio.proyecto.dto.mensajeChat.MensajeChatResponseDTO;
import co.edu.uniquindio.proyecto.modelo.Mensaje;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ChatDTOResponse {


    private int codigo;

    private int codigoReceptor;

    private int codigoEmisor;

    private int codigoProducto;

    private List<MensajeChatResponseDTO> mensajes;
}
