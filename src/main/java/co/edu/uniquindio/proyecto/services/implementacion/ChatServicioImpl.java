package co.edu.uniquindio.proyecto.services.implementacion;


import co.edu.uniquindio.proyecto.dto.chat.ChatDTO;
import co.edu.uniquindio.proyecto.dto.chat.ChatDTOResponse;
import co.edu.uniquindio.proyecto.dto.mensajeChat.MensajeChatDTO;
import co.edu.uniquindio.proyecto.dto.mensajeChat.MensajeChatResponseDTO;
import co.edu.uniquindio.proyecto.modelo.Chat;
import co.edu.uniquindio.proyecto.modelo.EstadoMensaje;
import co.edu.uniquindio.proyecto.modelo.Mensaje;
import co.edu.uniquindio.proyecto.repositorios.ChatRepo;
import co.edu.uniquindio.proyecto.repositorios.MensajeRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import co.edu.uniquindio.proyecto.services.interfaces.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class ChatServicioImpl implements ChatService {


    ChatRepo chatRepo;
    ProductoRepo productoRepo;
    UsuarioRepo usuarioRepo;

    MensajeRepo mensajeRepo;


    public Chat crearChat(ChatDTO chatDTO){

        Chat chat = new Chat();
        chat.setProducto(productoRepo.obtenerProducto(chatDTO.getCodigoProducto()));
        chat.setUsuario(usuarioRepo.buscarUsuarioByCedula(chatDTO.getCodigoReceptor()));
        chat.setUsuario2(usuarioRepo.buscarUsuarioByCedula(chatDTO.getCodigoEmisor()));

        return chatRepo.save(chat);
    }

    public List<ChatDTOResponse> listarChats(int codigoUsuario) throws Exception{

        List<Chat> listaChats = chatRepo.obtenerChats(codigoUsuario);

        List<ChatDTOResponse> chatsDTOS = new ArrayList<ChatDTOResponse>();
        for(Chat chat : listaChats){
            chatsDTOS.add(convertir(chat));
        }

        return chatsDTOS;
    }

    public List<MensajeChatResponseDTO> abrirMensajesChat(int codigoChat) throws Exception{

        List<Mensaje> mensajes = mensajeRepo.listarMensajesChat(codigoChat);
        List<MensajeChatResponseDTO> mensajesDTO = new ArrayList<>();
        for (Mensaje m : mensajes){
            if(m.getEstado() == EstadoMensaje.NOLEIDO){
                m.setEstado(EstadoMensaje.LEIDO);
                mensajeRepo.save(m);
            }
            mensajesDTO.add(convertirMensaje(m));
        }
        return mensajesDTO;
    }

    public boolean enviarMensaje(MensajeChatDTO mensajeChatDTO) throws Exception{

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje(mensajeChatDTO.getMensaje());
        mensaje.setChat(obtenerChat(mensajeChatDTO.getCodigoChat()));
        mensaje.setUsuario(usuarioRepo.buscarUsuarioByCedula(mensajeChatDTO.getCodigoUsuario()));
        mensaje.setFecha(LocalDateTime.now());
        mensaje.setEstado(EstadoMensaje.NOLEIDO);

        return mensajeRepo.save(mensaje) != null ? true : false;
    }

    public boolean eliminarChat(int codigoChat) throws Exception{

        Chat chat = obtenerChat(codigoChat);

        List<Mensaje> mensajes = chat.getMensaje();

        for (Mensaje m : mensajes ){
            mensajeRepo.delete(m);
        }

        //mensajeRepo.eliminarMensajes(codigoChat);


        chatRepo.delete(chat);

        return true;
    }

    public boolean verificarExisteChat(int codigoProducto, int receptor, int transmisor){

        Chat chat =  chatRepo.verificarChat(codigoProducto,receptor,transmisor);
        return chat == null ? true : false;
    }

    public Chat obtenerChat(int codigo) throws Exception{


        Chat chat =  chatRepo.obtenerChat(codigo);
        if(chat == null){
            throw new Exception("El código "+ codigo+" no está asociado a ningún chat");
        }

        return chat;
    }

    public ChatDTOResponse convertir(Chat chat){

        List<Mensaje> mensaje = chat.getMensaje();

        List<MensajeChatResponseDTO> mensajesDTO = new ArrayList<>();

        for(Mensaje m : mensaje){
            mensajesDTO.add(convertirMensaje(m));
        }

        ChatDTOResponse chatResp = new ChatDTOResponse(
          chat.getCodigo(),
          chat.getUsuario().getCedula(),
          chat.getUsuario2().getCedula(),
          chat.getProducto().getCodigo(),
          mensajesDTO
        );

        return chatResp;
    }

    public MensajeChatResponseDTO convertirMensaje(Mensaje mensaje){

        MensajeChatResponseDTO respuesta = new MensajeChatResponseDTO(
                mensaje.getCodigo(),
                mensaje.getChat().getCodigo(),
                mensaje.getFecha(),
                mensaje.getEstado(),
                mensaje.getMensaje(),
                mensaje.getUsuario().getCedula()
        );

        return respuesta;
    }

}
