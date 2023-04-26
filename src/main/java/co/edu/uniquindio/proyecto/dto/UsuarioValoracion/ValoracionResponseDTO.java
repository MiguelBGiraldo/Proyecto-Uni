package co.edu.uniquindio.proyecto.dto.UsuarioValoracion;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
public class ValoracionResponseDTO {

    private int codigo;

    private int puntacion;

    private LocalDateTime fecha;

    private int codigoValorado;

    private int codigoEvaluado;
}
