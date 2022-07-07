package domain.consumo;

public class ConsumoLogistica extends Consumo{
    private String logistica;
    private String medio;
    private int peso;
    private int distancia;

    public ConsumoLogistica(Actividad actividad, PeriodoDeImputacion periodicidad, TipoConsumo tipoConsumo, int peso, int distancia, String medio, String logistica) {
        this.actividad = actividad;
        this.periodicidad = periodicidad;
        this.tipoConsumo = tipoConsumo;
        this.peso = peso;
        this.distancia = distancia;
        this.medio = medio;
        this.logistica = logistica;
    }

    public double calcularHC(){
        double HC;
        HC = this.distancia * this.peso * this.factorEmision() * this.valorDeK(); // de donde sacamos la k?
        return HC;
    }

    //TODO
    public double valorDeK(){
            //Depende del peso y la distancia
        return 1;
    }
}
