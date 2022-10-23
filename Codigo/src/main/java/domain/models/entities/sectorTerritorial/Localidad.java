package domain.models.entities.sectorTerritorial;

import javax.persistence.*;

@Entity
@Table(name = "localidad")
public class Localidad {
    @Id
    @GeneratedValue
    @Column(name = "id_localidad")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    public Localidad(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

}
