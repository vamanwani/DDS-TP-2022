package domain.calculoHC;

import domain.consumo.Consumo;
import domain.consumo.PeriodoDeImputacion;
import domain.consumo.TipoPeriodicidad;
import domain.organizacion.Organizacion;

import java.util.List;
import java.util.stream.Collectors;

public class CalculadoraHCOrganizacion {


    //if (tipo periodicidad == mes)
    //filter segun ese mes/anio
    // else // si o si va a ser anio
    //filter segun ese anio
    //hc de lo filtrado;

    public double calcularHC(List<Consumo> consumos, PeriodoDeImputacion periodoACalcular) {
        List<Consumo> auxiliar = null;

        if (periodoACalcular.getPeriodicidad() == TipoPeriodicidad.ANUAL) {
            auxiliar = consumos.stream().filter(consumo -> consumo.getPeriodicidad().getAnio() == periodoACalcular.getAnio()).collect(Collectors.toList());
        } else if (periodoACalcular.getPeriodicidad() == TipoPeriodicidad.MENSUAL) {
            auxiliar = consumos.stream().filter(consumo -> (consumo.getPeriodicidad().getAnio() == periodoACalcular.getAnio()
                    && consumo.getPeriodicidad().getMes() == periodoACalcular.getMes())).collect(Collectors.toList());
        }

        return auxiliar.stream().mapToDouble(c -> c.calcularHC()).sum();
    }
}
