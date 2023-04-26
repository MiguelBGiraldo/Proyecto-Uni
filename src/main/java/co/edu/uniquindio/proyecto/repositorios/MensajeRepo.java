package co.edu.uniquindio.proyecto.repositorios;


import co.edu.uniquindio.proyecto.modelo.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensajeRepo extends JpaRepository<Mensaje,Integer> {

    @Query("select m from Mensaje m where m.chat.codigo = :codigoChat")
    List<Mensaje> listarMensajesChat(int codigoChat);

    @Modifying
    @Query("delete FROM Mensaje m where m.chat.codigo = :codigoChat")
    void eliminarMensajes(int codigoChat);
}
