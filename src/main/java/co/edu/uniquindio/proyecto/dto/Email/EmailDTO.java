package co.edu.uniquindio.proyecto.dto.Email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailDTO {

    private String asunto;

    private String cuerpo;

    private String destinatario;
}
