package domain.models.entities.calculoHC;

import domain.models.entities.consumo.PeriodoDeImputacion;
import domain.models.entities.consumo.TipoPeriodicidad;
import domain.models.entities.miembro.Miembro;
import domain.models.entities.recorridos.Tramo;
import domain.models.entities.recorridos.Trayecto;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

public class CalculadoraHCSector {

    private static final DecimalFormat df = new DecimalFormat("0.00");

    public double calcularHCMensual(List<Miembro> miembros, PeriodoDeImputacion periodoDeImputacion) throws IOException {
        Double hc = Double.valueOf(0);
        for (Miembro m : miembros) {
            List<Trayecto> trayectos = (List<Trayecto>) m.getTrayectos().stream().
            filter(t -> (t.getPeriodoDeImputacion().getMes() == periodoDeImputacion.getMes() && t.getPeriodoDeImputacion().getAnio() == periodoDeImputacion.getAnio())).
            collect(Collectors.toList());
            hc += new CalculdoraHCMiembro().calcularHC(trayectos);
        }
        return hc;
    }

    public double calcularHCAnual(List<Miembro> miembros, PeriodoDeImputacion periodoDeImputacion) throws IOException {
        Double hc = Double.valueOf(0);
        for (Miembro m : miembros) {
            List<Trayecto> trayectos = (List<Trayecto>) m.getTrayectos().stream().
                    filter(t -> (t.getPeriodoDeImputacion().getAnio() == periodoDeImputacion.getAnio())).
                    collect(Collectors.toList());
            hc += new CalculdoraHCMiembro().calcularHC(trayectos);
        }
        return hc;
    }
}
