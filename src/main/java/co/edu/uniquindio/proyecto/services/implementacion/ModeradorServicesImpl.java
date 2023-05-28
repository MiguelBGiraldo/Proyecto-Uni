package co.edu.uniquindio.proyecto.services.implementacion;


import co.edu.uniquindio.proyecto.dto.Email.EmailDTO;
import co.edu.uniquindio.proyecto.dto.Moderador.ModeradorDTO;
import co.edu.uniquindio.proyecto.dto.producto.ProductoResponseDTO;
import co.edu.uniquindio.proyecto.dto.usuario.UsuarioResponseDTO;
import co.edu.uniquindio.proyecto.modelo.Estado;
import co.edu.uniquindio.proyecto.modelo.Producto;
import co.edu.uniquindio.proyecto.modelo.ProductoModerador;
import co.edu.uniquindio.proyecto.modelo.Usuario;
import co.edu.uniquindio.proyecto.repositorios.ModeradorRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoModeradorRepo;
import co.edu.uniquindio.proyecto.services.interfaces.EmailService;
import co.edu.uniquindio.proyecto.services.interfaces.ModeradorService;
import co.edu.uniquindio.proyecto.services.interfaces.ProductoService;
import co.edu.uniquindio.proyecto.services.interfaces.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ModeradorServicesImpl implements ModeradorService {

    ProductoService productoService;

    ProductoModeradorRepo productoModeradorRepo;

    UsuarioService usuarioService;

    ModeradorRepo moderadorRepo;

    EmailService emailService;

    public boolean registrarActividad(ModeradorDTO moderadorDTO) throws Exception{

        ProductoModerador productoModerador = new ProductoModerador();

        productoModerador.setCodigoModerador(moderadorRepo.obtenerModerador(moderadorDTO.getCedula_moderador()));
        productoModerador.setCodigoProducto(productoService.obtenerProducto(moderadorDTO.getCodigo_producto()));
        productoModerador.setFecha(LocalDateTime.now());
        productoModerador.setEstado(moderadorDTO.getEstado());
        productoModerador.setMotivo(moderadorDTO.getMotivo());
        productoModeradorRepo.save(productoModerador);
        productoService.actualizarEstado(moderadorDTO.getCodigo_producto(),moderadorDTO.getEstado());

//        EmailDTO emailDTO = new EmailDTO();
//        emailDTO.setDestinatario(usuarioService.obtenerCorreoPorID(productoService.obtenerUsuario(moderadorDTO.getCodigo_producto())));
//        emailDTO.setCuerpo(moderadorDTO.getMotivo());
//        emailDTO.setAsunto("Cambio estado producto");
//        emailService.enviarEmail(emailDTO);
        return true;
    }

    public List<ProductoResponseDTO> listarProductos() {
        return productoService.listarProductos();
    }

    public List<UsuarioResponseDTO> listarUsuarios(){

        return usuarioService.listarUsuarios();
    }

    public  boolean  activarProducto(int codigoProducto) throws Exception {

        productoService.actualizarEstado(codigoProducto,Estado.ACEPTADO);

        return true;
    }

    public  boolean rechazarProducto (int codigoProducto) throws Exception{
        productoService.actualizarEstado(codigoProducto,Estado.RECHAZADO);
        return true;
    }

}
