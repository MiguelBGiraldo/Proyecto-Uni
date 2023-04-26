package co.edu.uniquindio.proyecto.dto.usuario;

import co.edu.uniquindio.proyecto.modelo.EstadoUsuario;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;


//El DTO recibe la informacion del cliente y ese es el que se guarda. Los DTO de get no se validan porque ya esta en BD
@Getter
@Setter
public class UsuarioDTO {

    @NotNull
    @Positive
    int cedula;

    @NotBlank
    @NotNull
    @Length(min = 3,max = 150, message = "El nombre debe tener maximo 150 caracteres")
    String nombre;

    @NotBlank
    @NotNull
    @Length(min = 3,max = 100, message = "El correo debe tener maximo 100 caracteres")
    String email;

    @NotBlank
    @NotNull
    @Length(min = 3,max = 100, message = "La dirección debe tener maximo 100 caracteres")
    String direccion;

    @NotBlank
    @NotNull
    @Length(min = 3,max = 12, message = "El telefono debe tener maximo 12 caracteres")
    String telefono;

    @NotBlank
    @NotNull
    @Length(min = 3,max = 50, message = "La contraseña debe tener maximo 50 caracteres")
    String password;

    public UsuarioDTO(int cedula,String nombre, String email, String telefono, String password) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.password = password;
    }
}
