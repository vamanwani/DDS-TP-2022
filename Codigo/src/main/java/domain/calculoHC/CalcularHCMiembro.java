package domain.calculoHC;

import domain.recorridos.Tramo;
import domain.recorridos.Trayecto;

import java.util.List;

public class CalcularHCMiembro {

    public double calcularHC(List<Trayecto> trayectos){
            return trayectos.stream().mapToDouble( t -> t.huellaCarbono()).sum();
    }

}
