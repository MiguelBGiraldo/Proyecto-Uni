package co.edu.uniquindio.proyecto.repositorios;


import co.edu.uniquindio.proyecto.modelo.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransaccionRepo extends JpaRepository<Transaccion, Integer> {


    @Query("select t from Transaccion t where t.usuario.cedula = :codigoUsuario")
    List<Transaccion> listarTransaccionesUsuario(int codigoUsuario);

    @Query("select t from Transaccion t where t.codigo = :codigo")
    Transaccion obtenerTransaccion(int codigo);
}
