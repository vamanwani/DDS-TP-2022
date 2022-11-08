package domain.models.entities.reporte;

import domain.models.entities.consumo.PeriodoDeImputacion;
import domain.models.entities.organizacion.ClasificaciónDeOrg;
import domain.models.entities.organizacion.Organizacion;
import domain.models.entities.sectorTerritorial.Pais;
import domain.models.entities.sectorTerritorial.SectorTerritorial;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Reporte {

    public HashMap contenidoReporteComposicionOrganizacion(Organizacion organizacion){
        //Diccionario que tenga una lista con los componentes de la organizacion(sectores) y su valor de HC
        HashMap<String, Double> map = new HashMap<String, Double>();
        for (int i = 0; i < organizacion.getSectores().size(); i ++){
            map.put(organizacion.getSectores().get(i).getNombre(), organizacion.getSectores().get(i).calcularHCSector());
        }
        return map;
    }
    public HashMap contenidoReporteComposicionPais(Pais pais) throws IOException{
        HashMap<String, Double> map = new HashMap<String, Double>();
        for (SectorTerritorial sector : pais.getSectoresTerritoriales()){
            if (sector.esProvincia()){
                map.put(sector.getNombre(), sector.calcularHCSectorHistorico());
            }
        }
        return map;
    }
    public HashMap contenidoReporteComposicionSectorTerritorial(SectorTerritorial sectorTerritorial) throws IOException {
        HashMap<String,Double> map = new HashMap<String, Double>();
        for(int i=0; i< sectorTerritorial.getOrganizaciones().size(); i++){
            map.put(sectorTerritorial.getOrganizaciones().get(i).getRazonSocial(), sectorTerritorial.getOrganizaciones().get(i).calcularHCOrgHistorico());
        }
        return map;
    }

    public HashMap contenidoReporteEvolucionOrganizacion(Organizacion organizacion) throws IOException{
        //Diccionario que tenga actualizaciones con mes, año y valor de HC de una organizacion
        HashMap<String, Double> map = new HashMap<String, Double>();
        List<PeriodoDeImputacion> periododesDeImputacionDeLaOrganicacion = new ArrayList<>();

        for (int i = 0; i < organizacion.getConsumos().size(); i ++){ // ARMAR UNA LISTA DE LOS PERIODOS DE IMPUTACION
            if (!periododesDeImputacionDeLaOrganicacion.contains(organizacion.getConsumos().get(i).getPeriodicidad())){
                periododesDeImputacionDeLaOrganicacion.add(organizacion.getConsumos().get(i).getPeriodicidad());
            }
        }

        for (int j = 0; j < periododesDeImputacionDeLaOrganicacion.size(); j++){ // LLENAR EL HASMAP CON LOS HC DE CADA PERIODO
            double hc = organizacion.calcularHCOrganizacion(periododesDeImputacionDeLaOrganicacion.get(j));
            map.put(Integer.toString(periododesDeImputacionDeLaOrganicacion.get(j).getMes()) + "/" + Integer.toString(periododesDeImputacionDeLaOrganicacion.get(j).getAnio()), hc);
        }

        return map;
    }

    public HashMap contenidoReporteEvolucionSectorTerritorial(SectorTerritorial sectorTerritorial){
        HashMap<String, Double> map = new HashMap<String, Double>();
        List<PeriodoDeImputacion> periododesDeImputacionDelSectorT = new ArrayList<>();

        for (Organizacion organizacion : sectorTerritorial.getOrganizaciones()) {
            for (int i = 0; i < organizacion.getConsumos().size(); i++) { // ARMAR UNA LISTA DE LOS PERIODOS DE IMPUTACION
                if (!periododesDeImputacionDelSectorT.contains(organizacion.getConsumos().get(i).getPeriodicidad())) {
                    periododesDeImputacionDelSectorT.add(organizacion.getConsumos().get(i).getPeriodicidad());
                }
            }
        }
        for (int j = 0; j < periododesDeImputacionDelSectorT.size(); j++){ // LLENAR EL HASMAP CON LOS HC DE CADA PERIODO
            double hc = sectorTerritorial.calcularHCSectorTerritorial(periododesDeImputacionDelSectorT.get(j));
            map.put(Integer.toString(periododesDeImputacionDelSectorT.get(j).getMes()) + "/" + Integer.toString(periododesDeImputacionDelSectorT.get(j).getAnio()), hc);
        }

        return map;
    }

    //TODO ESTE REPORTE NO PUEDEN GENERARLO LOS MIEMBROS.
    //Organizaciones->contenidoReporteHCOrganizacion(this.clasificacion, [this])
    //AgenteSecorial->contenidoReporteHCOrganizacion(unaClasificacion, this.organizaciones)
        public HashMap contenidoReporteHCOrganizacion(String clasificaciónDeOrg, List<Organizacion> organizacionList) throws IOException {
        HashMap<String, Double> map = new HashMap<String, Double>();
            double hcDeLaClasificacion = 0;
            for (int j = 0; j < organizacionList.size(); j++){
                if (organizacionList.get(j).getClasificacionDeOrg().getNombre() == clasificaciónDeOrg) {
                    hcDeLaClasificacion += organizacionList.get(j).calcularHCOrgHistorico();
                }
            }
            map.put(clasificaciónDeOrg, hcDeLaClasificacion);

        return map;
    }

    public Double contenidoReporteHCSectorTerritorial(SectorTerritorial sectorTerritorial) throws IOException{
        //Sumatoria de las hc de cada organizacion (usando calculadora para c/u)
            return sectorTerritorial.calcularHCSectorHistorico();
    }

}
