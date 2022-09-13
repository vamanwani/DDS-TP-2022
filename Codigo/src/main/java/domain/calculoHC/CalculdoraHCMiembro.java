package domain.calculoHC;

import domain.consumo.FactorDeEmision;
import domain.recorridos.Tramo;
import domain.recorridos.Trayecto;

import java.io.IOException;
import java.util.List;

public class CalculdoraHCMiembro {

    private FactorDeEmision fe = new FactorDeEmision();

    public double calcularHC(List<Trayecto> trayectos) throws IOException {
        double HC = 0;
        for (Trayecto trayecto : trayectos) {
            for (Tramo tramo : trayecto.getTramos()) {
                if (tramo.getMedioDeTransporte().getId() == "Transporte analogico") {
                    HC += 0;
                } else {
                    HC += (tramo.distanciaTramo() * fe.getValorFE()) / tramo.getMiembrosMismoTransporte().size();
                }
            }
        }
        return HC;
    }
}
