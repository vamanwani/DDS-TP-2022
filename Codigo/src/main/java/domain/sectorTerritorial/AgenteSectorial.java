package domain.sectorTerritorial;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AgenteSectorial {
    @Column(name = "nombre")
    private String nombre;
}
