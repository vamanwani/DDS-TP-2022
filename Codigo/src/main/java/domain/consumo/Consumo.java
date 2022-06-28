package domain.consumo;

public class Consumo {

    private Actividad actividad;
    private PeriodoDeImputacion periodicidad;
    private int proximaAcreditacionConsumo;
    private TipoConsumo tipoConsumo;
    private double valor;

    public Consumo(Actividad actividad, PeriodoDeImputacion periodicidad, TipoConsumo tipoConsumo, double valor) {
        this.actividad = actividad;
        this.periodicidad = periodicidad;
        this.tipoConsumo = tipoConsumo;
        this.valor = valor;
    }

    public Double factorEmision(Double valorTipoActividad, Double valorTipoConsumo){
        //TODO
        return null;
    }

    public PeriodoDeImputacion getPeriodicidad() {
        return periodicidad;
    }

    public calcularHC(){
        this.actividad.
    }
}
