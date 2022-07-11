package domain.calculoHC;

import domain.consumo.FactorEmision;
import domain.recorridos.Tramo;
import domain.recorridos.Trayecto;

import java.io.IOException;
import java.util.List;

public class CalculdorarHCMiembro {

    private FactorEmision fe = new FactorEmision();

    public void setFe(FactorEmision fe) {
        this.fe = fe;
    }

    public double calcularHC(List<Trayecto> trayectos) throws IOException {

        double HC = 0;

        for (Trayecto trayecto : trayectos){
            for (Tramo tramo : trayecto.getTramos()){
                if (tramo.getMedioDeTransporte().getId() == "Transporte analogico") break;
                HC += (tramo.distanciaTramo() * fe.getFeTramo()) / tramo.getMiembrosMismoTransporte().size();
            }
        }
        return HC;
    }

}
