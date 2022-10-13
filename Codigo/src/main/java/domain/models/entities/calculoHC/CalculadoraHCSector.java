package domain.models.entities.calculoHC;

import domain.models.entities.miembro.Miembro;

import java.io.IOException;
import java.util.List;

public class CalculadoraHCSector {

    public double calcularHC(List<Miembro> miembros){
        return miembros.stream().mapToDouble( m -> {
            try {
                return m.calcularHCMiembro();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return 0;
        }).sum();
    }
}
