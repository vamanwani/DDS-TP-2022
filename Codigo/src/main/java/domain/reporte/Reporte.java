package domain.reporte;

import domain.organizacion.ClasificaciónDeOrg;
import domain.organizacion.Organizacion;
import domain.organizacion.Sector;
import domain.sectorTerritorial.Pais;
import domain.sectorTerritorial.SectorTerritorial;

public class Reporte {
    public Diccionario contenidoReporteComposicionOrganizacion(Organizacion organizacion){
        //TODO Diccionario que tenga una lista con los componentes de la organizacion(sectores) y su valor de HC
    }
    public Diccionario contenidoReporteComposicionPais(Pais pais){

    }
    public Diccionario contenidoReporteComposicionSectorTerritorial(SectorTerritorial sectorTerritorial){

    }
    public Diccionario contenidoReporteEvolucionOrganizacion(Organizacion organizacion){
        //TODO Diccionario que tenga actualizaciones con fecha, hora y valor de HC de una organizacion
    }
    public Diccionario contenidoReporteEvolucionSectorTerritorial(SectorTerritorial sectorTerritorial){

    }
    public Double contenidoReporteHCOrganizacion(ClasificaciónDeOrg clasificaciónDeOrg, Organizacion organizacion){
        //TODO Calculadora de organizacion
    }
    public Double contenidoReporteHCSectorTerritorial(SectorTerritorial sectorTerritorial){
        //TODO Sumatoria de las hc de cada organizacion (usando calculadora para c/u)
    }
}
