package domain.models.entities.sectorTerritorial;

import javax.persistence.*;

@Entity
@Table(name = "localidad")
public class Localidad {
    @Id
    @GeneratedValue //TODO no tiene que ser un valor autogenerado, tiene que instanciarse una vez en la BBDD y despues recuperarlo
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    public void setId(Integer id) {
        this.id = id;
    }

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
