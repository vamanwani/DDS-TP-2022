package domain.sectorTerritorial;

import domain.consumo.PeriodoDeImputacion;
import domain.organizacion.Organizacion;

import java.util.List;

public class SectorTerritorial {
    private String nombre;
    private AgenteSectorial agenteSectorial;
    private List<String> localidades;
    private List<Organizacion> organizaciones;
    private TipoSectorTerritorial tipoSector;

    public void agregarOrganizacion(Organizacion organizacion){
        if(localidades.contains(organizacion.getUbicacion().idLocalidad()) && !organizaciones.contains(organizacion)){
            organizaciones.add(organizacion);
        }
    }

    public double calcularHCSectorTerritorial(){
        PeriodoDeImputacion periodoDeImputacion = null;
        return organizaciones.stream().mapToDouble(o -> o.calcularHCOrganizacion(periodoDeImputacion)).sum();
    }

}
