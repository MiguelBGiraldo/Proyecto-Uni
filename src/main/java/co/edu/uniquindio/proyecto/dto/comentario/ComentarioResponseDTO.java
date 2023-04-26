package co.edu.uniquindio.proyecto.dto.comentario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioResponseDTO {

    private int codigo;

    private LocalDateTime fecha;

    private String mensaje;

    private int codigoUsuario;

    private int codigoProducto;
}
