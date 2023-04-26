package co.edu.uniquindio.proyecto.services.implementacion;


import co.edu.uniquindio.proyecto.dto.Email.EmailDTO;
import co.edu.uniquindio.proyecto.dto.comentario.ComentarioDTO;
import co.edu.uniquindio.proyecto.dto.comentario.ComentarioResponseDTO;
import co.edu.uniquindio.proyecto.dto.producto.ProductoDTO;
import co.edu.uniquindio.proyecto.dto.producto.ProductoResponseDTO;
import co.edu.uniquindio.proyecto.modelo.Comentario;
import co.edu.uniquindio.proyecto.modelo.Producto;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import co.edu.uniquindio.proyecto.services.interfaces.ComentarioService;
import co.edu.uniquindio.proyecto.services.interfaces.EmailService;
import co.edu.uniquindio.proyecto.services.interfaces.ProductoService;
import co.edu.uniquindio.proyecto.services.interfaces.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ComentarioServicioImpl implements ComentarioService {



    private final UsuarioService usuarioService;
    private final ProductoService productoService;

    private final ComentarioRepo comentarioRepo;

    private final EmailService emailService;

    public int createComentarios(ComentarioDTO comentarioDTO) throws Exception {

       Comentario comentario = convertir(comentarioDTO);

       String email = usuarioService.obtenerCorreoPorID(productoService.obtenerUsuario(comentarioDTO.getCodigoProducto()));

        System.out.println(email);
        //Dice que no puedo enviar mas correos
        //EmailDTO emailDTO = new EmailDTO();
        //emailDTO.setDestinatario(email);
        //emailDTO.setCuerpo("Se ha realizado un comentario al producto " + comentarioDTO.getCodigoProducto());
        //emailDTO.setAsunto("Comentario");
        //emailService.enviarEmail(emailDTO);

       return  comentarioRepo.save(comentario).getCodigo();
    }

    public List<ComentarioResponseDTO> comentariosList(int codigoProducto) throws Exception {

        productoService.obtenerProducto(codigoProducto);
        System.out.println("Hola");
        List<Comentario> lista = comentarioRepo.listarComentariosProducto(codigoProducto);

        List<ComentarioResponseDTO> respuesta = new ArrayList<>();
        for(Comentario c: lista){
            respuesta.add(convertir(c));
        }

        return respuesta;
    }

    private Comentario convertir(ComentarioDTO comentarioDTO) throws Exception{
        Comentario comentario = new Comentario();
        comentario.setContenido(comentarioDTO.getMensaje());
        comentario.setCodigoProducto(productoService.obtenerProducto(comentarioDTO.getCodigoProducto()));
        comentario.setCodigoUsuario(usuarioService.obtener(comentarioDTO.getCodigoUsuario()));
        comentario.setFecha(LocalDateTime.now());

        return comentario;
    }

    private  ComentarioResponseDTO convertir(Comentario comentario){


        ComentarioResponseDTO comentarioDTO = new ComentarioResponseDTO();
        comentarioDTO.setCodigo(comentario.getCodigo());
        comentarioDTO.setMensaje(comentario.getContenido());
        comentarioDTO.setFecha(comentario.getFecha());
        comentarioDTO.setCodigoProducto(comentario.getCodigoProducto().getCodigo());
        comentarioDTO.setCodigoUsuario(comentario.getCodigoUsuario().getCedula());
        return comentarioDTO;
    }
}
