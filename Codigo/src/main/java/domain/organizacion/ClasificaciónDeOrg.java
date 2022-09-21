package domain.organizacion;

import javax.persistence.*;

@Entity
@Table(name = "clasificacion_org")
public class ClasificaciónDeOrg {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "clasificacion")
    private String nombre;

    public ClasificaciónDeOrg(String nombre) {
        this.nombre = nombre;
    }
}
