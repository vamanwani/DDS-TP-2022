package domain.models.entities.sectorTerritorial;

import domain.models.entities.miembro.Usuario;

import javax.persistence.*;

@Entity
@Table(name = "agente_sectorial")
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
