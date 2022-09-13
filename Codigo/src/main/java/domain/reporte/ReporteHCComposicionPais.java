package domain.reporte;

import domain.organizacion.Organizacion;
import domain.sectorTerritorial.Pais;
import domain.sectorTerritorial.SectorTerritorial;

import java.util.ArrayList;
import java.util.List;

public class ReporteHCComposicionPais implements ReportableComposicion{
    private Pais pais;
    public List<Double> contenidoReporteComposicion(){
        List<Double> valores = new ArrayList<>();
        for(SectorTerritorial st : pais.getSectoresTerritoriales()){
            valores.add(st.calcularHCSectorTerritorial());
        }
        return valores;
    }
}

