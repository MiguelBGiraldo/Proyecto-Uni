package co.edu.uniquindio.proyecto.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Transaccion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int codigo;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(nullable = false)
    private double valorTotal;

    @Column(nullable = false)
    //@Enumerated(EnumType.STRING)
    private MetodoPago metodoPago;

    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "codigoTransaccion")
    private List<DetalleTransaccion> detalleTransaccion;

}
