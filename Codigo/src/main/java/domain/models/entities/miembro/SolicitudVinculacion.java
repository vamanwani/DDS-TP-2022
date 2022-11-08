package domain.models.entities.miembro;

import domain.models.entities.organizacion.Organizacion;
import domain.models.entities.organizacion.Sector;

import javax.persistence.*;

@Entity
@Table(name = "solicitudVinculacion")
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

    @ManyToOne
    @JoinColumn(name = "sector_id")
    private Sector sector;

    public Sector getSector() {
        return sector;
    }

    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_solicitud")
    private domain.models.entities.miembro.EstadoSolicitud estadoSolicitud = EstadoSolicitud.PENDIENTE;

    public EstadoSolicitud getEstadoSolicitud() {
        return estadoSolicitud;
    }

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


    public SolicitudVinculacion(Miembro miembro, Organizacion organizacion, Sector sector){
        this.miembro = miembro;
        this.organizacion = organizacion;
        this.sector = sector;
    }

    public String getMail(){return getMiembro().getMail();}
    public String getTelefono(){return getMiembro().getTelefono();}
    public String getNombre(){return getMiembro().getNombreYApellido();}

    public int getId() {
        return id;
    }

    public void setEstadoSolicitud(EstadoSolicitud estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }
}
