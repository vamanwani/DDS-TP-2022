package domain.consumo;

public class OtroConsumo extends Consumo{
    private double valorConsumo;

    public OtroConsumo(Actividad actividad, PeriodoDeImputacion periodicidad, TipoConsumo tipoConsumo, double valorConsumo) {
        this.actividad = actividad;
        this.periodicidad = periodicidad;
        this.tipoConsumo = tipoConsumo;
        this.valorConsumo = valorConsumo;
    }


    public double calcularHC(){
        return valorConsumo * tipoConsumo.getValorParaFE();
    }

    public double getValorConsumo() {
        return valorConsumo;
    }
}
