package domain.controllers;

import domain.models.entities.recorridos.Tramo;
import domain.models.entities.recorridos.Trayecto;
import domain.models.entities.transporte.Transporte;
import domain.models.entities.transporte.TransportePublico;
import domain.models.entities.transporte.VehiculoParticular;
import domain.models.entities.ubicacion.Ubicacion;
import domain.models.repos.RepositorioDeMiembros;
import domain.models.repos.RepositorioDeTramos;
import domain.models.repos.RepositorioDeTrayectos;
import domain.models.repos.RepositorioDeUbicaciones;
import domain.services.dbManager.EntityManagerHelper;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.persistence.Entity;
import java.util.HashMap;
import java.util.List;

public class TrayectoController {
    // Mostrar trayectos "miembros/:id/trayectos"
    // Editar trayectos "miembros/:id/trayectos/:id_trayecto/tramos"
    // Agregar trayectos POST DE "miembros/:id/trayectos/:id_trayecto/tramos"
    RepositorioDeTrayectos repo = new RepositorioDeTrayectos();
    RepositorioDeUbicaciones repositorioDeUbicaciones = new RepositorioDeUbicaciones();
    RepositorioDeTramos repositorioDeTramos = new RepositorioDeTramos();
    RepositorioDeMiembros repositorioDeMiembros = new RepositorioDeMiembros();

    public ModelAndView mostrarTrayectos(Request request, Response response){
        try{
            String idMimebro = request.params("id");
            List<Trayecto> trayectos = this.repo.buscarTodos(new Integer(idMimebro));
            return new ModelAndView(new HashMap<String, Object>(){{
                put("trayectos", trayectos);
            }}, "/Miembro/editarTrayectos.hbs"); // MODIFICAR ESTO
        } catch (Exception ex){
            return new ModelAndView(null, "/Miembro/editarTrayectos.hbs");
        }
    }

    public ModelAndView mostarTrayecto(Request request, Response response){
        try{
            String idTrayecto = request.params("id_trayecto");
            Trayecto trayecto = this.repo.buscar(Integer.valueOf(idTrayecto));
            return new ModelAndView(new HashMap<String, Object>(){{
                put("trayecto", trayecto);
                put("tramos", trayecto.getTramos());
            }}, "/Miembro/editarTrayecto.hbs");
        } catch (Exception ex){
            return new ModelAndView(null, "");// "no such trayecto"
        }
    }

    public ModelAndView mostrarTramosDeTrayecto(Request request, Response response){
        Trayecto trayecto = this.repo.buscar(Integer.valueOf(request.params("id_trayecto")));
        return new ModelAndView(null,"/Miembro/editarTrayecto.hbs"); // MODIFICAR ESTO
    }

    public ModelAndView agregarTrayecto(Request request, Response response){
        Trayecto trayecto = this.repo.buscar(Integer.valueOf(request.params("id_trayecto")));
        return new ModelAndView(new HashMap<String,Object>(){{
            put("miembro_id", request.params("id"));
            put("trayecto_id", trayecto.getId());
            put("tramos", trayecto.getTramos());
        }}, "/Miembro/agregarTrayecto.hbs");
    }

    public Response crearTrayecto(Request request, Response response){
        Trayecto nuevoTrayecto = new Trayecto();
        this.repo.guardar(nuevoTrayecto);
        response.redirect("" + nuevoTrayecto.getId() + "/agregar");// Pantalla de gestion de trayectos
        return response;
    }

    public Response definirTrayecto(Request request, Response response){
        Trayecto trayecto = this.repo.buscar(Integer.valueOf(request.params("id_trayecto")));
        trayecto.setNombre(request.queryParams("nombre"));
        trayecto.setDescripcion(request.queryParams("descripcion"));
        trayecto.setMiembro(this.repositorioDeMiembros.buscar(Integer.valueOf(request.params("id"))));
        this.repo.guardar(trayecto);
        response.redirect("/miembro/"+ request.params("id") +"/trayectos");
        return response;
    }

    public ModelAndView mostrarTramo(Request request, Response response){
        Tramo tramo = this.repositorioDeTramos.buscar(Integer.valueOf(request.params("id_tramo")));
        return new ModelAndView(new HashMap<String, Object>(){{
            put("tramo",tramo );
        }}, "/Miembro/editarTramo.hbs");
    }


    public Response crearTramo(Request request, Response response){
        Trayecto trayecto = this.repo.buscar(Integer.valueOf(request.params("id_trayecto")));
        Tramo tramo = new Tramo();
        Ubicacion puntoInicio = new Ubicacion(request.queryParams("punto_inicio_calle"), Integer.valueOf(request.queryParams("punto_inicio_altura")), null);
        Ubicacion puntoFin = new Ubicacion(request.queryParams("punto_fin_calle"), Integer.valueOf(request.queryParams("punto_fin_altura")), null);
//        tramo.setMedioDeTransporte(EntityManagerHelper.getEntityManager().find(Transporte.class, request.queryParams("medio_transporte")));

        String medio_transporte= request.queryParams("medio_transporte");
        Integer transporte = 0;
        if(medio_transporte == "Vehiculo Particular"){
            String tipoVehiculo = request.queryParams("tipo_vehiculo");
            String tipoCombustible = request.queryParams("tipo_vehiculo");
//            VehiculoParticular vehiculoParticular = new VehiculoParticular();
            transporte = 1;
        } else if (medio_transporte == "Transporte Publico"){
            String tipoVehiculo =  request.queryParams("tipo_vehiculo");
            String paradaInicial=request.queryParams("tipo_vehiculo");
            TransportePublico transportePublico=new TransportePublico();
            //TODO : fijarse si el transporte se debe crear y persistir acá o si el Admin lo tiene que cargar de antemano junto con sus paradas
            transporte = 2;
        } else if (medio_transporte == "Servicio Contratado"){
            String servicioContratado=request.queryParams("servicio_contratado");
            transporte = 3;
        } else if (medio_transporte == "Transporte Analogico"){
            transporte = 4;
        }

        repositorioDeUbicaciones.guardarSiNoExiste(puntoInicio);
        repositorioDeUbicaciones.guardarSiNoExiste(puntoFin);
        Ubicacion ubicacion = repositorioDeUbicaciones.buscar(puntoInicio.getCalle(), puntoInicio.getAltura());
        Ubicacion ubicacionFin = repositorioDeUbicaciones.buscar(puntoFin.getCalle(), puntoFin.getAltura());
        tramo.setPuntoInicio(ubicacion);
        tramo.setPuntoFin(ubicacionFin);
        repositorioDeTramos.guardarSiNoExiste(tramo);
        trayecto.agregarTramo(tramo);
        repo.guardar(trayecto);
        response.redirect("/miembro/"+ request.params("id") +"/trayectos/"+ request.params("id_trayecto") +"/agregar");//Pantalla de agregar tramos
        return response;
    }


    public Response editarTramo(Request request, Response response){
//        Tramo tramo = this.repositorioDeTramos.buscar(Integer.valueOf(request.params("id_tramo")));
//
//        this.repositorioDeTramos.guardar(tramo);
//        response.redirect("/miembro/"+ request.params("id") +"/trayectos" + request.params("id_trayecto") + "/editar");
        response.redirect("funciona");
        return response;
    }

}