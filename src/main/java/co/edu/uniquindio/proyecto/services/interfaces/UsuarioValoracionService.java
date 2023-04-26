package co.edu.uniquindio.proyecto.services.interfaces;


import co.edu.uniquindio.proyecto.dto.UsuarioValoracion.ValoracionDTO;
import co.edu.uniquindio.proyecto.dto.UsuarioValoracion.ValoracionResponseDTO;

import java.util.List;

public interface UsuarioValoracionService {

    boolean guardarValoracion(ValoracionDTO valoracionDTO) throws Exception;

    List<ValoracionResponseDTO> obtenerValoraciones(int codigoUsuario) throws Exception;

    double obtenerPromedio(int codigoUsuario) throws Exception;
}
