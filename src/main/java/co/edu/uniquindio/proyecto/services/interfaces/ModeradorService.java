package co.edu.uniquindio.proyecto.services.interfaces;

import co.edu.uniquindio.proyecto.dto.Moderador.ModeradorDTO;
import co.edu.uniquindio.proyecto.dto.comentario.ComentarioDTO;
import co.edu.uniquindio.proyecto.dto.producto.ProductoResponseDTO;
import co.edu.uniquindio.proyecto.dto.usuario.UsuarioResponseDTO;
import co.edu.uniquindio.proyecto.modelo.Estado;
import co.edu.uniquindio.proyecto.modelo.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface ModeradorService {

    boolean  activarProducto(int codigoProducto) throws Exception;

    boolean rechazarProducto (int codigoProducto) throws Exception;

    boolean registrarActividad(ModeradorDTO moderadorDTO) throws Exception;

    List<ProductoResponseDTO> listarProductos();

    List<UsuarioResponseDTO> listarUsuarios();
}
