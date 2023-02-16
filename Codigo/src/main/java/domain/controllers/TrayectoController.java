package domain.controllers;

import domain.models.entities.consumo.FactorDeEmision;
import domain.models.entities.consumo.PeriodoDeImputacion;
import domain.models.entities.consumo.TipoPeriodicidad;
import domain.models.entities.miembro.Miembro;
import domain.models.entities.miembro.Usuario;
import domain.models.entities.organizacion.Sector;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

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
    RepositorioDeConsumos repositorioDeConsumos = new RepositorioDeConsumos();

    public ModelAndView mostrarTrayectos(Request request, Response response){
        try{
            String idMimebro = request.params("id");
            Miembro miembro = this.repositorioDeMiembros.buscar(Integer.valueOf(request.params("id")));
            List<Trayecto> trayectos = this.repo.buscarTodos(new Integer(idMimebro));
            return new ModelAndView(new HashMap<String, Object>(){{
                put("miembro", miembro);
                put("trayectos", trayectos);
            }}, "/Miembro/editarTrayectos.hbs"); // MODIFICAR ESTO
        } catch (Exception ex){
            return new ModelAndView(null, "/Miembro/editarTrayectos.hbs");
        }
    }

    public ModelAndView mostrarTrayectosEliminated(Request request, Response response){
        try{
            String idMimebro = request.params("id");
            Miembro miembro = this.repositorioDeMiembros.buscar(Integer.valueOf(request.params("id")));
            List<Trayecto> trayectos = this.repo.buscarTodos(new Integer(idMimebro));
            return new ModelAndView(new HashMap<String, Object>(){{
                put("miembro", miembro);
                put("trayectos", trayectos);
                put("eliminated", true);
            }}, "/Miembro/editarTrayectos.hbs"); // MODIFICAR ESTO
        } catch (Exception ex){
            return new ModelAndView(null, "/Miembro/editarTrayectos.hbs");
        }
    }

    public ModelAndView mostarTrayecto(Request request, Response response){
        try{
            Miembro miembro = this.repositorioDeMiembros.buscar(Integer.valueOf(request.params("id")));
            String idTrayecto = request.params("id_trayecto");
            Trayecto trayecto = this.repo.buscar(Integer.valueOf(idTrayecto));
            return new ModelAndView(new HashMap<String, Object>(){{
                put("miembro", miembro);
                put("trayecto", trayecto);
                put("tramos", trayecto.getTramos());
            }}, "/Miembro/editarTrayecto.hbs");
        } catch (Exception ex){
            return new ModelAndView(null, "");// "no such trayecto"
        }
    }


    public ModelAndView agregarTramoSuccess(Request request, Response response){
        try{
            Miembro miembro = this.repositorioDeMiembros.buscar(Integer.valueOf(request.params("id")));
            String idTrayecto = request.params("id_trayecto");
            Trayecto trayecto = this.repo.buscar(Integer.valueOf(idTrayecto));
            return new ModelAndView(new HashMap<String, Object>(){{
                put("miembro", miembro);
                put("trayecto", trayecto);
                put("tramos", trayecto.getTramos());
                put("tramosuccess", true);
            }}, "/Miembro/agregarTrayecto.hbs");
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
        List<Transporte> transportes = this.repositoriosDeTransporte.buscarTodos();
        List<Provincia> provincias = this.repositorioDeLocalidades.retornarProvincias();
//        List<Localidad> localidades = this.repositorioDeLocalidades.retornarLocalidades();
        List<Miembro> miembros = this.repositorioDeMiembros.retornarMiembros();
        List<Tramo> tramosExistentes = this.repositorioDeTramos.buscarTodosLosTramosDelMiembro(Math.toIntExact(miembro.getId()));
        List<Transporte> subtes = this.repositoriosDeTransporte.buscarTodosLosSubtes();
        List<Transporte> colectivos = this.repositoriosDeTransporte.buscarTodosLosColectivos();
        List<Transporte> trenes = this.repositoriosDeTransporte.buscarTodosLosTrenes();


        List<Localidad> bsas = this.repositorioDeLocalidades.buscarProvNombre("BUENOS AIRES").getLocalidades();
        List<Localidad> catamarca = this.repositorioDeLocalidades.buscarProvNombre("CATAMARCA").getLocalidades();
        List<Localidad> chaco = this.repositorioDeLocalidades.buscarProvNombre("CHACO").getLocalidades();
        List<Localidad> chubut = this.repositorioDeLocalidades.buscarProvNombre("CHUBUT").getLocalidades();
        List<Localidad> cordoba = this.repositorioDeLocalidades.buscarProvNombre("CORDOBA").getLocalidades();
        List<Localidad> corrientes = this.repositorioDeLocalidades.buscarProvNombre("CORRIENTES").getLocalidades();
        List<Localidad> caba = this.repositorioDeLocalidades.buscarProvNombre("CIUDAD DE BUENOS AIRES").getLocalidades();
        List<Localidad> entre_rios = this.repositorioDeLocalidades.buscarProvNombre("ENTRE RIOS").getLocalidades();
        List<Localidad> formosa = this.repositorioDeLocalidades.buscarProvNombre("FORMOSA").getLocalidades();
        List<Localidad> jujuy = this.repositorioDeLocalidades.buscarProvNombre("JUJUY").getLocalidades();
        List<Localidad> la_pampa = this.repositorioDeLocalidades.buscarProvNombre("LA PAMPA").getLocalidades();
        List<Localidad> la_rioja = this.repositorioDeLocalidades.buscarProvNombre("LA RIOJA").getLocalidades();
        List<Localidad> mendoza = this.repositorioDeLocalidades.buscarProvNombre("MENDOZA").getLocalidades();
        List<Localidad> misiones = this.repositorioDeLocalidades.buscarProvNombre("MISIONES").getLocalidades();
        List<Localidad> neuqen = this.repositorioDeLocalidades.buscarProvNombre("NEUQUEN").getLocalidades();
        List<Localidad> rio_negro = this.repositorioDeLocalidades.buscarProvNombre("RIO NEGRO").getLocalidades();
        List<Localidad> salta = this.repositorioDeLocalidades.buscarProvNombre("SALTA").getLocalidades();
        List<Localidad> san_juan = this.repositorioDeLocalidades.buscarProvNombre("SAN JUAN").getLocalidades();
        List<Localidad> san_luis = this.repositorioDeLocalidades.buscarProvNombre("SAN LUIS").getLocalidades();
        List<Localidad> santa_cruz = this.repositorioDeLocalidades.buscarProvNombre("SANTA CRUZ").getLocalidades();
        List<Localidad> santa_fe = this.repositorioDeLocalidades.buscarProvNombre("SANTA FE").getLocalidades();
        List<Localidad> santiago_del_estero = this.repositorioDeLocalidades.buscarProvNombre("SANTIAGO DEL ESTERO").getLocalidades();
        List<Localidad> tierra_del_fuego = this.repositorioDeLocalidades.buscarProvNombre("TIERRA DEL FUEGO").getLocalidades();
        List<Localidad> tucuman = this.repositorioDeLocalidades.buscarProvNombre("TUCUMAN").getLocalidades();



        miembros.remove(miembro);
        List<Miembro> miembrosMismoTrabajo = new ArrayList<>();

        for(Miembro miem : miembros){
            for(Sector sector : miem.getTrabajos()){
                if(miembro.getTrabajos().contains(sector)){
                    miembrosMismoTrabajo.add(miem);
                }
            }
        }
        return new ModelAndView(new HashMap<String,Object>(){{
            put("subtes", subtes);
            put("colectivo", colectivos);
            put("trenes", trenes);
            put("miembro", miembro);
            put("miembro_id", miembro.getId());
            put("trayecto_id", trayecto.getId());
            put("tramos", trayecto.getTramos());
            put("provincias", provincias);
            put("loc_bsas", bsas);
            put("loc_catamarca", catamarca);
            put("loc_chaco", chaco);
            put("loc_chubut", chubut);
            put("loc_cordoba", cordoba);
            put("loc_corrientes", corrientes);
            put("loc_caba", caba);
            put("loc_entre_rios", entre_rios);
            put("loc_formosa", formosa);
            put("loc_jujuy", jujuy);
            put("loc_la_pampa", la_pampa);
            put("loc_la_rioja", la_rioja);
            put("loc_mendoza", mendoza);
            put("loc_misiones", misiones);
            put("loc_neuquen", neuqen);
            put("loc_rio_negro", rio_negro);
            put("loc_salta", salta);
            put("loc_san_juan", san_juan);
            put("loc_san_luis", san_luis);
            put("loc_santa_cruz", santa_cruz);
            put("loc_santa_fe", santa_fe);
            put("loc_santiago_del_estero", santiago_del_estero);
            put("loc_tierra_del_fuego", tierra_del_fuego);
            put("loc_tucuman", tucuman);
            put("miembros", miembrosMismoTrabajo);
            put("tramosExistentes", tramosExistentes);
        }}, "/Miembro/agregarTrayecto.hbs");
    }

    public ModelAndView agregarTrayectoSuccess(Request request, Response response){
        try{
            String idMimebro = request.params("id");
            Miembro miembro = this.repositorioDeMiembros.buscar(Integer.valueOf(request.params("id")));
            List<Trayecto> trayectos = this.repo.buscarTodos(new Integer(idMimebro));
            return new ModelAndView(new HashMap<String, Object>(){{
                put("miembro", miembro);
                put("trayectos", trayectos);
                put("trayectosuccess", true);
            }}, "/Miembro/editarTrayectos.hbs"); // MODIFICAR ESTO
        } catch (Exception ex){
            return new ModelAndView(null, "/Miembro/editarTrayectos.hbs");
        }
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
        trayecto.setFecha(LocalDate.of(Integer.valueOf(request.queryParams("anio_periodo")), Integer.valueOf(request.queryParams("mes_periodo")),
                Integer.valueOf(request.queryParams("dia_periodo"))));
        PeriodoDeImputacion periodoDeImputacion = new PeriodoDeImputacion(trayecto.getFecha().getMonthValue(), trayecto.getFecha().getYear(), "MENSUAL");
        trayecto.setNombre(request.queryParams("nombre"));
        trayecto.setDescripcion(request.queryParams("descripcion"));
        trayecto.setMiembro(this.repositorioDeMiembros.buscar(Integer.valueOf(request.params("id"))));
        trayecto.setPeriodoDeImputacion(periodoDeImputacion);
        this.repositorioDeConsumos.guardarSiNoExistePeriodicidad(periodoDeImputacion);
        this.repo.guardar(trayecto);
        response.redirect("/miembro/"+ request.params("id") +"/trayectos/trayectosuccess");
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
        Trayecto trayecto = this.repo.buscar(Integer.valueOf(request.params("id_trayecto")));
        Miembro miembro = this.repositorioDeMiembros.buscar(Integer.valueOf(request.params("id")));
        Tramo tramo = new Tramo();

        //TODO intentamos usar el metodo de distancia pero rompe antes de mostrar el resultado, no llega al redirect final
        Localidad localidadInicio = repositorioDeLocalidades.buscar(Integer.valueOf(request.queryParams("localidad_inicio")));
        //localidadInicio.setId(24);
        Localidad localidadFin = repositorioDeLocalidades.buscar(Integer.valueOf(request.queryParams("localidad_fin")));
        //localidadFin.setId(25);
//        TODO, para la distancia con la API
        Ubicacion puntoInicio = new Ubicacion(request.queryParams("punto_inicio_calle"),
                Integer.valueOf(request.queryParams("punto_inicio_altura")),
                localidadInicio);
        Ubicacion puntoFin = new Ubicacion(request.queryParams("punto_fin_calle"),
                Integer.valueOf(request.queryParams("punto_fin_altura")),
                localidadFin);
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
            ServicioContratado transporte = new ServicioContratado(servicioContratado);
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
        response.redirect("/miembro/"+ request.params("id") +"/trayectos/"+ request.params("id_trayecto") +"/tramosuccess");
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
        response.redirect("/miembro/"+ request.params("id") +"/trayectos/"+ request.params("id_trayecto") +"/tramosuccess");
        return response;
    }

    public Response eliminarTrayecto(Request request, Response response){
        Trayecto trayecto = this.repo.buscar(Integer.valueOf(request.params("id_trayecto")));
        Miembro miembro = this.repositorioDeMiembros.buscar(Integer.valueOf(request.params("id")));
        trayecto.setEstado(false);
        this.repo.guardar(trayecto);
        response.redirect("/miembro/"+ miembro.getId()+"/trayectos/eliminated");
        return response;
    }
}