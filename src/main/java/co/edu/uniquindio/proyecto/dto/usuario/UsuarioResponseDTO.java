package co.edu.uniquindio.proyecto.dto.usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioResponseDTO {

    private int cedula;
    private String nombre;

    private String email;

    private String telefono;

    private String password;

    public UsuarioResponseDTO(int cedula,String nombre, String email, String telefono, String password) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.password = password;
    }
}
