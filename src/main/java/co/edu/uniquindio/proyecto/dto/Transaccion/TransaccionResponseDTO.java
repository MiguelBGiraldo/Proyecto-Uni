package co.edu.uniquindio.proyecto.dto.Transaccion;


import co.edu.uniquindio.proyecto.dto.detalleTransaccion.DetalleTransaccionDTO;
import co.edu.uniquindio.proyecto.modelo.MetodoPago;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class TransaccionResponseDTO {

    private int codigo;

    private LocalDateTime fecha;

    private double valorTotal;

    private int codigoUsuario;

    private MetodoPago metodoPago;

    private List<DetalleTransaccionDTO> detalleTransaccionDTO;
}
