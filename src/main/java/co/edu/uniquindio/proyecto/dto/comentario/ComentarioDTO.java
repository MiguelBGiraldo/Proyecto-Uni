package co.edu.uniquindio.proyecto.dto.comentario;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ComentarioDTO {

    private String mensaje;

    private int codigoUsuario;

    private int codigoProducto;
}
