package domain.controllers;

import com.sendgrid.Request;
import com.sendgrid.Response;
import domain.models.entities.miembro.Miembro;
import domain.models.entities.organizacion.Organizacion;
import domain.models.repos.RepositorioDeMiembros;
import domain.models.repos.RepositorioDeOrganizaciones;
import org.apache.poi.hssf.dev.ReSave;
import spark.ModelAndView;

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
    //Organizaciones
    public ModelAndView mostrarMenu(spark.Request request, spark.Response response){
        return new ModelAndView(null , "indexOrg.hbs");// el viewname que sea igual al home de organizacion
    }

    public ModelAndView mostrarRecomendaciones(spark.Request request, spark.Response response){
        Organizacion organizacion=repo.buscar(new Integer(request.params("id")));

        return new ModelAndView(new HashMap<String, Object>(){{
            put("linkRecomendacionesOrg", organizacion.getLinkRecomendacion());
        }}, "Template/Organizacion/recomendaciones.hbs");
    }


    public void registrarMediciones(spark.Request request, spark.Response response) {

    }

    public ModelAndView mostrarSolicitantes(spark.Request request, spark.Response response) {
        String idOrganizacion = request.params("id");
        List<Miembro> solicitantes = this.repo.buscarTodasLosSolicitantes(new Integer(idOrganizacion));
        return new ModelAndView(new HashMap<String, Object>(){{
            put("solicitantes", solicitantes);
        }}, "Template/Organizacion/aceptarVinculaciones.hbs");
    }

    public void actualizarMiembros(spark.Request request, spark.Response response) {
        String idmiembroNuevo=request.params("idmiembro");
        Miembro miembro=repositorioDeMiembros.buscar(new Integer(idmiembroNuevo));
        Organizacion organizacion=repo.buscar(new Integer(request.params("id")));

    }
}
