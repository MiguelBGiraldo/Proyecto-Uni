package co.edu.uniquindio.proyecto.dto.mensajeChat;


import co.edu.uniquindio.proyecto.modelo.EstadoMensaje;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@AllArgsConstructor
@Getter
@Setter
public class MensajeChatResponseDTO {


    private int codigo;

    private int codigoChat;

    private LocalDateTime fecha;

    private EstadoMensaje estado;

    private String mensaje;

    private int codigoUsuario;
}
