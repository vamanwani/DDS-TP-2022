package domain.reporte;

import domain.sectorTerritorial.SectorTerritorial;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReporteHCEvolucionSectorTerritorial implements ReportableEvolucion{
    private SectorTerritorial sector;
    private List<Actualizacion> actualizaciones;
    public List<Actualizacion> contenidoReporteEvolucion(){
        List<Actualizacion> actualizados = new ArrayList<>();
        for(Actualizacion act : actualizaciones){
            new Actualizacion(null, sector.calcularHCSectorTerritorial());
            actualizados.add(act);
        }
        return actualizados;
    }
}

