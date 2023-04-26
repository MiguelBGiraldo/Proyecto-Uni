package co.edu.uniquindio.proyecto.dto.Transaccion;

import co.edu.uniquindio.proyecto.dto.detalleTransaccion.DetalleTransaccionDTO;
import co.edu.uniquindio.proyecto.modelo.MetodoPago;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TransaccionDTO {


    private int codigoUsuario;

    private LocalDateTime fecha;

    private double valorTotal;

    private MetodoPago metodoPago;
    private List<DetalleTransaccionDTO> detalleCompra;
}
