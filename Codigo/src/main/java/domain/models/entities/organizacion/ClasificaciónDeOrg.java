package domain.models.entities.organizacion;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clasificacion_org")
public class ClasificaciónDeOrg {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "clasificacion")
    private String nombre;

    public ClasificaciónDeOrg() {
    }

    public ClasificaciónDeOrg(String nombre) {
        this.nombre = nombre;
    }

    public List<Organizacion> organizacionList;

    public String getNombre() {
        return nombre;
    }
}
