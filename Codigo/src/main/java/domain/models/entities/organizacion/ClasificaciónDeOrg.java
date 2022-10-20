package domain.models.entities.organizacion;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clasificacion_org")
public class ClasificaciónDeOrg {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "nombre")
    private String nombre;

    public ClasificaciónDeOrg() {
    }

    public ClasificaciónDeOrg(String nombre) {
        this.nombre = nombre;
    }



    public String getNombre() {
        return nombre;
    }
}
