package co.edu.uniquindio.proyecto.dto.producto;

import co.edu.uniquindio.proyecto.modelo.Categoria;
import co.edu.uniquindio.proyecto.modelo.Estado;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class ProductoDTO {

    @Length(max = 140)
    @NotBlank
    @NotNull
    private String nombre;

    @NotBlank
    @NotNull
    private String descripcion;

    @PositiveOrZero
    private int unidades;

    @PositiveOrZero
    private float precio;

    @Positive
    private int codigoVendedor;

    private List<String> imagenes;

    private List<Categoria> categorias;

    private Estado estado;

    private LocalDateTime fechaCreado;

    private LocalDateTime fechaLimite;

    //private List<>

}
