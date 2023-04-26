package co.edu.uniquindio.proyecto.repositorios;


import co.edu.uniquindio.proyecto.modelo.DetalleTransaccion;
import co.edu.uniquindio.proyecto.modelo.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleTransaccionRepo extends JpaRepository<DetalleTransaccion, Integer> {

    @Query("select t from DetalleTransaccion t where t.codigoTransaccion.codigo = :codigoTransaccion")
    List<DetalleTransaccion> listarDetalleTransaccion(int codigoTransaccion);
}
