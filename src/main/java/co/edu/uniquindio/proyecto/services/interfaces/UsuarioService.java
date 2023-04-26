package co.edu.uniquindio.proyecto.services.interfaces;

import co.edu.uniquindio.proyecto.dto.usuario.UsuarioDTO;
import co.edu.uniquindio.proyecto.dto.usuario.UsuarioResponseDTO;
import co.edu.uniquindio.proyecto.modelo.Usuario;

import java.util.List;

public interface UsuarioService {

    int CreateUser(UsuarioDTO usuarioDTO) throws Exception;

    UsuarioResponseDTO updateUser(int codigoUsuario,UsuarioDTO usuarioDTO) throws Exception;

    UsuarioResponseDTO getUser(int codigoUsuario) throws Exception;

    Boolean deleteUser( int codigoUsuario) throws Exception;

    public Usuario obtener(int codigoUsuario) throws Exception;

    List<UsuarioResponseDTO> listarUsuarios ();

    String obtenerCorreoPorID(int usuario);


}
