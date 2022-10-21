package domain.controllers;

import domain.models.entities.consumo.PeriodoDeImputacion;
import domain.models.entities.miembro.Miembro;
import domain.models.entities.organizacion.Organizacion;
import domain.models.entities.reporte.Reporte;
import domain.models.repos.RepositorioDeMiembros;
import domain.models.repos.RepositorioDeOrganizaciones;
import domain.services.dbManager.EntityManagerHelper;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrganizacionController {

    //
    // MOSTRAR TRABAJADORES
    // GENERAR REPORTES
    // REGISTRAR MEDICIONES pantalla del excel
    // ACEPTAR VINCULACION
    // VER SUGERENCIAS
    private RepositorioDeOrganizaciones repo;
    private RepositorioDeMiembros repositorioDeMiembros;

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
        }

    }


    public Response registrarMediciones(spark.Request request, spark.Response response) {
        return response;
    }

    public ModelAndView mostrarSolicitantes(spark.Request request, spark.Response response) {
        try {
            String idOrganizacion = request.params("id");
            List<Miembro> solicitantes = this.repo.buscarTodasLosSolicitantes(new Integer(idOrganizacion));
            return new ModelAndView(new HashMap<String, Object>(){{
                put("solicitantes", solicitantes);
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
}

