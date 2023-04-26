package co.edu.uniquindio.proyecto.services.implementacion;

import co.edu.uniquindio.proyecto.dto.UsuarioValoracion.ValoracionDTO;
import co.edu.uniquindio.proyecto.dto.UsuarioValoracion.ValoracionResponseDTO;
import co.edu.uniquindio.proyecto.modelo.Usuario;
import co.edu.uniquindio.proyecto.modelo.ValoracionVendedor;
import co.edu.uniquindio.proyecto.repositorios.ValoracionRepo;
import co.edu.uniquindio.proyecto.services.interfaces.UsuarioService;
import co.edu.uniquindio.proyecto.services.interfaces.UsuarioValoracionService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class UsuarioValoracionImpl implements UsuarioValoracionService {

    @Autowired
    private  UsuarioService usuarioService;

    @Autowired
    private  ValoracionRepo valoracionRepo;
    public boolean guardarValoracion(ValoracionDTO valoracionDTO) throws Exception{

        Usuario evaluado =  usuarioService.obtener(valoracionDTO.getCodigoEvaluado());
        Usuario valorado = usuarioService.obtener(valoracionDTO.getCodigoValorado());

        ValoracionVendedor valoracion = new ValoracionVendedor();
        valoracion.setFecha(LocalDateTime.now());
        valoracion.setPuntuacion(valoracionDTO.getPuntuacion());
        valoracion.setCodigoEvaluador(evaluado);
        valoracion.setCodigoValorado(valorado);

        valoracionRepo.save(valoracion);

        return true;
    }

    public List<ValoracionResponseDTO> obtenerValoraciones(int codigoUsuario) throws Exception{

        Usuario valorado = usuarioService.obtener(codigoUsuario);

        List<ValoracionVendedor> listaValoraciones = valoracionRepo.obtenerValoraciones(codigoUsuario);

        List<ValoracionResponseDTO> valoracionesDTO = new ArrayList<>();

        for (ValoracionVendedor v : listaValoraciones){
            valoracionesDTO.add(convertir(v));
        }
        return  valoracionesDTO;
    }

    public  double obtenerPromedio(int codigoUsuario) throws Exception {

        usuarioService.obtener(codigoUsuario);

        return valoracionRepo.obtenerPromedio(codigoUsuario);
    }

    ValoracionResponseDTO convertir(ValoracionVendedor valoracion){

        ValoracionResponseDTO valoracionDTO = new ValoracionResponseDTO(
                valoracion.getCodigo(),
                valoracion.getPuntuacion(),
                valoracion.getFecha(),
                valoracion.getCodigoValorado().getCedula(),
                valoracion.getCodigoEvaluador().getCedula()
        );

        return valoracionDTO;
    }
}
