package domain.miembro;

import domain.calculoHC.CalculdoraHCMiembro;
import domain.consumo.PeriodoDeImputacion;
import domain.organizacion.Organizacion;
import domain.organizacion.Sector;
import domain.recorridos.Trayecto;

import javax.persistence.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "miembro")
public class Miembro {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "nombre")
    private String nombre;

    //TODO Definir si documento es una entidad extra o no
    @Column(name = "nroDoc")
    private int numeroDeDocumento;

    @Column(name = "tipoDoc")
    private String tipoDeDocumento;

    @OneToMany
    private List<Sector> trabajos;

    @OneToOne //??????????????
    private Usuario usuario;

    @ManyToMany
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
}
