package co.edu.uniquindio.proyecto.services.interfaces;

import co.edu.uniquindio.proyecto.dto.chat.ChatDTO;
import co.edu.uniquindio.proyecto.dto.chat.ChatDTOResponse;
import co.edu.uniquindio.proyecto.dto.mensajeChat.MensajeChatDTO;
import co.edu.uniquindio.proyecto.dto.mensajeChat.MensajeChatResponseDTO;
import co.edu.uniquindio.proyecto.modelo.Chat;
import co.edu.uniquindio.proyecto.modelo.Mensaje;

import java.util.List;

public interface ChatService {

    List<ChatDTOResponse> listarChats(int codigoUsuario) throws Exception;

    List<MensajeChatResponseDTO> abrirMensajesChat(int codigoChat) throws Exception;

    boolean enviarMensaje(MensajeChatDTO mensajeChatDTO) throws Exception;

    boolean eliminarChat(int codigoChat) throws Exception;

    Chat crearChat(ChatDTO chat) throws  Exception;


}
