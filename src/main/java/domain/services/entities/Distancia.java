package domain.services.entities;

public class Distancia {
    String valor;
    String unidad;

    public double distancia(){
        return Double.parseDouble(this.valor);
    }
}
