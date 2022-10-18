package domain.controllers;

import domain.models.entities.sectorTerritorial.SectorTerritorial;
import domain.models.repos.RepositorioDeAgentesSectoriales;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;

public class AgenteSectorialController {
    private RepositorioDeAgentesSectoriales repo;
    // VER MENU
    // VER REPORTES
    // VER RECOMENDACIONES

    public ModelAndView mostrarMenu(Request request, Response response){
        return new ModelAndView(null, "/AgenteSectorial/indexAgSec.hbs");
    }

    public ModelAndView mostrarRecomendaciones(Request request, Response response){
        SectorTerritorial sectorTerritorial=repo.buscar(new Integer(request.params("id")));

        return new ModelAndView(new HashMap<String, Object>(){{
            put("linkRecomendacionesAgSec", sectorTerritorial.getLink_recomendacion());
        }}, "/AgenteSectorial/recomendacionesAgSec.hbs");
    }

    public ModelAndView mostrarReportes(Request request, Response response){
        return new ModelAndView(null, "/AgenteSectorial/reportesAgSec.hbs");
    }
}
