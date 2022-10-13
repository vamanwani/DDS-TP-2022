package domain.controllers;

import com.sendgrid.Request;
import com.sendgrid.Response;
import org.apache.poi.hssf.dev.ReSave;
import spark.ModelAndView;

public class OrganizacionController {
    //
    // MOSTRAR TRABAJADORES
    // GENERAR REPORTES
    // REGISTRAR MEDICIONES pantalla del excel
    // ACEPTAR VINCULACION
    // VER SUGERENCIAS

    // MOSTRAR
    public ModelAndView mostrarMenu(Request request, Response response){
        return new ModelAndView();
    }

    public ModelAndView mostrarRecomendaciones(Request request, Response response){
        return new ModelAndView();
    }


    public void registrarMediciones(spark.Request request, spark.Response response) {
    }

    public ModelAndView mostrarSolicitantes(spark.Request request, spark.Response response) {
    }

    public void actualizarMiembros(spark.Request request, spark.Response response) {
    }
}
