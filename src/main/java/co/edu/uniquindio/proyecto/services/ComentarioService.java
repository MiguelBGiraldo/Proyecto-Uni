package co.edu.uniquindio.proyecto.services;

import co.edu.uniquindio.proyecto.dto.comentario.ComentarioDTO;
import co.edu.uniquindio.proyecto.dto.comentario.ComentarioResponseDTO;

import java.util.List;

public interface ComentarioService {

    int createComentarios(ComentarioDTO comentarioDTO);

    List<ComentarioResponseDTO> comentariosList(int codigoProducto);
}
