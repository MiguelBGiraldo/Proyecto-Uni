package co.edu.uniquindio.proyecto.repositorios;


import co.edu.uniquindio.proyecto.modelo.Moderador;
import co.edu.uniquindio.proyecto.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeradorRepo extends JpaRepository<Moderador,String> {

    @Query("select m from Moderador m where m.email = :correo")
    Moderador buscarUsuario(String correo);

    @Query("select m from Moderador m where m.cedula = :cedula")
    Moderador obtenerModerador(int cedula);

}
