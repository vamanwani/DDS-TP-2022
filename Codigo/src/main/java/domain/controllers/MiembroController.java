package domain.controllers;

import domain.models.entities.miembro.Miembro;
import domain.models.entities.miembro.SolicitudVinculacion;
import domain.models.entities.organizacion.Organizacion;
import domain.models.entities.organizacion.Sector;
import domain.models.repos.RepositorioDeMiembros;
import domain.models.repos.RepositorioDeOrganizaciones;
import domain.models.repos.RepositorioDeSolicitudes;
import domain.models.repos.RespositorioDeSectores;
import domain.services.dbManager.EntityManagerHelper;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MiembroController {

    private RepositorioDeMiembros repo;
    private RepositorioDeSolicitudes repositorioDeSolicitudes = new RepositorioDeSolicitudes();
    private RepositorioDeOrganizaciones repositorioDeOrganizaciones = new RepositorioDeOrganizaciones();
    private RespositorioDeSectores respositorioDeSectores = new RespositorioDeSectores();

    public MiembroController() {
        this.repo = new RepositorioDeMiembros();
    }

    // Vincular a organizaciones "miembros/:id/organizaciones/vincular"
    // Mostrar organizaciones "miembros/:id/organizaciones"

    public ModelAndView mostrarMenu(Request request, Response response){
        Miembro miembro = this.repo.buscar(Integer.valueOf(request.params("id")));
        return new ModelAndView(new HashMap<String, Object>(){{
            put("miembro", miembro);
        }}, "/Miembro/indexMiembro.hbs");// el viewname que sea igual al home de miembro
    }

    public ModelAndView mostrarOrganizaciones(Request request, Response response){
        try{
            List<Organizacion> organizaciones = this.repo.mostrarOrganizaciones();
            Miembro miembro = this.repo.buscar(Integer.valueOf(request.params("id")));
            List<Sector> sectores = miembro.getTrabajos();
            if(sectores.isEmpty()){
                return new ModelAndView(new HashMap<String, Object>(){{
                    put("miembro", miembro);
                    put("organizaciones", organizaciones);
                }}, "/Miembro/unirseAOrg.hbs"); // MODIFICAR ESTO
            } else {
                List<Organizacion> organizacionesDisponibles = new ArrayList<>();
                for(Organizacion org : organizaciones){
                    for (Sector sector : sectores) {
                        if (!org.getSectores().contains(sector)) {
                            organizacionesDisponibles.add(org);
                        }
                    }
                }
                return new ModelAndView(new HashMap<String, Object>(){{
                    put("miembro", miembro);
                    put("organizaciones", organizacionesDisponibles);
                }}, "/Miembro/unirseAOrg.hbs");
            }
        } catch (Exception ex){
            return new ModelAndView (null, "/Miembro/unirseAOrg.hbs");
        }
    }

    public Response vincularAOrg(Request request, Response response){
        Miembro miembro = this.repo.buscar(Integer.valueOf(request.params("id")));
        Sector sectorAVincular = this.respositorioDeSectores.buscar(Integer.parseInt(request.queryParams("sector_id")));
        SolicitudVinculacion solicitudVinculacion = miembro.generarSolicitud(sectorAVincular);
        this.repositorioDeSolicitudes.guardar(solicitudVinculacion);
        response.redirect("/miembro/" + miembro.getId() + "/organizaciones" ); // TODO arreglar path
        return response;
    }

    public ModelAndView mostrarHC(Request request, Response response) throws IOException {
        Miembro miembro = EntityManagerHelper
                .getEntityManager()
                .find(Miembro.class, Long.valueOf(request.params("id")));
        return new ModelAndView(new HashMap<String, Object>(){{
            put("hcMiembro", miembro.calcularHCMiembro());
            put("miembro", miembro);
        }}, "/Miembro/hcMiembro.hbs");
    }

    public ModelAndView mostrarReportes(Request request, Response response){
        return new ModelAndView(null, "/Miembro/reportesMiembro.hbs");
    }

}