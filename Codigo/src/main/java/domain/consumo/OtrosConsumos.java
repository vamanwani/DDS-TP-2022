package domain.consumo;

import domain.calculoHC.EstrategiaDeCalculo;

public class OtrosConsumos extends Consumo{
    private double valorConsumo;

    public OtrosConsumos(Actividad actividad, PeriodoDeImputacion periodicidad, TipoConsumo tipoConsumo, double valor, EstrategiaDeCalculo estrategia) {
        this.actividad = actividad;
        this.periodicidad = periodicidad;
        this.tipoConsumo = tipoConsumo;
        this.valorConsumo = valorConsumo;
        this.estrategiaDeCalculo = estrategia;
    }

    public double calcularHC(){
        return this.valorConsumo * this.factorEmision();
    }

    public double getValorConsumo() {
        return valorConsumo;
    }
}
