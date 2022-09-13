package domain.reporte;

import domain.organizacion.Organizacion;
import domain.organizacion.Sector;

import java.util.ArrayList;
import java.util.List;

public class ReporteHCComposicionOrganizacion implements ReportableComposicion{
    private Organizacion organizacion;
    public List<Double> contenidoReporteComposicion(){
        List<Double>  consumoSectores = new ArrayList<>();
        for(Sector sector : organizacion.getSectores()){
            consumoSectores.add(sector.calcularHCSector());
        }
        return consumoSectores;
    }

}
