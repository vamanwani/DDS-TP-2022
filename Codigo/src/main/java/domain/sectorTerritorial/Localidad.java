package domain.sectorTerritorial;

import javax.persistence.*;

@Entity
@Table(name = "localidad")
public class Localidad {


    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    public Integer getId() {
        return id;
    }

}
