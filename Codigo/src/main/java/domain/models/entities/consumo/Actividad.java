package domain.models.entities.consumo;

import javax.persistence.*;

@Entity
@Table(name = "actividad")
public class Actividad implements AsignableFE {
    @Id
    @GeneratedValue
    @Column(name = "actividad_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "alcance")
    private TipoAlcance alcance;

    @Column(name = "tipoActividad")
    private String nombre;

    @Column(name = "valor_fe")
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
