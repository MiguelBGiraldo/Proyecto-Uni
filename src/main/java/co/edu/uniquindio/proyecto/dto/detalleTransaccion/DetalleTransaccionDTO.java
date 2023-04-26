package co.edu.uniquindio.proyecto.dto.detalleTransaccion;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DetalleTransaccionDTO {

    @NotBlank
    @NotNull
    private int codigoProducto;

    @NotNull
    @PositiveOrZero
    private double precio;

    @NotNull
    @PositiveOrZero
    private int unidades;
}
