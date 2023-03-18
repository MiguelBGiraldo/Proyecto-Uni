package co.edu.uniquindio.proyecto.services;

import co.edu.uniquindio.proyecto.dto.producto.ProductoDTO;
import co.edu.uniquindio.proyecto.dto.producto.ProductoResponseDTO;

public interface ProductoService {

    int createProducto(ProductoDTO productoDTO);

    int deleteProducto(int codigoProducto);

    int updateProducto(int codigoProducto, ProductoDTO productoDTO);

    //int updateByStatus(int codigoProducto, Estado estado);

    int updateByAmount(int codigoProducto, int unidades);

    ProductoResponseDTO getProduct(int codigoProducto);

    //List<ProductoDTO> productosListCategory(Categoria categoria);

    //List<ProductoDTO> productosListUsers(int codigoUsuario);

    //List<ProductoDTO> productosListStatus(Estado estado);

    //List<ProductoDTO> productosListName(String nombre);

    //List<ProductoDTO> productosListPrice(floar minPrice, float maxPrice);

}
