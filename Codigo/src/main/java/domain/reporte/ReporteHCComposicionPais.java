package domain.reporte;

import domain.organizacion.Organizacion;
import domain.sectorTerritorial.Pais;
import domain.sectorTerritorial.SectorTerritorial;
import domain.sectorTerritorial.TipoSectorTerritorial;

import java.util.ArrayList;
import java.util.List;

public class ReporteHCComposicionPais implements ReportableComposicion{
    private Pais pais;
    public List<Double> contenidoReporteComposicion(){
        List<Double> valores = new ArrayList<>();
        for(SectorTerritorial st : pais.getSectoresTerritoriales()){
            if(st.getTipoSector()== TipoSectorTerritorial.PROCVINCIAS){
                valores.add(st.calcularHCSectorTerritorial());
            }
        }
        return valores;
    }
}

