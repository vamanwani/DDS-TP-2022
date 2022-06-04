package domain.ubicacion;

public class Ubicacion extends PadreDeUbicacion{
    private String calle;
    private int altura;
    private String nombreLocalidad;

    public int idLocalidad(){
        return this.obtenerIdLocalidad(nombreLocalidad);
    }

    public Ubicacion(String calle, int altura, String nombreLocalidad) {
        this.calle = calle;
        this.altura = altura;
        this.nombreLocalidad = nombreLocalidad;
    }

    public String getCalle() {
        return calle;
    }

    public int getAltura() {
        return altura;
    }
}
