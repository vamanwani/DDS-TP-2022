package domain.models.entities.ubicacion;

import domain.models.entities.sectorTerritorial.Localidad;
import domain.models.entities.sectorTerritorial.Provincia;

import javax.persistence.*;
import javax.swing.*;

@Entity
@Table(name = "ubicacion")
public class Ubicacion extends PadreDeUbicacion{
    @Id
    @GeneratedValue
    @Column(name = "id_ubicacion")
    private Long id;

    @Column(name = "calle")
    private String calle;

    @Column(name = "altura")
    private int altura;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "localidad")
    private Localidad nombreLocalidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provincia_id")
    private Provincia provincia;

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

    public String getDirec(){return this.getCalle() + " " +String.valueOf(this.getAltura());}
}
