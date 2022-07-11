package domain.consumo;

public class ConsumoLogistica extends Consumo{
    private String categoria;
    private String medio;
    private double peso;
    private double distancia;

    public ConsumoLogistica(Actividad actividad, PeriodoDeImputacion periodicidad, TipoConsumo tipoConsumo, int peso, int distancia, String medio, String categoria) {
        this.actividad = actividad;
        this.periodicidad = periodicidad;
        this.tipoConsumo = tipoConsumo;
        this.peso = peso;
        this.distancia = distancia;
        this.medio = medio;
        this.categoria = categoria;
    }

    @Override
    public double calcularHC(){

        // analiza periodicidad
        // llama al metodo que le corresponda

        //HC = DISTANCIA x PESO x FE X K, donde K es un factor que afecta al cálculo según el peso y la distancia
        return (peso * distancia * this.valorDeK() * tipoConsumo.getValorParaFE());
    }

    //TODO
    public double valorDeK(){
            //Depende del peso y la distancia
        return 1;
    }

}
