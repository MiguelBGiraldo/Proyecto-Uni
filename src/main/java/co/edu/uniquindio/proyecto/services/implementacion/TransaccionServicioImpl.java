package co.edu.uniquindio.proyecto.services.implementacion;

import co.edu.uniquindio.proyecto.dto.Email.EmailDTO;
import co.edu.uniquindio.proyecto.dto.Transaccion.TransaccionDTO;
import co.edu.uniquindio.proyecto.dto.Transaccion.TransaccionResponseDTO;
import co.edu.uniquindio.proyecto.dto.detalleTransaccion.DetalleTransaccionDTO;
import co.edu.uniquindio.proyecto.dto.producto.ProductoResponseDTO;
import co.edu.uniquindio.proyecto.modelo.DetalleTransaccion;
import co.edu.uniquindio.proyecto.modelo.Producto;
import co.edu.uniquindio.proyecto.modelo.Transaccion;
import co.edu.uniquindio.proyecto.repositorios.DetalleTransaccionRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.TransaccionRepo;
import co.edu.uniquindio.proyecto.services.interfaces.EmailService;
import co.edu.uniquindio.proyecto.services.interfaces.ProductoService;
import co.edu.uniquindio.proyecto.services.interfaces.TransaccionService;
import co.edu.uniquindio.proyecto.services.interfaces.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@Service
public class TransaccionServicioImpl implements TransaccionService {

    private final UsuarioService usuarioService;
    private final ProductoService productoService;

    private final TransaccionRepo transaccionRepo;

    private final DetalleTransaccionRepo detalleTransaccionRepo;

    private final ProductoRepo productoRepo;

    private final EmailService emailService;

    public  boolean creteCompra(TransaccionDTO transaccionDTO) throws Exception{

        //usuarioService.obtener(transaccionDTO.getCodigoUsuario());

        Transaccion transaccion = new Transaccion();
        transaccion.setUsuario(usuarioService.obtener(transaccionDTO.getCodigoUsuario()));
        transaccion.setFecha(LocalDateTime.now());
        transaccion.setMetodoPago(transaccionDTO.getMetodoPago());

        List<DetalleTransaccionDTO> detalleDTO = new ArrayList<>();
        detalleDTO = transaccionDTO.getDetalleCompra();

        List<DetalleTransaccion> listaDetalle = new ArrayList<>();
        double precioTotal = 0;
        for(DetalleTransaccionDTO l : detalleDTO){
          //  listaDetalle.add( convertirDetalle(l));
          //  DetalleTransaccion detalleTransaccion = convertirDetalle(l);
            precioTotal += l.getPrecio();
        }
        transaccion.setValorTotal(precioTotal);
        Transaccion transaccionSave =  transaccionRepo.save(transaccion);

        for(DetalleTransaccionDTO l : detalleDTO){
            //  listaDetalle.add( convertirDetalle(l));
             DetalleTransaccion detalleTransaccion = convertirDetalle(l);
             detalleTransaccion.setCodigoTransaccion(transaccionSave);
             detalleTransaccionRepo.save(detalleTransaccion);
             Producto producto = productoService.obtenerProducto(l.getCodigoProducto());
             if(l.getUnidades() >= producto.getUnidades())
                 throw new Exception("El producto con el codigo" + producto.getCodigo() + " No cuenta con las unidades requeridas");
             producto.setUnidades(producto.getUnidades() - l.getUnidades());
             productoRepo.save(producto);

             //Se envia el correo al que vendio
            //EmailDTO emailDTO2 = new EmailDTO();
            //emailDTO2.setDestinatario(usuarioService.obtenerCorreoPorID(productoService.obtenerUsuario(l.getCodigoProducto())));
            //emailDTO2.setCuerpo("Se ha realizado una compra");
            //emailDTO2.setAsunto("Compra");
            //emailService.enviarEmail(emailDTO2);
        }

        //Dice que no puedo enviar mas correos
       //EmailDTO emailDTO = new EmailDTO();
        //emailDTO.setDestinatario(transaccion.getUsuario().getEmail());
        //emailDTO.setCuerpo("Se ha realizado una compra");
        //emailDTO.setAsunto("Compra");

        //emailService.enviarEmail(emailDTO);


        return true;


    }

    public List<TransaccionResponseDTO> usarListCompra(int codigoUsuario) throws Exception{

        usuarioService.obtener(codigoUsuario);

        List<Transaccion> transacciones = transaccionRepo.listarTransaccionesUsuario(codigoUsuario);

        if(transacciones == null || transacciones.isEmpty()){
            throw new Exception("El usuario no tiene transacciones");
        }
        //Se listan las transacciones
        for (Transaccion t: transacciones){
            List<DetalleTransaccion> detallesTransaccion = detalleTransaccionRepo.listarDetalleTransaccion(t.getCodigo());
            t.setDetalleTransaccion(detallesTransaccion);
        }

        //Convertir a DTO.
        List<TransaccionResponseDTO> transaccionResponseDTO = new ArrayList<>();

        for (Transaccion t: transacciones){
            transaccionResponseDTO.add(convertir(t));
        }

        return transaccionResponseDTO;
    }

    public TransaccionResponseDTO obtenerDetalleTransaccion(int codigo) throws  Exception{

        Transaccion transaccion = transaccionRepo.obtenerTransaccion(codigo);

        if(transaccion == null){
            throw new Exception("El código "+codigo+" no está asociado a ninguna transaccion");
        }


            List<DetalleTransaccion> detallesTransaccion = detalleTransaccionRepo.listarDetalleTransaccion(codigo);
            transaccion.setDetalleTransaccion(detallesTransaccion);

            TransaccionResponseDTO transaccionDTO = convertir(transaccion);

            return transaccionDTO;

    }

    public DetalleTransaccion convertirDetalle(DetalleTransaccionDTO detalleDTO) throws Exception{


        DetalleTransaccion detalle = new DetalleTransaccion();
        detalle.setPrecio(detalleDTO.getPrecio());
        detalle.setUnidades(detalleDTO.getUnidades());
        //detalle.setCodigo(detalleDTO.getCodigoTransaccion() > 0 ? detalleDTO.getCodigoTransaccion() : 0 );
        detalle.setCodigoProducto(productoService.obtenerProducto(detalleDTO.getCodigoProducto()));

        return detalle;
    }

    public DetalleTransaccionDTO convertirDetalle(DetalleTransaccion detalle){


        DetalleTransaccionDTO detalleDto = new DetalleTransaccionDTO(
                detalle.getCodigoProducto().getCodigo(),
                //detalle.getCodigoTransaccion().getCodigo(),
                detalle.getPrecio(),
                detalle.getUnidades()
        );

        return  detalleDto;
    }

    public TransaccionResponseDTO convertir(Transaccion transaccion){

        List<DetalleTransaccionDTO> listaDetalle = new ArrayList<>();

        for(DetalleTransaccion d: transaccion.getDetalleTransaccion()){
            listaDetalle.add(convertirDetalle(d));
        }

        TransaccionResponseDTO transaccionDTO = new  TransaccionResponseDTO(
                transaccion.getCodigo(),
                transaccion.getFecha(),
                transaccion.getValorTotal(),
                transaccion.getUsuario().getCedula(),
                transaccion.getMetodoPago(),
                listaDetalle
                );

        return transaccionDTO;
    }
}
