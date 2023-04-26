package co.edu.uniquindio.proyecto.services.interfaces;

import co.edu.uniquindio.proyecto.dto.producto.ProductoDTO;
import co.edu.uniquindio.proyecto.dto.producto.ProductoResponseDTO;
import co.edu.uniquindio.proyecto.modelo.Categoria;
import co.edu.uniquindio.proyecto.modelo.Estado;
import co.edu.uniquindio.proyecto.modelo.Producto;

import java.util.List;

public interface ProductoService {

    int createProducto(ProductoDTO productoDTO) throws Exception;

    boolean deleteProducto(int codigoProducto) throws Exception;

    int updateProducto(int codigoProducto, ProductoDTO productoDTO) throws Exception;

    //int updateByStatus(int codigoProducto, Estado estado);

    boolean updateByAmount(int codigoProducto, int unidades) throws Exception;

    ProductoResponseDTO getProduct(int codigoProducto) throws Exception;

    public Producto obtenerProducto(int codigoProducto) throws Exception;

    public boolean actualizarEstado(int codigoProducto, Estado estado) throws Exception;

    ProductoResponseDTO listarProductoDetalle(int codigoProducto) throws Exception;

    List<ProductoResponseDTO> listarProductosUsuario(int codigoUsuario);

    List<ProductoResponseDTO> listarProductosCategoria(Categoria categoria);

    List<ProductoResponseDTO> listarProductosPorEstado(Estado estado);

    List<ProductoResponseDTO> listarProductosFavoritos(int codigoUsuario) throws Exception;

    List<ProductoResponseDTO> listarProductos();

    int guardarProductoFavorito(int codigoUsuario, int codigoProducto) throws Exception;

    boolean eliminarProductoFavorito(int codigoUsuario,int codigoProducto) throws Exception;

    List<ProductoResponseDTO> listarProductosPrecio(float precioMinimo, float precioMaximo);

    List<ProductoResponseDTO> listarProductosNombre(String nombre);

    int  obtenerUsuario(int codigo);


        //List<ProductoDTO> productosListCategory(Categoria categoria);

    //List<ProductoDTO> productosListUsers(int codigoUsuario);

    //List<ProductoDTO> productosListStatus(Estado estado);

    //List<ProductoDTO> productosListName(String nombre);

    //List<ProductoDTO> productosListPrice(floar minPrice, float maxPrice);

}
