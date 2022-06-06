package domain.consumo;

//import com.sun.org.apache.xerces.internal.parsers.IntegratedParserConfiguration;

public class PeriodoDeImputacion {
    private int anio;
    private int mes;
    private TipoPeriodicidad periodicidad;

    public void definirPeriodicidad(String formato){
        if(formato.length() == 7){
            anio = Integer.parseInt(formato.substring(3, 6)); //Ultimos 4 elementos
            mes = Integer.parseInt(formato.substring(0,1));//Primeros 2 elementos
            setPeriodicidad(TipoPeriodicidad.MENSUAL);        }
        else{
            anio = Integer.parseInt(formato);
            setPeriodicidad(TipoPeriodicidad.ANUAL);
        }
    }

    public void setPeriodicidad(TipoPeriodicidad periodicidad) {
        this.periodicidad = periodicidad;
    }
}
