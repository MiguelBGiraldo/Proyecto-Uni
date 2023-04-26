package co.edu.uniquindio.proyecto.dto.UsuarioValoracion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ValoracionDTO {

    private int puntuacion;

    private int codigoValorado;

    private int codigoEvaluado;
}
