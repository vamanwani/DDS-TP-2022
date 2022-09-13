package domain.recorridos;

import domain.miembro.Miembro;
import domain.transporte.Transporte;
import domain.transporte.TransporteAnalogico;
import domain.ubicacion.Ubicacion;
import org.jetbrains.annotations.NotNull;

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
}
