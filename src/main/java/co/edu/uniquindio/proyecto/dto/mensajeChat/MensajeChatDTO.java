package co.edu.uniquindio.proyecto.dto.mensajeChat;


import co.edu.uniquindio.proyecto.modelo.EstadoMensaje;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MensajeChatDTO {

    private int codigoChat;

    private String mensaje;

    private EstadoMensaje estado;

    private int codigoUsuario;

}
