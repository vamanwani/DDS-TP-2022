package domain.models.entities.sectorTerritorial;

import domain.models.entities.miembro.Usuario;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Embeddable
public class AgenteSectorial {
    @Column(name = "nombreAgente")
    private String nombre;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
