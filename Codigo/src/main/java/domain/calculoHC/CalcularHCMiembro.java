package domain.calculoHC;

import domain.consumo.FactorDeEmision;
import domain.recorridos.Tramo;
import domain.recorridos.Trayecto;
import domain.transporte.Transporte;
import domain.transporte.TransporteAnalogico;

import java.io.IOException;
import java.util.List;

public class CalcularHCMiembro {

    private FactorDeEmision fe = new FactorDeEmision();


    public double calcularHC(List<Trayecto> trayectos) throws IOException {

        double HC = 0;

        for (Trayecto trayecto : trayectos){
            for (Tramo tramo : trayecto.getTramos()){
                if (tramo.getMedioDeTransporte() == TransporteAnalogico) break;
                fe.calcularFETransporte(tramo.getMedioDeTransporte());
                HC += (tramo.distanciaTramo() * fe) / tramo.getMiembrosMismoTransporte().size();
            }
        }
        return HC;
    }
}
