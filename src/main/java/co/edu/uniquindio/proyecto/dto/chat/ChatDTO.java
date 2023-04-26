package co.edu.uniquindio.proyecto.dto.chat;


import co.edu.uniquindio.proyecto.modelo.Mensaje;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Setter
@Getter
public class ChatDTO {

    private int codigoReceptor;

    private int codigoEmisor;

    private int codigoProducto;

    //private List<Mensaje> mensajes;
}
