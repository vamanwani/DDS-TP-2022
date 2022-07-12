package domain.services.entities;

public class Distancia {
    String valor;
    String unidad;

    public Distancia(String valor, String unidad) {
        this.valor = valor;
        this.unidad = unidad;
    }



    public Distancia getDistanciaClass(){
        return this;
    }
    public double distancia(){
        return Double.parseDouble(this.valor);
    }

}
