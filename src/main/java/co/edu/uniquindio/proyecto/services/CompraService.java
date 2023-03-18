package co.edu.uniquindio.proyecto.services;

import co.edu.uniquindio.proyecto.dto.compra.CompraDTO;
import co.edu.uniquindio.proyecto.dto.compra.CompraResponseDTO;

import java.util.List;

public interface CompraService {

    int creteCompra(CompraDTO compraDTO);

    List<CompraResponseDTO> usarListCompra(int codigoUsuario);


}
