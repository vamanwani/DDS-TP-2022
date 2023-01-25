package domain.controllers;

import domain.models.entities.calculoHC.CalculadoraHCOrganizacion;
import domain.models.entities.calculoHC.CalculadoraHCSector;
import domain.models.entities.consumo.Consumo;
import domain.models.entities.consumo.PeriodoDeImputacion;
import domain.models.entities.consumo.TipoPeriodicidad;
import domain.models.entities.miembro.EstadoSolicitud;
import domain.models.entities.miembro.Miembro;
import domain.models.entities.miembro.SolicitudVinculacion;
import domain.models.entities.obtieneMediciones.ImportarDeExcel;
import domain.models.entities.organizacion.Organizacion;
import domain.models.entities.organizacion.Sector;
import domain.models.entities.reporte.Reporte;
import domain.models.repos.RepositorioDeMiembros;
import domain.models.repos.RepositorioDeOrganizaciones;
import domain.models.repos.RepositorioDeSolicitudes;
import domain.services.dbManager.EntityManagerHelper;
import org.apache.xmlbeans.impl.xb.xsdschema.ImportDocument;
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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OrganizacionController {

    private RepositorioDeOrganizaciones repo;
    private RepositorioDeMiembros repositorioDeMiembros = new RepositorioDeMiembros();
    private RepositorioDeSolicitudes repositorioDeSolicitudes = new RepositorioDeSolicitudes();
    private static final DecimalFormat df = new DecimalFormat("0.00");

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
        Organizacion organizacion=repo.buscar(new Integer(request.params("id")));
        try{
            return new ModelAndView(new HashMap<String, Object>(){{
                put("linkRecomendacionesOrg", organizacion.getLinkRecomendacion());
                put("organizacion", organizacion);
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

    public ModelAndView registrarMediciones(spark.Request request, spark.Response response) throws ServletException, IOException {
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement("/src/main/resources/uploads");
        request.raw().setAttribute("org.eclipse.jetty.multipartConfig", multipartConfigElement);
        final Part uploadedFile = request.raw().getPart("file");
        final Path path = Paths.get("src/main/resources/uploads/'"+ request.params("id") +"'.xlsx");
        try (final InputStream in = uploadedFile.getInputStream()) {
            Files.copy(in, path);
        }


        ImportarDeExcel importador = new ImportarDeExcel();
        Organizacion organizacion = this.repo.buscar(Integer.valueOf(request.params("id")));

        importador.setOrganizacion(organizacion);
        importador.setNombreDeExcel("'" + request.params("id") + "'.xlsx");

        importador.start();

//        importador.importar("'" + request.params("id") + "'.xlsx", organizacion);

//        response.redirect("/organizaciones/" + request.params("id"));
//        return response;
        return new ModelAndView(new HashMap<String, Object>(){{
            put("autor", true);
        }}, "Organizacion/registrarMediciones.hbs");
    }

    public ModelAndView mostrarSolicitantes(Request request, Response response) {
        Organizacion organizacion=repo.buscar(new Integer(request.params("id")));

        try {

            String idOrganizacion = request.params("id");
            List<SolicitudVinculacion> solicitudes = this.repositorioDeSolicitudes.buscarSolicitudesPendientesDeOrg(new Integer(idOrganizacion));
            return new ModelAndView(new HashMap<String, Object>(){{
                put("solicitudes", solicitudes);
                put("organizacion", organizacion);
            }}, "/Organizacion/aceptarVinculaciones.hbs");
        } catch (Exception ex){
            //TODO si no hay solicitantes
            return new ModelAndView(null,"/Organizacion/aceptarVinculaciones.hbs");
        }

    }

    public Response actualizarMiembros(Request request, Response response) {
        String idmiembroNuevo=request.params("idmiembro");
        Miembro miembro=repositorioDeMiembros.buscar(new Integer(idmiembroNuevo));
        Organizacion organizacion=repo.buscar(new Integer(request.params("id")));
        organizacion.agregarMiembro(miembro);
        response.redirect("/Organizacion/aceptarVinculaciones.hbs");
        return response;
    }

    public ModelAndView mostrarHC(Request request, Response response) throws IOException {
        Organizacion organizacion = EntityManagerHelper
                .getEntityManager()
                .find(Organizacion.class, new Integer(request.params("id")));


        return new ModelAndView(new HashMap<String, Object>(){{
            put("hcOrganizacion", df.format(organizacion.calcularHCOrgHistorico()));
            put("organizacion", organizacion);
        }}, "/Organizacion/hcOrganizacion.hbs");

    }

    public ModelAndView mostrarReportes(Request request, Response response) throws IOException {
        //TODO REPORTES
        //HC POR CLASIFICACION
        //COMPOSICION ORG (SECTORES), el grafico muestra todos los sectores de la org con sus HC
        //EVOLUCION ORG (HISTORICO)

        Organizacion organizacion = EntityManagerHelper
                .getEntityManager()
                .find(Organizacion.class, Integer.valueOf(request.params(("id"))));

        return new ModelAndView(new HashMap<String, Object>(){{
            put("organizacion", organizacion);
        }}, "/Organizacion/reportesOrg.hbs");
    }

    public Response aceptarSolicitud(Request request, Response response){
        SolicitudVinculacion solicitudVinculacion = this.repositorioDeSolicitudes.buscar(Integer.valueOf(request.params("id_solicitud")));
        Miembro miembro = solicitudVinculacion.getMiembro();
        miembro.agregarTrabajo(solicitudVinculacion.getSector());
        Sector sector = solicitudVinculacion.getSector();
        sector.agregarMiembro(miembro);
        solicitudVinculacion.setEstadoSolicitud(EstadoSolicitud.ACEPTADA);
        this.repositorioDeSolicitudes.guardar(solicitudVinculacion);
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

    public ModelAndView calcularHC(Request request, Response response) throws IOException {
        Organizacion organizacion = this.repo.buscar(Integer.valueOf(request.params("id")));
        String mes = request.queryParams("mes_periodo");
        String anio = request.queryParams("anio_periodo");
        String tipoPeriodicidad = request.queryParams("tipo_periodicidad");
        PeriodoDeImputacion periodoDeImputacion = new PeriodoDeImputacion(Integer.valueOf(mes),Integer.valueOf(anio),tipoPeriodicidad);
        CalculadoraHCOrganizacion calculadoraParaOrganizacion = new CalculadoraHCOrganizacion();
        List<Consumo> consumos = this.repo.buscarTodosLosConsumos(Integer.valueOf(request.params("id")));

        Double hcOrg = calculadoraParaOrganizacion.calcularHC(consumos, periodoDeImputacion);
        Double hcDeMiembrosDeOrg = organizacion.hcMiembrosOrganizacion();
        Double hcTotal = hcOrg + hcDeMiembrosDeOrg;
        return new ModelAndView(new HashMap<String, Object>(){{
            put("hcOrganizacion", hcTotal);
            put("organizacion", organizacion);
        }}, "/Organizacion/hcOrganizacion.hbs");

    }

    public ModelAndView obtenerReportes (Request request, Response response) throws IOException {
        Organizacion organizacion = this.repo.buscar(Integer.valueOf(request.params("id")));

        String tipoReporte = request.queryParams("tipo_reporte");
        Reporte reporte = new Reporte();
        HashMap datos = null;
        if(tipoReporte.equals("composicion")){
            String mes = request.queryParams("mes");
            String anio = request.queryParams("anio");
            String tipoPeriodicidad = request.queryParams("tipo_periodicidad");
            PeriodoDeImputacion periodoDeImputacion = new PeriodoDeImputacion(Integer.valueOf(mes),Integer.valueOf(anio),tipoPeriodicidad);
           datos = reporte.contenidoReporteComposicionOrganizacion(organizacion, periodoDeImputacion);
        } else if (tipoReporte.equals("evolucion")) {
            datos = reporte.contenidoReporteEvolucionOrganizacion(organizacion);
        }

        HashMap finalDatos = datos;

        return new ModelAndView(new HashMap<String, Object>(){{
            put("nombres", finalDatos.keySet());
            put("valores", finalDatos.values());
            put("organizacion", organizacion);
            put("tipo_reporte", tipoReporte);
        }}, "/Organizacion/reportesOrg.hbs");
    }

}