package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.dto.usuario.UsuarioDTO;
import co.edu.uniquindio.proyecto.services.interfaces.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UsuarioTest {

    //Todos los metodos de prueba deben ser void y deben tener el @Test

    //Se llama la interface.
    @Autowired
    private UsuarioService usuarioService;

    @Test
    public void crearUsuarioTest(){


        try {
            UsuarioDTO usuarioDTO = new UsuarioDTO(1097407885,"Pepito","pep@gmail.com","1234","5456646");
            usuarioService.CreateUser(usuarioDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
