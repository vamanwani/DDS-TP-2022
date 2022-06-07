package domain.ubicacion;

public class Parada {
    private Ubicacion localizacion;
    private Parada paradaAnterior;
    private double distanciaParadaAnterior;
    private Parada paradaSiguiente;
    private double distanciaParadaSiguiente;

    //TODO Hacer constructores opcionales por si no existe paradaAnterior o paradaSiguiente
    public Parada(Ubicacion localizacion, Parada paradaAnterior, Parada paradaSiguiente) {
        this.localizacion = localizacion;
        this.paradaAnterior = paradaAnterior;
        this.paradaSiguiente = paradaSiguiente;
    }
    public Ubicacion getLocalizacion(){
        return localizacion;
    }
    public Parada getParadaSiguiente(){return paradaSiguiente;}
    public double getDistanciaParadaAnterior() {
        return distanciaParadaAnterior;
    }

    public double getDistanciaParadaSiguiente() {
        return distanciaParadaSiguiente;
    }
}
