package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.modelo.EstadoUsuario;
import co.edu.uniquindio.proyecto.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


//Se manda el tipo de dato de la entidad y el tipo de dato de la llave primaria
@Repository
public interface UsuarioRepo extends JpaRepository<Usuario,Integer> {

    //JPQL ---> Lenguaje de consultas para JPA
    //Se hace referencia a la case usuario y se le pone un alias u
    @Query("select u from Usuario u where u.email = :correo")
    Usuario buscarUsuario(String correo);

    @Query("select  u from Usuario u where u.cedula = :cedula")
    Usuario buscarUsuarioByCedula(int cedula);

    //el IDE entiende que debe buscar solo por el findBy
    //Usuario findByEmail(String email);


    //Usuario findByEmailAAndPassword(String email,String password);

    @Query("update Usuario u SET u.estado = :estado  where u.email = :email")
    Usuario eliminarPorCorreo(String email, EstadoUsuario estado);

    @Query("select u.email FROM Usuario  u where u.cedula = :cedula")
    String obtenerCorreo(int cedula);
}
