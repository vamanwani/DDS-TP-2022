package domain.controllers;

import domain.models.entities.consumo.PeriodoDeImputacion;
import domain.models.entities.miembro.EstadoSolicitud;
import domain.models.entities.miembro.Miembro;
import domain.models.entities.miembro.SolicitudVinculacion;
import domain.models.entities.organizacion.Organizacion;
import domain.models.entities.organizacion.Sector;
import domain.models.entities.reporte.Reporte;
import domain.models.repos.RepositorioDeMiembros;
import domain.models.repos.RepositorioDeOrganizaciones;
import domain.models.repos.RepositorioDeSolicitudes;
import domain.services.dbManager.EntityManagerHelper;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class OrganizacionController {

    //
    // MOSTRAR TRABAJADORES
    // GENERAR REPORTES
    // REGISTRAR MEDICIONES pantalla del excel
    // ACEPTAR VINCULACION
    // VER SUGERENCIAS
    private RepositorioDeOrganizaciones repo;
    private RepositorioDeMiembros repositorioDeMiembros = new RepositorioDeMiembros();
    private RepositorioDeSolicitudes repositorioDeSolicitudes = new RepositorioDeSolicitudes();

    public OrganizacionController(){
        this.repo = new RepositorioDeOrganizaciones();
    }

    //Organizaciones
    public ModelAndView mostrarMenu(spark.Request request, spark.Response response){
        Organizacion organizacion = this.repo.buscar(Integer.valueOf(request.params("id")));
        return new ModelAndView(new HashMap<String, Object>(){{
            put("organizacion", organizacion);
        }} , "/Organizacion/indexOrg.hbs");// el viewname que sea igual al home de organizacion
    }

    public ModelAndView mostrarRecomendaciones(spark.Request request, spark.Response response){
        try{
            Organizacion organizacion=repo.buscar(new Integer(request.params("id")));
            return new ModelAndView(new HashMap<String, Object>(){{
                put("linkRecomendacionesOrg", organizacion.getLinkRecomendacion());
            }}, "/Organizacion/recomendaciones.hbs");
        } catch (Exception ex){
            //TODO si no hay link de recomendaciones
            return new ModelAndView(null, null);
        }

    }

    public ModelAndView registroMediciones(Request request, Response response) {
        try{
            Organizacion organizacion = this.repo.buscar(Integer.valueOf(request.params("id")));
            return new ModelAndView(new HashMap<String, Object>(){{
                put("organizacion", organizacion);
            }} , "/Organizacion/registrarMediciones.hbs");// el viewname que sea igual al home de organizacion
        } catch (Exception ex){
            return new ModelAndView(null, "Organizacion/registrarMediciones.hbs");
        }
    }

    public Response registrarMediciones(spark.Request request, spark.Response response) throws ServletException, IOException {
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement("/D:\\Facultad\\3er año 2022\\Diseño de sistemas\\2022-ma-ma-mama-grupo-05\\Codigo\\src\\main\\resources\\uploads");
        request.raw().setAttribute("org.eclipse.jetty.multipartConfig", multipartConfigElement);
        final Part uploadedFile = request.raw().getPart("file");
        final Path path = Paths.get("D:\\Facultad\\3er año 2022\\Diseño de sistemas\\2022-ma-ma-mama-grupo-05\\Codigo\\src\\main\\resources\\uploads\\'"+ request.params("id") +"'.xlsx");
        try (final InputStream in = uploadedFile.getInputStream()) {
            Files.copy(in, path);
        }

        response.redirect("/organizaciones/" + request.params("id"));
        return response;
    }

    public ModelAndView mostrarSolicitantes(spark.Request request, spark.Response response) {
        try {
            String idOrganizacion = request.params("id");
            List<SolicitudVinculacion> solicitudes = this.repositorioDeSolicitudes.buscarSolicitudesPendientesDeOrg(new Integer(idOrganizacion));
            return new ModelAndView(new HashMap<String, Object>(){{
                put("solicitudes", solicitudes);
            }}, "/Organizacion/aceptarVinculaciones.hbs");
        } catch (Exception ex){
            //TODO si no hay solicitantes
            return new ModelAndView(null,"/Organizacion/aceptarVinculaciones.hbs");
        }

    }

    public Response actualizarMiembros(spark.Request request, spark.Response response) {
        String idmiembroNuevo=request.params("idmiembro");
        Miembro miembro=repositorioDeMiembros.buscar(new Integer(idmiembroNuevo));
        Organizacion organizacion=repo.buscar(new Integer(request.params("id")));
        organizacion.agregarMiembro(miembro);
        response.redirect("/Organizacion/aceptarVinculaciones.hbs");
        return response;
    }

    public ModelAndView mostrarHC(spark.Request request, Response response) throws IOException {
        Organizacion organizacion = EntityManagerHelper
                .getEntityManager()
                .find(Organizacion.class, request.params("id"));
        PeriodoDeImputacion periodoDeImputacion = new PeriodoDeImputacion(request.queryParams("periodo_imputacion"));
        return new ModelAndView(null, "/Organizacion/hcOrganizacion.hbs");
    }

    public ModelAndView mostrarReportes(Request request, Response response) throws IOException {
        Organizacion organizacion = EntityManagerHelper
                .getEntityManager()
                .find(Organizacion.class, request.params("id"));
        List<HashMap> reportes = new ArrayList<>();
        Reporte reporte = new Reporte();

//        if (evolucion) else if (composicion)
        reportes.add(reporte.contenidoReporteEvolucionOrganizacion(organizacion));
        reportes.add(reporte.contenidoReporteComposicionOrganizacion(organizacion));
        return new ModelAndView(new HashMap<String, HashMap>(){{
            //put("reportes", reportes);
        }}, "");
    }


    public Response aceptarSolicitud(Request request, Response response){
        SolicitudVinculacion solicitudVinculacion = this.repositorioDeSolicitudes.buscar(Integer.valueOf(request.params("id_solicitud")));
        System.out.println(solicitudVinculacion);
        Miembro miembro = solicitudVinculacion.getMiembro();
        miembro.agregarTrabajo(solicitudVinculacion.getSector());
        Sector sector = solicitudVinculacion.getSector();
        sector.agregarMiembro(miembro);
        solicitudVinculacion.setEstadoSolicitud(EstadoSolicitud.ACEPTADA);
        this.repositorioDeSolicitudes.guardar(solicitudVinculacion);
//        this.repositorioDeMiembros.guardar(miembro);
        response.redirect("/organizaciones/"+ solicitudVinculacion.getOrganizacion().getId() + "/aceptar_vinculacion");
        return response;
    }

    public Response rechazarSolicitud(Request request, Response response){
        SolicitudVinculacion solicitudVinculacion = this.repositorioDeSolicitudes.buscar(Integer.valueOf(request.params("id_solicitud")));
        solicitudVinculacion.setEstadoSolicitud(EstadoSolicitud.RECHAZADA);
        this.repositorioDeSolicitudes.guardar(solicitudVinculacion);
        response.redirect("/organizaciones/"+ solicitudVinculacion.getOrganizacion().getId() + "/aceptar_vinculacion");
        return response;
    }

}

