package co.edu.uniquindio.proyecto.dto.Moderador;

import co.edu.uniquindio.proyecto.modelo.Estado;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class ModeradorDTO {

    private String motivo;

    private Estado estado;

    private int cedula_moderador;

    private int codigo_producto;
}
