package co.edu.uniquindio.proyecto.modelo;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private LocalDateTime fechaCreado;

    @Column(nullable = false)
    private LocalDateTime fechaLimite;

    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "codigoProducto")
    private List<Favorito> favoritos;

    @OneToMany(mappedBy = "codigoProducto")
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "codigoProducto")
    private List<ProductoModerador> productoModerador;

    @ElementCollection
    @Column(nullable = false)
    private List<String> imagen;

    @ElementCollection
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private  List<Categoria> categoria;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private  Estado estado;

    @OneToMany(mappedBy = "codigoProducto")
    private List<DetalleTransaccion> detalleTransaccion;

    @OneToMany(mappedBy = "producto")
    private List<Chat> chat;
}
