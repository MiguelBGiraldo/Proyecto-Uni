package co.edu.uniquindio.proyecto.repositorios;


import co.edu.uniquindio.proyecto.modelo.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoritoRepo extends JpaRepository<Favorito,Integer> {


    @Query("select f from Favorito f where f.codigoUsuario.cedula = :codigoUsuario and f.codigoProducto.codigo = :codigoProducto")
    Favorito obtenerProductoFavorito(int codigoUsuario, int codigoProducto);
}
