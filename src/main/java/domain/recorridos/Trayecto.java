package domain.recorridos;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;

public class Trayecto {
    private ArrayList<Tramo> tramos;

    public Trayecto(Tramo @NotNull ... tramos) {
        for(Tramo tramo : tramos){
            this.tramos.add(tramo);
        }

    }

    public double calculoDistanciaTotal() throws IOException {
        return tramos.stream().mapToDouble(tramo -> {
            try {
                return tramo.distanciaTramo();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).sum();
    }

}
