package co.edu.uniquindio.proyecto.repositorios;


import co.edu.uniquindio.proyecto.modelo.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.event.ListDataEvent;
import java.util.List;
import java.util.Objects;

@Repository
public interface ProductoRepo extends JpaRepository<Producto, Integer> {

    @Query("select p from Producto p  where p.usuario.cedula = :codigoUsuario and (p.estado = 'ESPERA' or p.estado = 'ACEPTADO' or p.estado = 'RECHAZADO')")
    List<Producto> listarProductosUsuario(int codigoUsuario);

    @Query("select p from Producto p where p.nombre like concat( '%', :nombre, '%' ) ")
    List<Producto> listarProductosNombre(String nombre);

    @Query("select p from Producto p where p.codigo = :codigo")
    Producto obtenerProducto(int codigo);

    @Query("select p from Producto p join p.categoria c where c = :categoria")
    List<Producto> listarProductosCategoria(Categoria categoria);

    @Query("select p from Producto p where p.estado = :estado")
    List<Producto> listarProductosEstado(Estado estado);

    @Query("select p from Producto p join p.favoritos f where f.codigoUsuario.cedula = :codigoUsuario")
    List<Producto> obtenerFavoritos(int codigoUsuario);

    @Query("select  p from Producto p left join fetch p.categoria left join p.imagen where p.codigo = :codigoProducto")
    Producto obtenerProductoInfo(int codigoProducto);

    @Query("select  p from Producto p where p.precio between :minimo and :maximo")
    List<Producto> listarProductosPrecio(double minimo, double maximo);

    @Query("select p.usuario.cedula from Producto p where p.codigo = :codigo")
    int obtenerUsuario(int codigo);

    //@Query("select distinct dt.codigoProducto from DetalleTransaccion dt where dt.codigoTransaccion.usuario.cedula = :codigo")
    //List<Producto> obtenerProductosComprados(int codigo);

    //@Query("select c, count(p.codigo) as cantidad from Producto p join p.categoria c group by c ")
    //List<Object[]> obtenerCantidadCategoria();

    /*@Query("select p from Producto p join p.categoria c WHERE c = :categoria order by p.precio desc")
    Producto obtenerProductoMasCaro(Categoria categoria, Pageable pageable);

    @Query("select p as precio from Producto p join p.categoria c WHERE c = :categoria order by p.precio asc")
    Producto obtenerProductoMasBarato(Categoria categoria, Pageable pageable);*/

    //@Query("select p from Producto p join p.categoria c WHERE c = :categoria")
    //List<Producto> obtenerCategoria(Categoria categoria);

}