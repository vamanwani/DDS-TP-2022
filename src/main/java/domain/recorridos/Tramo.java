package domain.recorridos;

import domain.transporte.Transporte;
import domain.ubicacion.Ubicacion;

public class Tramo {
    private Transporte medioDeTransporte;
    private Ubicacion puntoFin;
    private Ubicacion puntoInicio;

    public Tramo(Transporte medioDeTransporte, Ubicacion puntoFin, Ubicacion puntoInicio) {
        this.medioDeTransporte = medioDeTransporte;
        this.puntoFin = puntoFin;
        this.puntoInicio = puntoInicio;
    }
}
