package co.edu.uniquindio.proyecto.services.interfaces;

import co.edu.uniquindio.proyecto.dto.comentario.ComentarioDTO;
import co.edu.uniquindio.proyecto.dto.comentario.ComentarioResponseDTO;

import java.util.List;

public interface ComentarioService {

    int createComentarios(ComentarioDTO comentarioDTO) throws Exception;

    List<ComentarioResponseDTO> comentariosList(int codigoProducto) throws Exception;
}
