package domain.controllers;

import domain.models.entities.sectorTerritorial.AgenteSectorial;
import domain.models.entities.sectorTerritorial.SectorTerritorial;
import domain.models.repos.RepositorioDeAgentesSectoriales;
import domain.services.dbManager.EntityManagerHelper;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;

public class AgenteSectorialController {
    private RepositorioDeAgentesSectoriales repo = new RepositorioDeAgentesSectoriales();
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
        SectorTerritorial sectorTerritorial = (SectorTerritorial) EntityManagerHelper
                .getEntityManager()
                .createQuery("from "+ SectorTerritorial.class.getName() +" where agente_sectorial_id=" + agenteSectorial.getId())
                .getSingleResult();
        return new ModelAndView(new HashMap<String, Object>(){{
            put("linkRecomendacionesAgSec", sectorTerritorial.getLink_recomendacion());
        }}, "/AgenteSectorial/recomendacionesAgSec.hbs");
    }

    public ModelAndView mostrarReportes(Request request, Response response){
        return new ModelAndView(null, "/AgenteSectorial/reportesAgSec.hbs");
    }
}
