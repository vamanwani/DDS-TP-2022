package domain.models.entities.consumo;

import javax.persistence.*;

//import com.sun.org.apache.xerces.internal.parsers.IntegratedParserConfiguration;
@Entity
@Table(name = "periodoDeImputacion")
public class PeriodoDeImputacion {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "anio")
    private int anio;
    @Column(name = "mes")
    private int mes;
    @Enumerated(EnumType.STRING)
    @Column(name = "periodicidad")
    private TipoPeriodicidad periodicidad; // enum


    public PeriodoDeImputacion() {
    }

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

    public PeriodoDeImputacion(int mes, int anio, String periodicidad){
        this.mes = mes;
        this.anio = anio;
        if (periodicidad.contains("ANUAL"))
        {
            setPeriodicidad(TipoPeriodicidad.ANUAL);
        }else {
            setPeriodicidad(TipoPeriodicidad.MENSUAL);
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

    public boolean periodoMayor(PeriodoDeImputacion p1, PeriodoDeImputacion p2){
        if (p1.getMes() > p2.getMes() && p1.getAnio() > p2.getAnio()) return true;
        else  return false;
    }

}
