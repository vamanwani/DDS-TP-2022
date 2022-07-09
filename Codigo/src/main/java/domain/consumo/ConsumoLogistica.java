package domain.consumo;

import domain.calculoHC.EstrategiaDeCalculo;

public class ConsumoLogistica extends Consumo{
    private String logistica;
    private String medio;
    private int peso;
    private int distancia;

    public ConsumoLogistica(Actividad actividad, PeriodoDeImputacion periodicidad, TipoConsumo tipoConsumo, int peso, int distancia, String medio, String logistica, EstrategiaDeCalculo estrategiaDeCalculo) {
        this.actividad = actividad;
        this.periodicidad = periodicidad;
        this.tipoConsumo = tipoConsumo;
        this.peso = peso;
        this.distancia = distancia;
        this.medio = medio;
        this.logistica = logistica;
        this.estrategiaDeCalculo = estrategiaDeCalculo;
    }

    public double calcularHC(){
        return this.estrategiaDeCalculo.calcular(this);
    }

    //TODO
    public double valorDeK(){
            //Depende del peso y la distancia
        return 1;
    }

    HC = DISTANCIA x PESO x FE X K, donde K es un factor que afecta al cálculo según el peso y la distancia

    valor = peso * distacia * valorDeK();
}
