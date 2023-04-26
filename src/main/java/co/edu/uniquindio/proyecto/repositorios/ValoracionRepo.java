package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.modelo.ValoracionVendedor;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ValoracionRepo extends JpaRepository<ValoracionVendedor, Integer> {


    @Query("select v from ValoracionVendedor v where v.codigoValorado.cedula = :usuario")
    List<ValoracionVendedor> obtenerValoraciones(int usuario);

    @Query("select avg(v.puntuacion) from ValoracionVendedor v where v.codigoValorado.cedula = :usuario")
    double obtenerPromedio(int usuario);
}
