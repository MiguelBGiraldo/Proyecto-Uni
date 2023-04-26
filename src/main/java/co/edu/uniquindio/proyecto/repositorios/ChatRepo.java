package co.edu.uniquindio.proyecto.repositorios;


import co.edu.uniquindio.proyecto.modelo.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepo extends JpaRepository<Chat, Integer> {

    @Query("select c from Chat c where (c.usuario.cedula = :usuario1 or c.usuario.cedula = :usuario2) and (c.usuario2.cedula = :usuario1 or c.usuario2.cedula = :usuario2) and c.producto.codigo = :codigoProducto")
    Chat verificarChat(int codigoProducto, int usuario1, int usuario2);

    @Query("select c from Chat c where c.usuario.cedula = :codigoUsuario or c.usuario2.cedula = :codigoUsuario ")
    List<Chat> obtenerChats(int codigoUsuario);

    @Query("select c from Chat c where c.codigo = :codigo")
    Chat obtenerChat(int codigo);

}
