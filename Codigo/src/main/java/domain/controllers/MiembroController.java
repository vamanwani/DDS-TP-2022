package domain.controllers;

import domain.models.entities.miembro.Miembro;
import domain.models.entities.organizacion.Organizacion;
import domain.models.entities.recorridos.Tramo;
import domain.models.entities.recorridos.Trayecto;
import domain.models.repos.RepositorioDeMiembros;
import domain.services.dbManager.EntityManagerHelper;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;

public class MiembroController {

    private RepositorioDeMiembros repo;

    public MiembroController() {
        this.repo = new RepositorioDeMiembros();
    }

    // Vincular a organizaciones "miembros/:id/organizaciones/vincular"
    // Mostrar organizaciones "miembros/:id/organizaciones"

    public ModelAndView mostrarMenu(Request request, Response response){
        return new ModelAndView(null, "")// el viewname que sea igual al home de miembro
    }

    public ModelAndView mostrarOrganizaciones(Request request, Response response){
        String idMiembro = request.params("id");
        List<Organizacion> organizaciones = this.repo.buscarOrganizaciones(new Integer(idMiembro));
        return new ModelAndView(new HashMap<String, Object>(){{
            put("organizaciones", organizaciones);
        }}, "Template/Miembro/unirseA.hbs"); // MODIFICAR ESTO
    }

    public Response vincularAOrg(Request request, Response response){
        Miembro miembro = this.repo.buscar(Integer.valueOf(request.params("id")));
        Organizacion organizacionAVincular = EntityManagerHelper
                .getEntityManager()
                .find(Organizacion.class, request.queryParams("organizacion_id"));
        miembro.generarSolicitud(organizacionAVincular);
        response.redirect("Template/Miembro/unirseA.hbs");
        return response;
    }

    public ModelAndView mostrarReportes(Request request, Response response){
        return new ModelAndView();
    }

}
