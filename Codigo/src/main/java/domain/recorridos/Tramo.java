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
    private Integer id;

    @Column(name = "medio de transporte")
    private Transporte medioDeTransporte;
    @Column(name = "puntoFin")
    private Ubicacion puntoFin;
    @Column(name = "puntoInicio")
    private Ubicacion puntoInicio;
    @Column(name = "miembroTransporte")
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
