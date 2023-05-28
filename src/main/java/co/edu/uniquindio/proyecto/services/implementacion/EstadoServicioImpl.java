package co.edu.uniquindio.proyecto.services.implementacion;


import co.edu.uniquindio.proyecto.modelo.Categoria;
import co.edu.uniquindio.proyecto.modelo.Estado;
import co.edu.uniquindio.proyecto.services.interfaces.EstadoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class EstadoServicioImpl implements EstadoService {


    public List<Estado> obtenerEstados (){

        List<Estado> estados = new ArrayList<>();
        Estado[] valores = Estado.values();
        for (Estado valor : valores) {
            estados.add(valor);
        }
        return estados;
    }
}
