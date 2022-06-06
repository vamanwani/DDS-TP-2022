package domain.consumo;

public class Consumo {

    private Actividad actividad;
    private PeriodoDeImputacion periodicidad;
    private int proximaAcreditacionConsumo;
    private TipoConsumo tipoConsumo;
    private double valor;

    public Double factorEmision(Double valorTipoActividad, Double valorTipoConsumo){
        //TODO
        return null;
    }

    public PeriodoDeImputacion getPeriodicidad() {
        return periodicidad;
    }
}
