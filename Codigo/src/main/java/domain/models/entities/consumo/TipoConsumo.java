package domain.models.entities.consumo;


import javax.persistence.*;

@Entity
@Table(name = "tipo_consumo")
public class TipoConsumo implements AsignableFE{
    @Id
    @JoinColumn(name = "id_consumo")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(name = "unidad")
    private Unidad unidad;

    @Column(name = "valor_fe")
    private double valorParaFE;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoConsumo() {
    }

    public TipoConsumo(String nombre, Unidad unidad) {
        this.nombre = nombre;
        this.unidad = unidad;
    }

    public void setValorParaFE(double valorParaFE) {
        this.valorParaFE = valorParaFE;
    }

    public String getNombre() {
        return nombre;
    }

    public double getValorParaFE() {
        return valorParaFE;
    }
}
