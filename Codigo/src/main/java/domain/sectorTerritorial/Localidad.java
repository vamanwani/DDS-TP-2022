package domain.sectorTerritorial;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


public class Localidad {
    public Integer getId() {
        return id;
    }

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "nombre")
    private String nombre;



}
