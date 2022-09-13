package domain.reporte;

import java.time.LocalDate;

public class Actualizacion {
    private LocalDate fecha;
    private Double hcActualizado;
    public Actualizacion(LocalDate fecha, Double hcActualizado){
        this.fecha = fecha;
        this.hcActualizado = hcActualizado;
    }
}
