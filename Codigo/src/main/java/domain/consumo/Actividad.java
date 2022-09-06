package domain.consumo;

import javax.persistence.*;

@Entity
@Table(name = "actividad")
public class Actividad implements AsignableFE {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "alacnce")
    private TipoAlcance alcance;

    @Column(name = "tipoActividad")
    private String nombre;

    @Transient
    private double valorParaFE;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Actividad() {
    }

    public Actividad(TipoAlcance alcance, String nombre) {
        this.alcance = alcance;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setValorParaFE(double valorParaFE) {
        this.valorParaFE = valorParaFE;
    }
}
