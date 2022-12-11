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
import java.util.stream.Collectors;

@Entity
@Table(name = "miembro")
public class Miembro {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "nroDoc")
    private String numeroDeDocumento;

    @Column(name = "tipoDoc")
    private String tipoDeDocumento;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Sector> trabajos;

    public List<Sector> getTrabajos() {
        return trabajos;
    }

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany
    @JoinColumn(name = "miembro_id")
    private List<Trayecto> trayectos;

    @Column(name = "mail")
    private String mail;

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
            String numeroDeDocumento,
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

    public void agregarTrayecto(Trayecto trayecto) {this.trayectos.add(trayecto);}

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

    public double calcularHCMiembro(PeriodoDeImputacion periodoDeImputacion) throws IOException {
        List<Trayecto> trayectos = (List<Trayecto>) this.getTrayectos().stream().filter(t -> t.getPeriodoDeImputacion().equals(periodoDeImputacion)).collect(Collectors.toList());
        return new CalculdoraHCMiembro().calcularHC(trayectos);
    }

    public double calcularHCHistorico() throws IOException {
        return new CalculdoraHCMiembro().calcularHC(this.getTrayectos());
    }

    public String getLinkRecomendacion(){
        return linkRecomendacion;
    }

    public double impactoEnOrganizacion(Organizacion organizacion) throws IOException {
        PeriodoDeImputacion periodoDeImputacion = null;
        return this.calcularHCMiembro(periodoDeImputacion)/organizacion.calcularHCOrganizacion(periodoDeImputacion);
    }

    public SolicitudVinculacion generarSolicitud(Sector sector){
        return new SolicitudVinculacion(this, sector.getOrganizacion(), sector);
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNumeroDeDocumento(String  numeroDeDocumento) {
        this.numeroDeDocumento = numeroDeDocumento;
    }

    public void setTipoDeDocumento(String tipoDeDocumento) {
        this.tipoDeDocumento = tipoDeDocumento;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNombreYApellido(){return this.getNombre() + " " + this.getApellido();}

    public String getTelefono(){return this.getUsuario().getTelefono();}

    public void agregarTrabajo(Sector sector){trabajos.add(sector);}

    public List<Trayecto> getTrayectos() {
        return trayectos;
    }

}
