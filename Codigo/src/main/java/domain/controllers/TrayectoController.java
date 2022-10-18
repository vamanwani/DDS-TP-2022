package domain.controllers;

import domain.models.entities.recorridos.Tramo;
import domain.models.entities.recorridos.Trayecto;
import domain.models.entities.transporte.Transporte;
import domain.models.entities.ubicacion.Ubicacion;
import domain.models.repos.RepositorioDeTrayectos;
import domain.services.dbManager.EntityManagerHelper;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;

public class TrayectoController {
    // Mostrar trayectos "miembros/:id/trayectos"
    // Editar trayectos "miembros/:id/trayectos/:id_trayecto/tramos"
    // Agregar trayectos POST DE "miembros/:id/trayectos/:id_trayecto/tramos"


    RepositorioDeTrayectos repo = new RepositorioDeTrayectos();

    public ModelAndView mostrarTrayectos(Request request, Response response){
        String idMimebro = request.params("id");
        List<Trayecto> trayectos = this.repo.buscarTodos(new Integer(idMimebro));
        return new ModelAndView(new HashMap<String, Object>(){{
            put("trayectos", trayectos);
        }}, "servicios/servicio.hbs"); // MODIFICAR ESTO
    }

    public ModelAndView mostrarTramosDeTrayecto(Request request, Response response){
        String idTrayecto = request.params("id_trayecto");
        List<Tramo> tramos = this.repo.buscar(new Integer(idTrayecto));
        return new ModelAndView(new HashMap<String, Object>(){{
            put("tramos", tramos);
        }}, "servicios/servicio.hbs"); // MODIFICAR ESTO
    }

    public Response crearTramo(Request request, Response response){
        Tramo tramo = new Tramo();
        tramo.setMedioDeTransporte(EntityManagerHelper.getEntityManager().find(Transporte.class, request.queryParams("medio_transporte")));
        tramo.setPuntoInicio(EntityManagerHelper.getEntityManager().find(Ubicacion.class, request.queryParams("punto_inicio")));
        tramo.setPuntoInicio(EntityManagerHelper.getEntityManager().find(Ubicacion.class, request.queryParams("punto_fin")));
        response.redirect("");//Pantalla de agregar tramos
        return response;
    }

    public Response crearTrayecto(Request request, Response response){
        Trayecto nuevoTrayecto = new Trayecto();
        nuevoTrayecto.setDescripcion(request.queryParams("descripcion"));
        nuevoTrayecto.setNombre(request.queryParams("nombre"));

        response.redirect("")// Pantalla de gestion de trayectos
    }

}