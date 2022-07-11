package domain.consumo;

//import com.sun.org.apache.xerces.internal.parsers.IntegratedParserConfiguration;

public class PeriodoDeImputacion {
    private int anio;
    private int mes;
    private TipoPeriodicidad periodicidad; // enum

    public PeriodoDeImputacion(String formato) {
        this.definirPeriodicidad(formato);
    }

    public void definirPeriodicidad(String formato){
        if(formato.length() == 7){
            mes = Integer.parseInt(formato.substring(0,2));//Primeros 2 elementos
            anio = Integer.parseInt(formato.substring(3, 7)); //Ultimos 4 elementos
            setPeriodicidad(TipoPeriodicidad.MENSUAL);        }
        else{
            anio = Integer.parseInt(formato);
            mes = 0;
            setPeriodicidad(TipoPeriodicidad.ANUAL);
        }
    }

    public void setPeriodicidad(TipoPeriodicidad periodicidad) {
        this.periodicidad = periodicidad;
    }

    public TipoPeriodicidad getPeriodicidad() {
        return periodicidad;
    }

    public int getAnio() {
        return anio;
    }

    public int getMes() {
        return mes;
    }
}
