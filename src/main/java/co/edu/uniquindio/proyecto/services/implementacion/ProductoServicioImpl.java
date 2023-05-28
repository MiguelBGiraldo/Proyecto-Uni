package co.edu.uniquindio.proyecto.services.implementacion;

import co.edu.uniquindio.proyecto.dto.producto.ProductoDTO;
import co.edu.uniquindio.proyecto.dto.producto.ProductoResponseDTO;
import co.edu.uniquindio.proyecto.dto.respuestas.CantidadProductosCategoriaDTO;
import co.edu.uniquindio.proyecto.dto.respuestas.CaroBaratoDTO;
import co.edu.uniquindio.proyecto.dto.usuario.UsuarioDTO;
import co.edu.uniquindio.proyecto.modelo.*;
import co.edu.uniquindio.proyecto.repositorios.FavoritoRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import co.edu.uniquindio.proyecto.services.interfaces.ProductoService;

/*
import co.edu.uniquindio.unimarket.dto.ProductoDTO;
import co.edu.uniquindio.unimarket.dto.ProductoGetDTO;
import co.edu.uniquindio.unimarket.modelo.Activo;
import co.edu.uniquindio.unimarket.modelo.Categoria;
import co.edu.uniquindio.unimarket.modelo.Estado;
import co.edu.uniquindio.unimarket.modelo.Producto;
import co.edu.uniquindio.unimarket.repositorios.ProductoRepo;
import co.edu.uniquindio.unimarket.servicios.interfaces.ProductoServicio;
import co.edu.uniquindio.unimarket.servicios.interfaces.UsuarioServicio;*/
import co.edu.uniquindio.proyecto.services.interfaces.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductoServicioImpl implements ProductoService {

    private final ProductoRepo productoRepo;
    private final UsuarioService usuarioServicio;

    private final FavoritoRepo favoritoRepo;


    @Override
    public int createProducto(ProductoDTO productoDTO) throws Exception {

        if(productoDTO.getImagenes().size() <= 0)
            throw new Exception("El producto debe tener asociada almenos una categoria");

        if(productoDTO.getImagenes().size() <= 0)
            throw new Exception("El producto debe tener asociada almenos una imagena");


        Producto producto = new Producto();
        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setUnidades(productoDTO.getUnidades());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setUsuario(usuarioServicio.obtener(productoDTO.getCodigoVendedor()));
        producto.setImagen(productoDTO.getImagenes());
        producto.setCategoria(productoDTO.getCategorias());
        producto.setEstado(Estado.ESPERA);
        producto.setFechaCreado(LocalDateTime.now());
        producto.setFechaLimite(LocalDateTime.now().plusDays(60));

        return productoRepo.save(producto).getCodigo();
    }

    @Override
    public int updateProducto(int codigoProducto, ProductoDTO productoDTO) throws Exception {

        validarExiste(codigoProducto);

        Producto producto = convertir(productoDTO);

        Producto prodAnt = obtenerProducto(codigoProducto);

        producto.setPrecio(productoDTO.getPrecio());
        if (productoDTO.getFechaLimite() != null) {
            producto.setFechaLimite(productoDTO.getFechaLimite());
        }
        else
            producto.setFechaLimite(prodAnt.getFechaLimite());

        producto.setCodigo(codigoProducto);
        producto.setUnidades(productoDTO.getUnidades());
        producto.setNombre(productoDTO.getNombre());
        producto.setEstado(prodAnt.getEstado());
        producto.setFechaCreado(prodAnt.getFechaCreado());
        if (productoDTO.getCategorias() != null)
            producto.setCategoria(productoDTO.getCategorias());
        producto.setUsuario(usuarioServicio.obtener(productoDTO.getCodigoVendedor()));

        return productoRepo.save(producto).getCodigo();
    }

    @Override
    public boolean updateByAmount(int codigoProducto, int unidades) throws Exception {

        Producto producto = productoRepo.obtenerProducto(codigoProducto);

        if (producto == null) {
            throw new Exception("El código " + codigoProducto + " no está asociado a ningún producto");
        }

        producto.setUnidades(unidades);

        productoRepo.save(producto);

        return true;
    }

    //@Override
    public boolean actualizarEstado(int codigoProducto, Estado estado) throws Exception {

        Producto producto = productoRepo.obtenerProducto(codigoProducto);

        if (producto == null) {
            throw new Exception("El código " + codigoProducto + " no está asociado a ningún producto");
        }

        if (estado != Estado.ACEPTADO && estado != Estado.RECHAZADO && estado != Estado.ELIMINADO && estado != Estado.ESPERA) {
            throw new Exception("El estado '" + estado + "' no es valido");
        }

        producto.setEstado(estado);

        productoRepo.save(producto);

        return true;
    }

    @Override
    public boolean deleteProducto(int codigoProducto) throws Exception {

        Producto producto = productoRepo.obtenerProducto(codigoProducto);

        if (producto == null) {
            throw new Exception("El código " + codigoProducto + " no está asociado a ningún producto");
        }

        producto.setEstado(Estado.ELIMINADO);

        productoRepo.save(producto);

        return true;

    }

    @Override
    public ProductoResponseDTO getProduct(int codigoProducto) throws Exception {

        return this.convertir(obtenerProducto(codigoProducto));

    }

    public Producto obtenerProducto(int codigoProducto) throws Exception {

        Optional<Producto> producto = productoRepo.findById(codigoProducto);

        if (producto.isEmpty()) {
            throw new Exception("El código " + codigoProducto + " no está asociado a ningún producto");
        }
        return producto.get();
    }

    public ProductoResponseDTO listarProductoDetalle(int codigoProducto) throws Exception {

        Producto producto = productoRepo.obtenerProductoInfo(codigoProducto);
        if (producto == null) {
            throw new Exception("El código " + codigoProducto + " no está asociado a ningún producto");
        }

        return convertirDetalle(producto);
    }

    //@Override
    public List<ProductoResponseDTO> listarProductosUsuario(int codigoUsuario) {

        List<Producto> lista = productoRepo.listarProductosUsuario(codigoUsuario);
        List<ProductoResponseDTO> respuesta = new ArrayList<>();
        for (Producto p : lista) {
            respuesta.add(convertir(p));
        }
        return respuesta;
    }

    public List<ProductoResponseDTO> listarProductos() {

        List<Producto> lista =  productoRepo.findAll(PageRequest.of(0,20)).toList();
        List<ProductoResponseDTO> respuesta = new ArrayList<>();
        for (Producto p : lista) {
            respuesta.add(convertir(p));
        }
        return respuesta;
    }



    public ProductoResponseDTO convertir(Producto producto) {

        ProductoResponseDTO productoGetDTO = new ProductoResponseDTO();

        productoGetDTO.setCodigo(producto.getCodigo());
        productoGetDTO.setCodigoVendedor(producto.getUsuario().getCedula());
        productoGetDTO.setCategorias(producto.getCategoria());
        productoGetDTO.setImagenes(producto.getImagen());
        productoGetDTO.setEstado(producto.getEstado());
        productoGetDTO.setFechaLimite(producto.getFechaLimite());
        productoGetDTO.setNombre(producto.getNombre());
        productoGetDTO.setDescripcion(producto.getDescripcion());
        productoGetDTO.setUnidades(producto.getUnidades());
        productoGetDTO.setPrecio(producto.getPrecio());

        return productoGetDTO;
    }
    private ProductoResponseDTO convertirDetalle(Producto producto) {

        ProductoResponseDTO productoGetDTO = new ProductoResponseDTO();

        productoGetDTO.setCodigo(producto.getCodigo());
        productoGetDTO.setEstado(producto.getEstado());
        productoGetDTO.setFechaLimite(producto.getFechaLimite());
        productoGetDTO.setNombre(producto.getNombre());
        productoGetDTO.setDescripcion(producto.getDescripcion());
        productoGetDTO.setUnidades(producto.getUnidades());
        productoGetDTO.setPrecio(producto.getPrecio());
        productoGetDTO.setCodigoVendedor(producto.getUsuario().getCedula());
        productoGetDTO.setCategorias(producto.getCategoria());

        return productoGetDTO;
    }

    private Producto convertir(ProductoDTO productoDTO) throws Exception {


        Producto producto = new Producto();
        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setUnidades(productoDTO.getUnidades());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setUsuario(usuarioServicio.obtener(productoDTO.getCodigoVendedor()));
        producto.setImagen(productoDTO.getImagenes());
        producto.setCategoria(productoDTO.getCategorias());
        producto.setEstado(productoDTO.getEstado());
        producto.setFechaCreado(productoDTO.getFechaCreado());
        producto.setFechaLimite(productoDTO.getFechaLimite());

        return producto;
    }


    //@Override
    public List<ProductoResponseDTO> listarProductosCategoria(Categoria categoria) {

        List<Producto> lista = productoRepo.listarProductosCategoria(categoria);
        List<ProductoResponseDTO> respuesta = new ArrayList<>();
        for (Producto p : lista) {
            System.out.println(p.getCodigo());
            respuesta.add(convertir(p));
        }
        return respuesta;

    }

    //@Override
    public List<ProductoResponseDTO> listarProductosPorEstado(Estado estado) {


        List<Producto> lista = productoRepo.listarProductosEstado(estado);
        List<ProductoResponseDTO> respuesta = new ArrayList<>();
        for (Producto p : lista) {
            respuesta.add(convertir(p));
        }
        return respuesta;
    }

    //@Override
    public List<ProductoResponseDTO> listarProductosFavoritos(int codigoUsuario) throws Exception {

        usuarioServicio.obtener(codigoUsuario);

        System.out.println("Aquiii");
        List<Producto> listaProductoFav = productoRepo.obtenerFavoritos(codigoUsuario);

        System.out.println("Aquiii");

        if (listaProductoFav.isEmpty()) {
            {
                throw new Exception("El usuario con el codigo " + codigoUsuario + " no tiene ningun producto como favorito");
            }
        }
        List<ProductoResponseDTO> listaProdDTO = new ArrayList<>();

        for (Producto p : listaProductoFav) {
            listaProdDTO.add(convertir(p));
        }

        return listaProdDTO;
    }

    public int guardarProductoFavorito(int codigoUsuario, int codigoProducto) throws Exception {

        usuarioServicio.obtener(codigoUsuario);
        obtenerProducto(codigoProducto);
        if(favoritoRepo.obtenerProductoFavorito(codigoUsuario,codigoProducto) != null)
            throw new Exception("El código " + codigoProducto + " ya esta registrado como favorito");

        Favorito favorito = new Favorito();
        favorito.setCodigoProducto(obtenerProducto(codigoProducto));
        favorito.setCodigoUsuario(usuarioServicio.obtener(codigoUsuario));
        favorito.setFecha(LocalDateTime.now());

        return favoritoRepo.save(favorito).getCodigoProducto().getCodigo();
    }

    public boolean eliminarProductoFavorito(int codigoUsuario, int codigoProducto) throws Exception {

        usuarioServicio.obtener(codigoUsuario);
        obtenerProducto(codigoProducto);

        Favorito favorioDelete = favoritoRepo.obtenerProductoFavorito(codigoUsuario, codigoProducto);

        favoritoRepo.delete(favorioDelete);

        return true;
    }

    //@Override
    public List<ProductoResponseDTO> listarProductosNombre(String nombre) {

        List<Producto> lista = productoRepo.listarProductosNombre(nombre);
        List<ProductoResponseDTO> respuesta = new ArrayList<>();

        for (Producto p : lista) {
            respuesta.add(convertir(p));
        }

        return respuesta;
    }

    //@Override
    public List<ProductoResponseDTO> listarProductosPrecio(float precioMinimo, float precioMaximo) {

        List<Producto> lista = productoRepo.listarProductosPrecio(precioMinimo, precioMaximo);
        List<ProductoResponseDTO> respuesta = new ArrayList<>();

        for (Producto p : lista) {
            respuesta.add(convertir(p));
        }
        return respuesta;
    }

    public int  obtenerUsuario(int codigo) {

        int usuario = productoRepo.obtenerUsuario(codigo);

        return usuario;
    }

    private void validarExiste(int codigoProducto) throws Exception {
        boolean existe = productoRepo.existsById(codigoProducto);

        if (!existe) {
            throw new Exception("El código " + codigoProducto + " no está asociado a ningún producto");
        }

    }

  /*  public List<ProductoResponseDTO> listarProductosComprados(int codigo) throws  Exception{

       Usuario buscado = usuarioServicio.obtener(codigo);

        List<Producto> productos = productoRepo.obtenerProductosComprados(codigo);

        List<ProductoResponseDTO> respuesta = new ArrayList<>();
        for(Producto p : productos){
            respuesta.add(convertir(p));
        }

        return respuesta;
    }*/

   /* public List<CantidadProductosCategoriaDTO> listarCantidadCategorias(){

        List<Object[]> consulta = productoRepo.obtenerCantidadCategoria();


        List<CantidadProductosCategoriaDTO> respuesta = new ArrayList<>();

        for(Object[] c : consulta){
            respuesta.add(new CantidadProductosCategoriaDTO((Categoria) c[0],(Long) c[1]));
        }
        return respuesta;
    }*/

    /*public List<Producto> listarProductosCaroyBaratoCategoria(Categoria categoria) throws Exception{


        Producto caro = productoRepo.obtenerProductoMasCaro(categoria, PageRequest.of(0,1));
        Producto barato = productoRepo.obtenerProductoMasBarato(categoria, PageRequest.of(0, 1));
        List<Producto> productos  = new ArrayList<>();
        productos.add(caro);
        productos.add(barato);

        return  productos;
    }*/
}