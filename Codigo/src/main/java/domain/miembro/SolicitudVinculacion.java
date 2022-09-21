package domain.miembro;

import com.sun.org.apache.xpath.internal.operations.Bool;
import domain.organizacion.Organizacion;

import javax.persistence.*;

@Entity
@Table(name = "solicitud_vinculacion")
public class SolicitudVinculacion {

    @Id
    @GeneratedValue
    @Column(name = "id_solicitud")
    private int id;


    @ManyToOne
    @JoinColumn(name = "miembro_miembro_id")
    private Miembro miembro;
    @ManyToOne
    @JoinColumn(name = "organizacion_id_organizacion")
    private Organizacion organizacion;
    private String descripcion;
    private Boolean aceptada = false;

    protected SolicitudVinculacion() {
    }

    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
    }

    public Miembro getMiembro() {
        return miembro;
    }

    public void setMiembro(Miembro miembro) {
        this.miembro = miembro;
    }


    public SolicitudVinculacion(Miembro miembro, Organizacion organizacion){
        this.miembro = miembro;
        this.organizacion = organizacion;
    }

}
