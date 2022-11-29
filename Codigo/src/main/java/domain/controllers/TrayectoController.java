package domain.controllers;

import domain.models.entities.consumo.FactorDeEmision;
import domain.models.entities.miembro.Miembro;
import domain.models.entities.miembro.Usuario;
import domain.models.entities.recorridos.Tramo;
import domain.models.entities.recorridos.Trayecto;
import domain.models.entities.sectorTerritorial.Localidad;
import domain.models.entities.sectorTerritorial.Provincia;
import domain.models.entities.transporte.*;
import domain.models.entities.ubicacion.Ubicacion;
import domain.models.repos.*;
import domain.services.dbManager.EntityManagerHelper;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.persistence.Entity;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class TrayectoController {
    // Mostrar trayectos "miembros/:id/trayectos"
    // Editar trayectos "miembros/:id/trayectos/:id_trayecto/tramos"
    // Agregar trayectos POST DE "miembros/:id/trayectos/:id_trayecto/tramos"
    RepositorioDeTrayectos repo = new RepositorioDeTrayectos();
    RepositorioDeUbicaciones repositorioDeUbicaciones = new RepositorioDeUbicaciones();
    RepositorioDeTramos repositorioDeTramos = new RepositorioDeTramos();
    RepositorioDeMiembros repositorioDeMiembros = new RepositorioDeMiembros();
    RepositoriosDeTransporte repositoriosDeTransporte = new RepositoriosDeTransporte();
    RepositorioDeLocalidades repositorioDeLocalidades = new RepositorioDeLocalidades();
    RepositorioDeUsuarios repositorioDeUsuarios = new RepositorioDeUsuarios();

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
        Miembro miembro = this.repositorioDeMiembros.buscar(Integer.valueOf(request.params("id")));
        Trayecto trayecto = this.repo.buscar(Integer.valueOf(request.params("id_trayecto")));
        List<Provincia> provincias = this.repositorioDeLocalidades.retornarProvincias();
        List<Localidad> localidades = this.repositorioDeLocalidades.retornarLocalidades();
        List<Miembro> miembros = this.repositorioDeMiembros.retornarMiembros();
        List<Tramo> tramosExistentes = this.repositorioDeTramos.buscarTodosLosTramosDelMiembro(Math.toIntExact(miembro.getId()));
        miembros.remove(miembro);
        return new ModelAndView(new HashMap<String,Object>(){{
            put("miembro_id", request.params("id"));
            put("trayecto_id", trayecto.getId());
            put("tramos", trayecto.getTramos());
            put("provincias", provincias);
            put("localidades", localidades);
            put("miembros", miembros);
            put("tramosExistentes", tramosExistentes);
        }}, "/Miembro/agregarTrayecto.hbs");
    }

    public Response crearTrayecto(Request request, Response response){
        Miembro miembro = this.repositorioDeMiembros.buscar(Integer.valueOf(request.params("id")));
        Trayecto nuevoTrayecto = new Trayecto();
        miembro.agregarTrayecto(nuevoTrayecto);
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

    public ModelAndView editarTramo(Request request, Response response){
        Tramo tramo = this.repositorioDeTramos.buscar(Integer.valueOf(request.params("id_tramo")));
        return new ModelAndView(new HashMap<String, Object>(){{
            put("tramo",tramo );
            put("miembro_id", request.params("id"));
            put("trayecto_id", request.params("id_trayecto"));
        }}, "/Miembro/editarTramo.hbs");
    }

    public ModelAndView agregarTramo(Request request, Response response){
        return new ModelAndView(new HashMap<String, Object>(){{
        }}, "/Miembro/agregarTramo.hbs");
    }


    public Response crearTramo(Request request, Response response) throws IOException {
        //Transporte transport = new TransporteAnalogico();
        Trayecto trayecto = this.repo.buscar(Integer.valueOf(request.params("id_trayecto")));
        Miembro miembro = this.repositorioDeMiembros.buscar(Integer.valueOf(request.params("id")));
        Tramo tramo = new Tramo();

        //TODO intentamos usar el metodo de distancia pero rompe antes de mostrar el resultado, no llega al redirect final
        //Localidad localidadInicio = repositorioDeLocalidades.buscar(Integer.valueOf(request.queryParams("localidad_inicio")));
        //localidadInicio.setId(24);
        //Localidad localidadFin = repositorioDeLocalidades.buscar(Integer.valueOf(request.queryParams("localidad_fin")));
        //localidadFin.setId(25);
//        TODO, para la distancia con la API
        Ubicacion puntoInicio = new Ubicacion(request.queryParams("punto_inicio_calle"), Integer.valueOf(request.queryParams("punto_inicio_altura")), null);
        Ubicacion puntoFin = new Ubicacion(request.queryParams("punto_fin_calle"), Integer.valueOf(request.queryParams("punto_fin_altura")), null);
        //double distancia = transport.distancia(puntoInicio, puntoFin);

        String medio_transporte= request.queryParams("medio_transporte");
        if(medio_transporte.equals("particular")){
            String tipoVehiculo = request.queryParams("tipo_vehiculo");
            String tipoCombustible = request.queryParams("tipo_combustible");
            Transporte transporte = this.repositoriosDeTransporte.buscarParticular(tipoVehiculo, tipoCombustible);
            tramo.setMedioDeTransporte(transporte);
        } else if (medio_transporte.equals("publico")){
            String linea =  request.queryParams("linea");
            String tipoTransportePublico = request.queryParams("tipo_transporte_publico");
            TransportePublico transporte= this.repositoriosDeTransporte.buscarTransportePublico(linea, tipoTransportePublico);
            tramo.setMedioDeTransporte(transporte);
        } else if (medio_transporte.equals("contratado")){
            FactorDeEmision fe = new FactorDeEmision(1.00);
            String servicioContratado=request.queryParams("tipo_servicio_contratado");
            ServicioContratado transporte = new ServicioContratado(servicioContratado, fe);
            this.repositoriosDeTransporte.guardarSiNoExisteContratado(transporte);
            tramo.setMedioDeTransporte(transporte);
        } else if (medio_transporte.equals("analogico")){
            String transporteAnalogico = request.queryParams("tipo_transporte_analogico");
            TransporteAnalogico transporte = this.repositoriosDeTransporte.buscarAnalogico(transporteAnalogico);
            tramo.setMedioDeTransporte(transporte);
        }

        try {
            Miembro acompaniante = this.repositorioDeMiembros.buscar(Integer.valueOf(request.queryParams("acompaniante_id")));
            tramo.agregarMiembroAlTramo(acompaniante);
        } catch (Exception e){
            System.out.println("pincho");
        }

        tramo.agregarMiembroAlTramo(miembro);
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
        //response.redirect(("/" + distancia));
        return response;
    }

    //TODO Hacer que los tramos se editen, para que cambie a otro tramo nuevo o uno ya existente
    public Response actualizarTramo(Request request, Response response) {
        response.redirect("acutalizar");
        return response;
    }

    public Response cambiarTramoAExistente(Request request, Response response){
        response.redirect("cambiarAExistente");
        return response;
    }


    public Response agregarTramoExistente(Request request, Response response) {
        Trayecto trayecto = this.repo.buscar(Integer.valueOf(request.params("id_trayecto")));
        Miembro miembro = this.repositorioDeMiembros.buscar(Integer.valueOf(request.params("id")));
        Tramo tramo = this.repositorioDeTramos.buscar(Integer.valueOf(request.params("id_tramo")));
        trayecto.agregarTramo(tramo);
        this.repo.guardar(trayecto);
        response.redirect("/miembro/"+ request.params("id") +"/trayectos/"+ request.params("id_trayecto") +"/agregar");
        return response;
    }
}