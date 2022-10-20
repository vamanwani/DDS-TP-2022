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

    @Transient
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transporte_id")
    private Transporte medioDeTransporte;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ubicacionInicio_id")
    private Ubicacion puntoFin;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ubicacionFin_id")
    private Ubicacion puntoInicio;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "miembro_id")
    private List<Miembro> miembrosMismoTransporte;

    public Tramo() {
    }

    public Tramo(Transporte medioDeTransporte, Ubicacion puntoFin, Ubicacion puntoInicio) {
        this.medioDeTransporte = medioDeTransporte;
        this.puntoFin = puntoFin;
        this.puntoInicio = puntoInicio;
        this.miembrosMismoTransporte = new ArrayList<Miembro>();
    }

    public void agregarMiembroAlTramo(Miembro miembro){
        this.miembrosMismoTransporte.add(miembro);
    }
    public List<Miembro> getMiembrosMismoTransporte() {
        return miembrosMismoTransporte;
    }

    public boolean esCompartido(){
        return miembrosMismoTransporte.size() > 1;
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
}
