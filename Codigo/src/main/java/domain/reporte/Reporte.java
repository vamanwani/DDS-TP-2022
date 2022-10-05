package domain.reporte;

import domain.consumo.PeriodoDeImputacion;
import domain.organizacion.ClasificaciónDeOrg;
import domain.organizacion.Organizacion;
import domain.organizacion.Sector;
import domain.sectorTerritorial.Pais;
import domain.sectorTerritorial.SectorTerritorial;

import java.io.IOException;
import java.time.Period;
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
    public HashMap contenidoReporteHCOrganizacion(List<ClasificaciónDeOrg> clasificaciónDeOrgList, List<Organizacion> organizacionList) throws IOException {
        HashMap<String, Double> map = new HashMap<String, Double>();
        for (int i = 0; i < clasificaciónDeOrgList.size(); i++){
            double hcDeLaClasificacion = 0;
            for (int j = 0; j < organizacionList.size(); j++){
                if (organizacionList.get(j).getClasificacionDeOrg() == clasificaciónDeOrgList.get(i)) {
                    hcDeLaClasificacion += organizacionList.get(j).calcularHCOrgHistorico();
                }
            }
            map.put(clasificaciónDeOrgList.get(i).getNombre(), hcDeLaClasificacion);
        }
        return map;
    }

    public Double contenidoReporteHCSectorTerritorial(SectorTerritorial sectorTerritorial) throws IOException{
        //Sumatoria de las hc de cada organizacion (usando calculadora para c/u)
            return sectorTerritorial.calcularHCSectorHistorico();
    }

}
