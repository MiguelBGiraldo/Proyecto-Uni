package co.edu.uniquindio.proyecto.services;

import co.edu.uniquindio.proyecto.dto.usuario.UsuarioDTO;
import co.edu.uniquindio.proyecto.dto.usuario.UsuarioResponseDTO;

public interface UsuarioService {

    int CreateUser(UsuarioDTO usuarioDTO);

    int updateUser(UsuarioDTO  usuarioDTO);

    UsuarioResponseDTO getUser(int codigoUsuario);
}
