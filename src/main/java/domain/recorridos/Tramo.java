package domain.recorridos;

import domain.miembro.Miembro;
import domain.transporte.Transporte;
import domain.ubicacion.Ubicacion;

import java.util.ArrayList;
import java.util.List;

public class Tramo {
    private Transporte medioDeTransporte;
    private Ubicacion puntoFin;
    private Ubicacion puntoInicio;
    private List<Miembro> miembrosMismoTransporte;

    public Tramo(Transporte medioDeTransporte, Ubicacion puntoFin, Ubicacion puntoInicio) {
        this.medioDeTransporte = medioDeTransporte;
        this.puntoFin = puntoFin;
        this.puntoInicio = puntoInicio;
        this.miembrosMismoTransporte = new ArrayList<>();
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

    public double distancia(){
        //TODO
        return 0.00;
    }

}
