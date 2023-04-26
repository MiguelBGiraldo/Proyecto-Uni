package co.edu.uniquindio.proyecto.repositorios;


import co.edu.uniquindio.proyecto.modelo.Comentario;
import co.edu.uniquindio.proyecto.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepo extends JpaRepository <Comentario, Integer> {


    @Query("select c from Comentario c where c.codigoProducto.codigo = :codigoProd")
    List<Comentario> listarComentariosProducto(int codigoProd);
}
