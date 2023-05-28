package co.edu.uniquindio.proyecto.services.implementacion;


import co.edu.uniquindio.proyecto.dto.Categorias.CategoriaDTO;
import co.edu.uniquindio.proyecto.modelo.Categoria;
import co.edu.uniquindio.proyecto.services.interfaces.CategoriaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class CategoriaServicioImpl implements CategoriaService {

    public List<Categoria> obtenerCategorias (){

        List<Categoria> categorias = new ArrayList<>();
        Categoria[] valores = Categoria.values();
        for (Categoria valor : valores) {
           categorias.add(valor);
        }
        return categorias;
    }
}
