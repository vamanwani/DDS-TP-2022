package domain.reporte;

import domain.consumo.PeriodoDeImputacion;
import domain.organizacion.Organizacion;
import domain.sectorTerritorial.SectorTerritorial;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReporteHCComposicionSectorTerritorial implements ReportableComposicion{
    private SectorTerritorial sector;
    public List<Double> contenidoReporteComposicion() throws IOException {
        List<Double> valores = new ArrayList<>();
        for(Organizacion organizacion : sector.getOrganizaciones()){
            valores.add(organizacion.calcularHCOrganizacion(null));
        }
        return valores;
    }
}
