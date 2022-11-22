package domain.models.entities.sectorTerritorial;

import javax.persistence.*;

@Entity
@Table(name = "localidad")
public class Localidad {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    public Localidad() {
    }

    public Localidad(String nombre, Provincia provincia) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
