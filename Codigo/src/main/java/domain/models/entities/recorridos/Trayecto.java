package domain.models.entities.recorridos;
import domain.models.entities.consumo.PeriodoDeImputacion;
import domain.models.entities.miembro.Miembro;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
//import java.util.arrayList;
//@Entity
@Entity
@Table(name = "trayecto")
public class Trayecto {
    @Id
    @GeneratedValue
    @Column(name = "id_trayecto")
    private Integer id;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tramo> tramos = new ArrayList<>();

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "miembro_id")
    private Miembro miembro;

    @Column(name = "estado")
    private boolean estado = true;

    @ManyToOne
    @JoinColumn(name = "periodo_imputacion_id")
    private PeriodoDeImputacion periodoDeImputacion;

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    @Column(name = "fecha")
    private LocalDate fecha;

    public Trayecto() {
    }

    public Trayecto(Tramo @NotNull ... tramos) {
        for(Tramo tramo : tramos){
            this.tramos.add(tramo);
        }
    }

    public double calculoDistanciaTotal() throws IOException {
        return tramos.stream().mapToDouble(tramo -> {
            try {
                return tramo.distanciaTramo();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).sum();
    }

    public void setEstado(boolean bool){
        this.estado = bool;
    }
    public List<Tramo> getTramos() {
        return tramos;
    }

    public Integer getId() {
        return id;
    }

    public void setTramos(List<Tramo> tramos) {
        this.tramos = tramos;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void agregarTramo(Tramo tramo){this.tramos.add(tramo);}

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getCantidadTramos(){return this.tramos.size();}

    public Miembro getMiembro() {
        return miembro;
    }

    public void setMiembro(Miembro miembro) {
        this.miembro = miembro;
    }

    public void setPeriodoDeImputacion(PeriodoDeImputacion periodoDeImputacion) {
        this.periodoDeImputacion = periodoDeImputacion;
    }

    public PeriodoDeImputacion getPeriodoDeImputacion() {
        return periodoDeImputacion;
    }

    public boolean isEstado() {
        return estado;
    }
}
