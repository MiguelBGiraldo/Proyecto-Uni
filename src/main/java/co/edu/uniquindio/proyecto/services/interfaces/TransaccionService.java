package co.edu.uniquindio.proyecto.services.interfaces;

import co.edu.uniquindio.proyecto.dto.Transaccion.TransaccionDTO;
import co.edu.uniquindio.proyecto.dto.Transaccion.TransaccionResponseDTO;

import java.util.List;

public interface TransaccionService {


    boolean creteCompra(TransaccionDTO transaccionDTO) throws Exception;

    List<TransaccionResponseDTO> usarListCompra(int codigoUsuario) throws Exception;

    TransaccionResponseDTO obtenerDetalleTransaccion(int codigo) throws  Exception;


}
