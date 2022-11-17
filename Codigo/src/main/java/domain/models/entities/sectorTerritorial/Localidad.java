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

    @OneToMany
    @JoinColumn(name = "provincia_id")
    private Provincia provincia;

    public Localidad() {
    }

    public Localidad(String nombre, Provincia provincia) {
        this.nombre = nombre;
        this.provincia = provincia;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
