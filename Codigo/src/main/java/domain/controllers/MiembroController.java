package domain.controllers;

import domain.models.entities.miembro.Miembro;
import domain.models.entities.miembro.Usuario;
import domain.models.entities.organizacion.Organizacion;
import domain.models.repos.RepositorioDeMiembros;
import domain.services.dbManager.EntityManagerHelper;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.IOException;
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
        Miembro miembro = this.repo.buscarSegunUsuarioId(Integer.valueOf(request.params("id")));
        return new ModelAndView(new HashMap<String, Object>(){{
            put("miembro", miembro);
        }}, "/Miembro/indexMiembro.hbs");// el viewname que sea igual al home de miembro
    }

    public ModelAndView mostrarOrganizaciones(Request request, Response response){
        String idMiembro = request.params("id");
        List<Organizacion> organizaciones = this.repo.buscarOrganizaciones(new Integer(idMiembro));
        return new ModelAndView(new HashMap<String, Object>(){{
            put("organizaciones", organizaciones);
        }}, "/Miembro/unirseAOrg.hbs"); // MODIFICAR ESTO
    }

    public Response vincularAOrg(Request request, Response response){
        Miembro miembro = this.repo.buscar(Integer.valueOf(request.params("id")));
        Organizacion organizacionAVincular = EntityManagerHelper
                .getEntityManager()
                .find(Organizacion.class, request.queryParams("organizacion_id"));
        miembro.generarSolicitud(organizacionAVincular);
        response.redirect("/Miembro/unirseAOrg.hbs");
        return response;
    }

    public ModelAndView mostrarHC(Request request, Response response) throws IOException {
        Miembro miembro = EntityManagerHelper
                .getEntityManager()
                .find(Miembro.class, Long.valueOf(request.params("id")));
        return new ModelAndView(null, "/Miembro/hcMiembro.hbs");
    }

    public ModelAndView mostrarReportes(Request request, Response response){
        return new ModelAndView(null, "/Miembro/reportesMiembro.hbs");
    }

}