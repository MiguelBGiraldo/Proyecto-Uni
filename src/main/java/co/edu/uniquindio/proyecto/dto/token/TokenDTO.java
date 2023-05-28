package co.edu.uniquindio.proyecto.dto.token;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TokenDTO {

    @NotNull
    private String token;

    private String refreshToken;

    //private String estado;
}
