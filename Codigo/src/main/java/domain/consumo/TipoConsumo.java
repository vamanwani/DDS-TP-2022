package domain.consumo;


import javax.persistence.*;

@Entity
@Table(name = "tipoConsumo")
public class TipoConsumo implements AsignableFE{
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "unidad")
    private Unidad unidad;

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
