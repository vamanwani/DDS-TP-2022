package domain.consumo;

public class Consumo {

    protected Actividad actividad;
    protected PeriodoDeImputacion periodicidad;
    protected int proximaAcreditacionConsumo;
    protected TipoConsumo tipoConsumo;



    public Double factorEmision(){
        String tipoConsumo = this.tipoConsumo.getNombre();
        String tipoActividad = this.actividad.getNombre();
        //Dependiendo de los dos tipos, se saca el FE (todavia no sabemos como)
        return null;
    }

    public PeriodoDeImputacion getPeriodicidad() {
        return periodicidad;
    }

    public double calcularHC() {
        return 0;
    }

}
