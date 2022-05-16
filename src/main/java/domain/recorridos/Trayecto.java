package domain.recorridos;

import java.util.ArrayList;

public class Trayecto {
    private ArrayList<Tramo> tramos;

    public Trayecto(Tramo ... tramos) {
        for(Tramo tramo : tramos){
            this.tramos.add(tramo);
        }

    }
}
