package domain.models.entities.reporte;

import domain.models.entities.calculoHC.CalculadoraHCSector;
import domain.models.entities.consumo.PeriodoDeImputacion;
import domain.models.entities.miembro.Miembro;
import domain.models.entities.organizacion.Organizacion;
import domain.models.entities.organizacion.Sector;
import domain.models.entities.sectorTerritorial.Pais;
import domain.models.entities.sectorTerritorial.SectorTerritorial;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

public class Reporte {

    CalculadoraHCSector calculadoraHCSector = new CalculadoraHCSector();
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public HashMap contenidoReporteComposicionOrganizacion(Organizacion organizacion, PeriodoDeImputacion periodoDeImputacion) throws IOException {
        //TODO DIAGRAMA DE PORCENTAJE DEL HC DE CADA SECTOR, QUE VA A SER EL HC RESULTANTE DE SUS MIEMBROS
        HashMap<String, Double> map = new HashMap<String, Double>();
        for(Sector sector : organizacion.getSectores()){
            map.put('"'+ sector.getNombre() + '"', Double.valueOf(df.format(sector.calcularHCSector(periodoDeImputacion))));
        }
        return map;
    }
    public HashMap contenidoReporteComposicionPais(Set<SectorTerritorial> sectores) throws IOException{
        //TODO DIAGRAMA DE PORCENTAJE DEL HC DE PROVINCIA, QUE VA A SER EL HC RESULTANTE DE SUS ORGS
        HashMap<String, Double> map = new HashMap<String, Double>();
        for (SectorTerritorial sector : sectores){
            if (sector.esProvincia()){
                map.put('"' + sector.getNombre() + '"', Double.valueOf(df.format(sector.calcularHCSectorHistorico())));
            }
        }
        return map;
    }
    public HashMap contenidoReporteComposicionSectorTerritorial(SectorTerritorial sectorTerritorial) throws IOException {
        //TODO DIAGRAMA DE PORCENTAJE DEL HC DE CADA ORG
        HashMap<String,Double> map = new HashMap<String, Double>();
        for(int i=0; i< sectorTerritorial.getOrganizaciones().size(); i++){
            map.put('"' + sectorTerritorial.getOrganizaciones().get(i).getRazonSocial() + '"', Double.valueOf(df.format(sectorTerritorial.getOrganizaciones().get(i).calcularHCOrgHistorico())));
        }
        return map;
    }

    public HashMap contenidoReporteEvolucionOrganizacion(Organizacion organizacion) throws IOException{
        //Diccionario que tenga actualizaciones con mes, año y valor de HC de una organizacion
        //TODO DIAGRAMA LINEA-TIEMPO QUE MUESTRE EL HC EVOLUTIVO DE UNA ORG
        HashMap<String, Double> map = new HashMap<String, Double>();
        List<PeriodoDeImputacion> periododesDeImputacionDeLaOrganicacion = new ArrayList<>();

        for (int i = 0; i < organizacion.getConsumos().size(); i ++){ // ARMAR UNA LISTA DE LOS PERIODOS DE IMPUTACION
            if (!periododesDeImputacionDeLaOrganicacion.contains(organizacion.getConsumos().get(i).getPeriodicidad())){
                periododesDeImputacionDeLaOrganicacion.add(organizacion.getConsumos().get(i).getPeriodicidad());
            }
        }

        for (int l = 0; l < organizacion.listarMiembros().size(); l++){
            Miembro miembro = organizacion.listarMiembros().get(l);
            for(int k = 0; k < miembro.getTrayectos().size(); k++){
                if(!periododesDeImputacionDeLaOrganicacion.contains(miembro.getTrayectos().get(k).getPeriodoDeImputacion())){
                    periododesDeImputacionDeLaOrganicacion.add(miembro.getTrayectos().get(k).getPeriodoDeImputacion());
                }
            }
        }

        for (int j = 0; j < periododesDeImputacionDeLaOrganicacion.size(); j++){ // LLENAR EL HASMAP CON LOS HC DE CADA PERIODO
            double hc = Double.parseDouble(df.format(organizacion.calcularHCOrganizacion(periododesDeImputacionDeLaOrganicacion.get(j))));
            map.put('"' + Integer.toString(periododesDeImputacionDeLaOrganicacion.get(j).getMes()) + "/" + Integer.toString(periododesDeImputacionDeLaOrganicacion.get(j).getAnio()) + '"', hc);
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


        for(int t = 0; t < sectorTerritorial.getOrganizaciones().size(); t++){
            Organizacion organizacion = sectorTerritorial.getOrganizaciones().get(t);
            for (int l = 0; l < organizacion.listarMiembros().size(); l++){
                Miembro miembro = organizacion.listarMiembros().get(l);
                for(int k = 0; k < miembro.getTrayectosActivos().size(); k++){
                    if(!periododesDeImputacionDelSectorT.contains(miembro.getTrayectosActivos().get(k).getPeriodoDeImputacion())){
                        periododesDeImputacionDelSectorT.add(miembro.getTrayectosActivos().get(k).getPeriodoDeImputacion());
                    }
                }
            }
        }

        for (int j = 0; j < periododesDeImputacionDelSectorT.size(); j++){ // LLENAR EL HASMAP CON LOS HC DE CADA PERIODO
            double hc = Double.parseDouble(df.format(sectorTerritorial.calcularHCSectorTerritorial(periododesDeImputacionDelSectorT.get(j))));
            map.put('"' + Integer.toString(periododesDeImputacionDelSectorT.get(j).getMes()) + "/" + Integer.toString(periododesDeImputacionDelSectorT.get(j).getAnio()) + '"', hc);
        }

        return map;
    }

    //TODO ESTE REPORTE NO PUEDEN GENERARLO LOS MIEMBROS.
    //Organizaciones->contenidoReporteHCOrganizacion(this.clasificacion, [this])
    //AgenteSecorial->contenidoReporteHCOrganizacion(unaClasificacion, this.organizaciones)
        public HashMap contenidoReporteHCOrganizacion(String tipoOrg, List<Organizacion> organizacionList) throws IOException {
        //TODO SEGUN LA CLASIFICACION QUE ELIJAS EN EL DESPLEGABLE, TE DEVUELVE EL HC DE TODAS LAS ORGS DE ESA CLASIFICACION
        HashMap<String, Double> map = new HashMap<String, Double>();
            double hcDeLaClasificacion = 0;
            for (int j = 0; j < organizacionList.size(); j++){
                if (organizacionList.get(j).getTipo().toString().equals(tipoOrg)) {
                    hcDeLaClasificacion += Double.valueOf(df.format(organizacionList.get(j).calcularHCOrgHistorico()));
                }
            }
            map.put('"' + tipoOrg + '"', hcDeLaClasificacion);
        return map;
    }

    public HashMap contenidoReporteHCSectorTerritorial(SectorTerritorial sectorTerritorial) throws IOException{
        //TODO TE DEVUELVE EL HC DE TODAS LAS ORGS SUMADOS
        //Sumatoria de las hc de cada organizacion (usando calculadora para c/u)

        double hc = sectorTerritorial.calcularHCSectorHistorico();

        HashMap<String, Double> map = new HashMap<>();
        map.put("HC TOTAL", hc);
        return map;
    }

}
