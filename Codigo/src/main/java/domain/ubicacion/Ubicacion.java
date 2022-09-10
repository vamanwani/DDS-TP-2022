package domain.ubicacion;

import domain.sectorTerritorial.Localidad;

import javax.persistence.*;
import java.util.Locale;

@Entity
@Table(name = "ubicacion")
public class Ubicacion extends PadreDeUbicacion{
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "calle")
    private String calle;

    @Column(name = "altura")
    private int altura;

    @ManyToOne
    @JoinColumn(name = "localidad_id")
    private Localidad nombreLocalidad;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ubicacion() {
    }

    public int idLocalidad(){
        return nombreLocalidad.getId();
    }

    public Ubicacion(String calle, int altura, Localidad nombreLocalidad) {
        this.calle = calle;
        this.altura = altura;
        this.nombreLocalidad = nombreLocalidad;
    }

    public String getCalle() {
        return calle;
    }

    public int getAltura() {
        return altura;
    }
}
