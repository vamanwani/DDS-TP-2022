package domain.models.entities.sectorTerritorial;

import domain.models.entities.miembro.Miembro;
import domain.models.entities.miembro.Usuario;
import org.bouncycastle.jcajce.provider.symmetric.CAST5;

import javax.persistence.*;

@Entity
@Table(name = "agenteSectorial")
public class AgenteSectorial {

    @Id
    @GeneratedValue
    @Column(name = "agente_id")
    private int id;

    @Column(name = "nombreAgente")
    private String nombre;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public AgenteSectorial() {
    }

    public AgenteSectorial(String nombre, Usuario usuario) {
        this.nombre = nombre;
        this.usuario = usuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
