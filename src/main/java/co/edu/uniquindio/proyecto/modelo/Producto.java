package co.edu.uniquindio.proyecto.modelo;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class Producto implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(nullable = false)
    private int unidades;

    @Column(nullable = false)
    private String Descripcion;

    @Column(nullable = false)
    private  double precio;

    @Column(nullable = false)
    private LocalDate fechaCreado;

    @Column(nullable = false)
    private LocalDate fechaLimite;

    @ManyToOne
    private Usuario usuario;

    @ManyToMany
    private List<Usuario> favoritos;

    @OneToMany(mappedBy = "codigoProducto")
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "codigoProducto")
    private List<ProductoModerador> productoModerador;

    @ElementCollection
    @Column(nullable = false)
    private Map<String,String> imagen;

    @ElementCollection
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private  List<Categoria> categoria;

    @OneToMany(mappedBy = "codigoProducto")
    private List<DetalleTransaccion> detalleTransaccion;

    @OneToMany(mappedBy = "producto")
    private List<Chat> chat;
}
