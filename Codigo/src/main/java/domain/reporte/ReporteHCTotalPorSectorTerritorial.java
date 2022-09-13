package domain.reporte;

import domain.organizacion.ClasificaciónDeOrg;
import domain.organizacion.Organizacion;
import domain.sectorTerritorial.SectorTerritorial;

import java.util.List;

public class ReporteHCTotalPorSectorTerritorial implements ReportableHCTotal{
    private SectorTerritorial sector;

    public double contenidoReporteHC(){
        return sector.calcularHCSectorTerritorial();
    }
    


}
    