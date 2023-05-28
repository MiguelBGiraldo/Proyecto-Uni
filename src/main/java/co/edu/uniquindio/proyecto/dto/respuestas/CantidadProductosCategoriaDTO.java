package co.edu.uniquindio.proyecto.dto.respuestas;


import co.edu.uniquindio.proyecto.modelo.Categoria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CantidadProductosCategoriaDTO {

    private Categoria categoria;

    private long cantidad;
}
