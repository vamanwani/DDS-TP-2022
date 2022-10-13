package domain.models.entities.miembro;

import domain.models.entities.calculoHC.CalculdoraHCMiembro;
import domain.models.entities.consumo.PeriodoDeImputacion;
import domain.models.entities.organizacion.Organizacion;
import domain.models.entities.organizacion.Sector;
import domain.models.entities.recorridos.Trayecto;

import javax.persistence.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "miembro")
public class Miembro {
    @Id
    @GeneratedValue
    @Column(name = "miembro_id")
    private Long id;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "nroDoc")
    private int numeroDeDocumento;

    @Column(name = "tipoDoc")
    private String tipoDeDocumento;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "sector_id")
    private List<Sector> trabajos;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public List<Trayecto> getTrayectos() {
        return trayectos;
    }

    @OneToMany
    @JoinColumn(name = "trayecto_id")
    private List<Trayecto> trayectos;

    @Column(name = "mail")
    private String mail;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "link_recomendacion")
    private String linkRecomendacion;

    public Miembro() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Miembro(
            String apellido,
            String nombre,
            int numeroDeDocumento,
            String tipoDeDocumento,
            Usuario usuario)
    {
        this.apellido = apellido;
        this.nombre = nombre;
        this.numeroDeDocumento = numeroDeDocumento;
        this.tipoDeDocumento = tipoDeDocumento;
        this.trabajos = new ArrayList<>();
        this.usuario = usuario;
        this.trayectos = new ArrayList<>();
    }

    public Usuario getUsuario(){
        return this.usuario;
    }

    public void setTrayectos(List<Trayecto> trayectos) {
        this.trayectos = trayectos;
    }

    public void setTrabajos(List<Sector> trabajos) {
        this.trabajos = trabajos;
    }


    public double calcularDistanciaTotal() {

        return trayectos.stream().mapToDouble(trayecto -> {
            try {
                return trayecto.calculoDistanciaTotal();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).sum();

    }

    public void recibirRecomendacion(String link) {
        this.linkRecomendacion = link;
    }
    public double calcularHCMiembro() throws IOException {
        return new CalculdoraHCMiembro().calcularHC(trayectos);
    }

    public String getLinkRecomendacion(){
        return linkRecomendacion;
    }
    public double impactoEnOrganizacion(Organizacion organizacion) throws IOException {
        PeriodoDeImputacion periodoDeImputacion = null;
        return this.calcularHCMiembro()/organizacion.calcularHCOrganizacion(periodoDeImputacion);
    }

    public void generarSolicitud(Organizacion organizacion){
        new SolicitudVinculacion(this, organizacion);
    }
}
