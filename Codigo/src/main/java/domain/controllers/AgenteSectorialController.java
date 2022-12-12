package domain.controllers;

import domain.models.entities.consumo.PeriodoDeImputacion;
import domain.models.entities.organizacion.Organizacion;
import domain.models.entities.organizacion.Sector;
import domain.models.entities.reporte.Reporte;
import domain.models.entities.sectorTerritorial.AgenteSectorial;
import domain.models.entities.sectorTerritorial.Pais;
import domain.models.entities.sectorTerritorial.SectorTerritorial;
import domain.models.repos.RepositorioDeAgentesSectoriales;
import domain.models.repos.RepositorioDeOrganizaciones;
import domain.services.dbManager.EntityManagerHelper;
import spark.ModelAndView;
import spark.Request;
import spark.RequestResponseFactory;
import spark.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class AgenteSectorialController {
    private RepositorioDeAgentesSectoriales repo = new RepositorioDeAgentesSectoriales();
    private RepositorioDeOrganizaciones repositorioDeOrganizaciones = new RepositorioDeOrganizaciones();
    // VER MENU
    // VER REPORTES
    // VER RECOMENDACIONES

    public ModelAndView mostrarMenu(Request request, Response response){
        AgenteSectorial agenteSectorial = this.repo.buscar(Integer.valueOf(request.params("id")));
        return new ModelAndView(new HashMap<String, Object>(){{
            put("agSec", agenteSectorial);
        }}, "/AgenteSectorial/indexAgSec.hbs");
    }

    public ModelAndView mostrarRecomendaciones(Request request, Response response){
        AgenteSectorial agenteSectorial=repo.buscar(new Integer(request.params("id")));
        try{
        SectorTerritorial sectorTerritorial = (SectorTerritorial) EntityManagerHelper
                .getEntityManager()
                .createQuery("from "+ SectorTerritorial.class.getName() +" where agente_sectorial_id=" + agenteSectorial.getId())
                .getSingleResult();
            return new ModelAndView(new HashMap<String, Object>(){{
                put("linkRecomendacionesAgSec", sectorTerritorial.getLink_recomendacion());
                put("agSec", agenteSectorial);
            }}, "/AgenteSectorial/recomendacionesAgSec.hbs");}
        catch(Exception exception){
            return new ModelAndView(null, "/AgenteSectorial/recomendacionesAgSec.hbs");}

    }

    public ModelAndView mostrarReportes(Request request, Response response){
        AgenteSectorial agenteSectorial = this.repo.buscar(Integer.valueOf(request.params("id")));
        return new ModelAndView(new HashMap<String, Object>(){{
            put("agSec", agenteSectorial);
        }}, "/AgenteSectorial/reportesAgSec.hbs");
    }

    public ModelAndView obtenerReporte(Request request, Response response) throws IOException {
        AgenteSectorial agenteSectorial = this.repo.buscar(Integer.valueOf(request.params("id")));
        SectorTerritorial sectorTerritorial = this.repo.buscarSectorSegunAgente(agenteSectorial.getId());

        String tipoReporte = request.queryParams("tipo_reporte");
        Reporte reporte = new Reporte();
        HashMap datos = null;
        if(tipoReporte.equals("composicion_sector")){
//            String mes = request.queryParams("mes");
//            String anio = request.queryParams("anio");
//            String tipoPeriodicidad = request.queryParams("tipo_periodicidad");
//            PeriodoDeImputacion periodoDeImputacion = new PeriodoDeImputacion(Integer.valueOf(mes),Integer.valueOf(anio),tipoPeriodicidad);
            datos = reporte.contenidoReporteComposicionSectorTerritorial(sectorTerritorial);
        } else if (tipoReporte.equals("composicion_pais")){
            String mes = request.queryParams("mes");
            String anio = request.queryParams("anio");
            String tipoPeriodicidad = request.queryParams("tipo_periodicidad");
            PeriodoDeImputacion periodoDeImputacion = new PeriodoDeImputacion(Integer.valueOf(mes),Integer.valueOf(anio),tipoPeriodicidad);
            Pais pais = sectorTerritorial.getPais();
            Set<SectorTerritorial> sectorTerritorialSet = this.repo.buscarSectoresSegunPais(pais.getId());
            datos = reporte.contenidoReporteComposicionPais(sectorTerritorialSet, periodoDeImputacion);
        } else if (tipoReporte.equals("evolucion")) {
            datos = reporte.contenidoReporteEvolucionSectorTerritorial(sectorTerritorial);
        } else if (tipoReporte.equals("total")){
            datos = reporte.contenidoReporteHCSectorTerritorial(sectorTerritorial);
        } else if (tipoReporte.equals("porTipoOrg")){
            String tipo = request.queryParams("tipo_org");
            List<Organizacion> organizacionList = this.repositorioDeOrganizaciones.buscarTodas();
            datos = reporte.contenidoReporteHCOrganizacion(tipo, organizacionList);
        }

        HashMap finalDatos = datos;

        return new ModelAndView(new HashMap<String, Object>(){{
            put("nombres", finalDatos.keySet());
            put("valores", finalDatos.values());
            put("agSec", agenteSectorial);
            put("tipo_reporte", tipoReporte);
        }}, "/AgenteSectorial/reportesAgSec.hbs");
    }
}
