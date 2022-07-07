package domain.consumo;

public class OtrosConsumos extends Consumo{
    private double valorConsumo;

    public OtrosConsumos(Actividad actividad, PeriodoDeImputacion periodicidad, TipoConsumo tipoConsumo, double valor) {
        this.actividad = actividad;
        this.periodicidad = periodicidad;
        this.tipoConsumo = tipoConsumo;
        this.valorConsumo = valorConsumo;
    }

    public double calcularHC(){
        return this.valorConsumo * this.factorEmision();
    }

}
