package domain.organizacion;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ClasificaciónDeOrg {
    @Column(name = "clasificacion")
    private String nombre;

    public ClasificaciónDeOrg(String nombre) {
        this.nombre = nombre;
    }
}
