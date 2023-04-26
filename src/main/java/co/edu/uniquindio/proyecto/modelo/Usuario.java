package co.edu.uniquindio.proyecto.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Usuario extends Persona implements Serializable {

    @Column(nullable = false, unique = true, length = 12)
    private String telefono;

    @Column(nullable = false, length = 100)
    private String direccion;


    //Aca va la referencia
    @OneToMany(mappedBy = "usuario")
    private List<Producto> productos;

    @OneToMany(mappedBy = "codigoUsuario")
    private List<Favorito> favoritos;

    @OneToMany(mappedBy = "usuario")
    private List<Transaccion> transacciones;

    @OneToMany(mappedBy = "codigoUsuario")
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "codigoValorado")
    private List<ValoracionVendedor> valoracion;

    @OneToMany(mappedBy = "codigoEvaluador")
    private  List<ValoracionVendedor> evaluacion;

    @OneToMany(mappedBy = "usuario")
    private List<Chat> chat;

    @OneToMany(mappedBy = "usuario2")
    private List<Chat> chat2;

    @OneToMany(mappedBy = "usuario")
    private List<Mensaje> mensaje;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private  EstadoUsuario estado;
}
