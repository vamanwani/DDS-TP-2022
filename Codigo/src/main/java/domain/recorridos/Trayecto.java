package domain.recorridos;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
//import java.util.arrayList;

public class Trayecto {

    private List<Tramo> tramos;

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

    public List<Tramo> getTramos() {
        return tramos;
    }

}
