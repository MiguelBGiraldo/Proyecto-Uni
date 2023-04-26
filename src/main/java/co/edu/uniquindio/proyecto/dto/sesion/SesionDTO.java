package co.edu.uniquindio.proyecto.dto.sesion;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SesionDTO {


    @NotNull
    private String email;

    @NotNull
    private String password;

   // private int tipo;
}
