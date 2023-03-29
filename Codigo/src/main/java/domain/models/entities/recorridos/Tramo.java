package domain.models.entities.recorridos;

import domain.models.entities.miembro.Miembro;
import domain.models.entities.ubicacion.Ubicacion;
import domain.models.entities.transporte.Transporte;

import javax.persistence.*;
import java.util.ArrayList;


import java.io.IOException;
import java.util.*;
@Entity
@Table(name = "tramo")
public class Tramo {
    @Id
    @GeneratedValue
    @Column(name = "id_tramo")
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ubicacionInicio_id")
    private Ubicacion puntoInicio;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ubicacionFin_id")
    private Ubicacion puntoFin;

    @OneToOne
    @JoinColumn(name = "transporte_id")
    private Transporte medioDeTransporte;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Miembro> miembros;

    public Tramo() {
        this.miembros = new ArrayList<>();

    }

    public Tramo(Transporte medioDeTransporte, Ubicacion puntoFin, Ubicacion puntoInicio) {
        this.medioDeTransporte = medioDeTransporte;
        this.puntoFin = puntoFin;
        this.puntoInicio = puntoInicio;
        this.miembros = new ArrayList<>();
    }

    public void agregarMiembroAlTramo(Miembro miembro){
        this.miembros.add(miembro);
    }
    public List<Miembro> getMiembrosMismoTransporte() {
        return miembros;
    }

    public boolean esCompartido(){
        return miembros.size() > 1;
    }

    public double distanciaTramo() throws IOException {
        return medioDeTransporte.distancia(puntoInicio,puntoFin);
    }

    public Transporte getMedioDeTransporte() {
        return medioDeTransporte;
    }

    public void setMedioDeTransporte(Transporte medioDeTransporte) {
        this.medioDeTransporte = medioDeTransporte;
    }

    public void setPuntoFin(Ubicacion puntoFin) {
        this.puntoFin = puntoFin;
    }

    public void setPuntoInicio(Ubicacion puntoInicio) {
        this.puntoInicio = puntoInicio;
    }

    public String getMedioTransporte(){
        return medioDeTransporte.getClass().getName();
    }

    public Integer getId() {
        return id;
    }

    public Ubicacion getPuntoInicio() {
        return puntoInicio;
    }

    public Ubicacion getPuntoFin() {
        return puntoFin;
    }

    public String getMedioTransporteNombre(){return this.medioDeTransporte.getNombre();}

    public List<Miembro> getMiembros() {
        return miembros;
    }
}
