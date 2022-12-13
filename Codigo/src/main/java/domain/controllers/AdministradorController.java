package domain.controllers;

import domain.models.entities.consumo.TipoConsumo;
import domain.models.entities.miembro.Adminisitrador;
import domain.models.entities.miembro.TipoUsuario;
import domain.models.entities.miembro.Usuario;
import domain.models.entities.organizacion.ClasificaciónDeOrg;
import domain.models.entities.organizacion.Organizacion;
import domain.models.entities.organizacion.Sector;
import domain.models.entities.organizacion.TipoDeOrganizacion;
import domain.models.entities.sectorTerritorial.*;
import domain.models.entities.transporte.TipoTransportePublico;
import domain.models.entities.transporte.Transporte;
import domain.models.entities.transporte.TransportePublico;
import domain.models.entities.ubicacion.Parada;
import domain.models.entities.ubicacion.Ubicacion;
import domain.models.repos.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.*;
import java.util.stream.Collectors;

public class AdministradorController {
    RepositorioDeOrganizaciones repositorioDeOrganizaciones = new RepositorioDeOrganizaciones();
    RepositorioDeLocalidades repositorioDeLocalidades = new RepositorioDeLocalidades();
    RepositorioDeAgentesSectoriales repositorioDeAgentesSectoriales = new RepositorioDeAgentesSectoriales();
    RepositorioDeTiposConsumo repositorioDeTiposConsumo = new RepositorioDeTiposConsumo();
    RepositorioDeAdministradores repositorioDeAdministradores = new RepositorioDeAdministradores();
    RepositoriosDeTransporte repositoriosDeTransporte = new RepositoriosDeTransporte();
    RepositorioDeUbicaciones repositorioDeUbicaciones = new RepositorioDeUbicaciones();
    RepositorioDeParadas repositorioDeParadas = new RepositorioDeParadas();
    RepositorioDeUsuarios repositorioDeUsuarios = new RepositorioDeUsuarios();

    public ModelAndView mostrarMenu(spark.Request request, spark.Response response){
        Adminisitrador adminisitrador = this.repositorioDeAdministradores.buscar(Integer.valueOf(request.params("id")));
        return new ModelAndView(new HashMap<String, Object>(){{
            put("administrador", adminisitrador);
        }} , "/Admin/indexAdmin.hbs");// el viewname que sea igual al home de organizacion
    }

    public ModelAndView crearAgente(Request request, Response response){
        Adminisitrador adminisitrador = this.repositorioDeAdministradores.buscar(Integer.valueOf(request.params("id")));
        return new ModelAndView(new HashMap<String, Object>(){{
            put("administrador", adminisitrador);
        }}, "Admin/registroAgSec.hbs");
    }

    public Response crearOrg(Request request, Response response) {
        Adminisitrador adminisitrador = this.repositorioDeAdministradores.buscar(Integer.valueOf(request.params("id")));
        List<Localidad> localidades = this.repositorioDeLocalidades.retornarLocalidades();
        List<SectorTerritorial> sectorTerritorials = this.repositorioDeAgentesSectoriales.buscarTodosLosSectores();
        Organizacion organizacion = new Organizacion();
        this.repositorioDeOrganizaciones.guardar(organizacion);
        response.redirect("/administrador/" + adminisitrador.getId()+ "/crear_org/" + organizacion.getId());
        return response;
    }

    public ModelAndView gestionarOrgNueva(Request request, Response response){
        Adminisitrador adminisitrador = this.repositorioDeAdministradores.buscar(Integer.valueOf(request.params("id")));
        List<Localidad> localidades = this.repositorioDeLocalidades.retornarLocalidades();
        Organizacion organizacion = this.repositorioDeOrganizaciones.buscar(Integer.valueOf(request.params("id_organizacion")));
        List<SectorTerritorial> sectorTerritorials = this.repositorioDeAgentesSectoriales.buscarTodosLosSectores();
        //        List<Provincia> provincias = this.repos
        this.repositorioDeOrganizaciones.guardar(organizacion);
        return new ModelAndView(new HashMap<String, Object>(){{
            put("administrador", adminisitrador);
            put("localidades", localidades);
            put("organizacion", organizacion);
            put("sectorterritorial", sectorTerritorials);
        }}, "Admin/registroOrg.hbs");
    }

    public Response crearTransportePublico(Request request, Response response){
        Adminisitrador adminisitrador = this.repositorioDeAdministradores.buscar(Integer.valueOf(request.params("id")));
        List<Localidad> localidades = this.repositorioDeLocalidades.retornarLocalidades();
        Transporte transporte = new TransportePublico();
        this.repositoriosDeTransporte.guardar(transporte);
        response.redirect("/administrador/" + adminisitrador.getId() + "/crear_transporte_publico/" + transporte.getId());
        return response;
    }

    public ModelAndView gestionNuevoTransporte(Request request, Response response){
        Adminisitrador adminisitrador = this.repositorioDeAdministradores.buscar(Integer.valueOf(request.params("id")));
        List<Localidad> localidades = this.repositorioDeLocalidades.retornarLocalidades();
        Transporte transporte = this.repositoriosDeTransporte.buscar(Integer.valueOf(request.params("id_transporte")));
        this.repositoriosDeTransporte.guardar(transporte);
        return new ModelAndView(new HashMap<String, Object>(){{
            put("administrador", adminisitrador);
            put("localidades", localidades);
            put("transporte", transporte);
        }}, "Admin/registroTransPub.hbs");
    }

    public Response definirDatosTransporte(Request request, Response response){
        Adminisitrador adminisitrador = this.repositorioDeAdministradores.buscar(Integer.valueOf(request.params("id")));
        TransportePublico transporte = (TransportePublico) this.repositoriosDeTransporte.buscar(Integer.valueOf((request.params("id_transporte"))));
        String tipoTransportePublico = request.queryParams("tipo_transporte_publico");
        TipoTransportePublico tipoTransportePublicoEnum = null;
        if (tipoTransportePublico.equals("Tren")) {tipoTransportePublicoEnum = TipoTransportePublico.Tren;}
        else if (tipoTransportePublico.equals("Subte")){tipoTransportePublicoEnum = TipoTransportePublico.Subte;}
        else if (tipoTransportePublico.equals("Colectivo")){tipoTransportePublicoEnum = TipoTransportePublico.Colectivo;}
        transporte.setLinea(request.queryParams("linea"));
        transporte.setTransporte(tipoTransportePublicoEnum);
        transporte.setNombre("Transporte Publico");
        this.repositoriosDeTransporte.guardar(transporte);
        response.redirect("/administrador/" + adminisitrador.getId());
        return response;
    }

    public Response agregarParada(Request request, Response response){
        Adminisitrador adminisitrador = this.repositorioDeAdministradores.buscar(Integer.valueOf(request.params("id")));
        TransportePublico transporte = (TransportePublico) this.repositoriosDeTransporte.buscar(Integer.valueOf(request.params("id_transporte")));
        Localidad localidad = this.repositorioDeLocalidades.buscar(Integer.valueOf(request.queryParams("localidad_parada")));
        Ubicacion ubicacion = new Ubicacion(request.queryParams("calle_parada"),
                Integer.valueOf(request.queryParams("altura_parada")),
                localidad);
        double distAnterior = Double.parseDouble(request.queryParams("dist_parada_ant"));
        double distPost = Double.parseDouble(request.queryParams("dist_parada_sig"));
        Parada parada = new Parada(ubicacion);
        transporte.agregarParada(parada);
        parada.setDistanciaParadaAnterior(distAnterior);
        parada.setDistanciaParadaSiguiente(distPost);
        String tipoParada = request.queryParams("tipo_parada");
        if (tipoParada.equals("inicial")){
            transporte.setParadaInicial(parada);
        } else if (tipoParada.equals("final")){
            transporte.setParadaFinal(parada);
        }
        this.repositorioDeUbicaciones.guardarSiNoExiste(ubicacion);
        this.repositorioDeParadas.guardar(parada);
        this.repositoriosDeTransporte.guardar(transporte);
        response.redirect("/administrador/" + adminisitrador.getId() + "/crear_transporte_publico/" + transporte.getId());
        return response;
    }

    public Response generar_org(Request request, Response response) {
        TipoDeOrganizacion tipoDeOrganizacion = TipoDeOrganizacion.valueOf(request.queryParams("tipo_org"));
        ClasificaciónDeOrg clasificaciónDeOrg = new ClasificaciónDeOrg(request.queryParams("clasificacion"));
        SectorTerritorial sectorTerritorial = this.repositorioDeAgentesSectoriales.buscarSector(Integer.valueOf(request.queryParams("sector_territorial_id")));
        String razonSocial = request.queryParams("razon_social");
        String localidadId = request.queryParams("localidad");
        Localidad localidad = this.repositorioDeLocalidades.buscar(Integer.valueOf(localidadId));
        Ubicacion ubicacion = new Ubicacion(request.queryParams("calle"), Integer.valueOf(request.queryParams("altura")), localidad);
        Usuario usuario = new Usuario(request.queryParams("nombre_usuario"),
                request.queryParams("contrasenia"), request.queryParams("email"),
                request.queryParams("telfono"), TipoUsuario.ORGANIZACION);
        Organizacion org = this.repositorioDeOrganizaciones.buscar(Integer.valueOf(request.params("id_organizacion")));
        org.setClasificacionDeOrg(clasificaciónDeOrg);
        org.setTipoDeOrganizacion(tipoDeOrganizacion);
        org.setRazonSocial(razonSocial);
        org.setUbicacion(ubicacion);
        org.setUsuario(usuario);
        sectorTerritorial.setOrganizaciones(org);
        repositorioDeUsuarios.guardar(usuario);
        repositorioDeAgentesSectoriales.guardarSector(sectorTerritorial);
        repositorioDeUbicaciones.guardarSiNoExiste(ubicacion);
        repositorioDeOrganizaciones.guardarClasificacion(clasificaciónDeOrg);
        repositorioDeOrganizaciones.guardar(org);
        response.redirect("/administrador/" + request.params("id"));
        return response;
    }

    public Response generar_agente(Request request, Response response) {
        Adminisitrador adminisitrador = this.repositorioDeAdministradores.buscar(Integer.valueOf(request.params("id")));
        String nombreAgente = request.queryParams("nombre_agente");
        String nombreSector = request.queryParams("nombre_sector");
        Usuario usuario = new Usuario(request.queryParams("nombre_usuario"),
                request.queryParams("contrasenia"), request.queryParams("email"),
                request.queryParams("telfono"), TipoUsuario.AGENTESECTORIAL);
        TipoSectorTerritorial tipoSectorTerritorial = TipoSectorTerritorial.valueOf(request.queryParams("tipo_sector"));

        AgenteSectorial agenteSectorial = new AgenteSectorial(nombreAgente, usuario);
        SectorTerritorial sectorTerritorial = new SectorTerritorial(nombreSector);
        sectorTerritorial.setTipoSector(tipoSectorTerritorial);
        sectorTerritorial.setAgenteSectorial(agenteSectorial);
        repositorioDeAgentesSectoriales.guardar(agenteSectorial);
        repositorioDeUsuarios.guardar(usuario);
        repositorioDeAgentesSectoriales.guardarSector(sectorTerritorial);
        response.redirect("/administrador/" + adminisitrador.getId());
        return response;
    }

    public ModelAndView gestionarFETiposConsumo(Request request, Response response){
        Adminisitrador adminisitrador = this.repositorioDeAdministradores.buscar(Integer.valueOf(request.params("id")));
        List<TipoConsumo> tiposConsumos = this.repositorioDeTiposConsumo.buscarTodos();
        return new ModelAndView(new HashMap<String,Object>(){{
            put("administrador", adminisitrador);
            put("tipos", tiposConsumos);
        }}, "/Admin/gestionFE.hbs");
    }

    public ModelAndView gestionarFETransportes(Request request, Response response){
        Adminisitrador adminisitrador = this.repositorioDeAdministradores.buscar(Integer.valueOf(request.params("id")));
        List<Transporte> transportes = this.repositoriosDeTransporte.buscarTodos();
        List<Transporte> transporteList = new ArrayList<>();
        for(Transporte transporte : transportes){
            if(!transporteList.stream().map(t -> t.getModelo()).collect(Collectors.toList()).contains(transporte.getModelo())){
                transporteList.add(transporte);
            }
        }
        return new ModelAndView(new HashMap<String,Object>(){{
            put("administrador", adminisitrador);
            put("transportes", transporteList);//cambiar a transportesFiltrados
        }}, "/Admin/gestionFETransportes.hbs");
    }

    public Response actualizarFE(Request request, Response response){
        Adminisitrador adminisitrador = this.repositorioDeAdministradores.buscar(Integer.valueOf(request.params("id")));
        TipoConsumo tipoConsumo = this.repositorioDeTiposConsumo.buscar(Integer.valueOf(request.params("id_tipo")));
        tipoConsumo.setFe(Double.parseDouble(request.queryParams("valor_fe")));
        this.repositorioDeTiposConsumo.guardar(tipoConsumo);
        response.redirect("/administrador/"+adminisitrador.getId()+"/gestionar_fe_tipos_consumo");
        return response;
    }

    public Response actualizarFETransporte(Request request, Response response) {
        Adminisitrador adminisitrador = this.repositorioDeAdministradores.buscar(Integer.valueOf(request.params("id")));
        Transporte transporte = this.repositoriosDeTransporte.buscar(Integer.valueOf(request.params("id_transporte")));
        List<Transporte> transportes = this.repositoriosDeTransporte.buscarTodos();
        List<Transporte> transporteList = transportes.stream().filter(t -> t.getModelo().equals(transporte.getModelo())).collect(Collectors.toList());
        for(Transporte transporte1 : transporteList){
            transporte1.setFactorDeEmision(Double.parseDouble(request.queryParams("valor_fe")));
            this.repositoriosDeTransporte.guardar(transporte1);
        }

        response.redirect("/administrador/"+adminisitrador.getId()+"");
        return response;
    }

    public Response agregarSector(Request request, Response response) {
        Adminisitrador adminisitrador = this.repositorioDeAdministradores.buscar(Integer.valueOf(request.params("id")));
        Organizacion organizacion = this.repositorioDeOrganizaciones.buscar(Integer.valueOf(request.params("id_organizacion")));
        Sector sector = new Sector(request.queryParams("nombre_sector"));
        organizacion.agregarSectores(sector);
        sector.setOrganizacion(organizacion);
        this.repositorioDeOrganizaciones.guardarSector(sector);
        this.repositorioDeOrganizaciones.guardar(organizacion);

        response.redirect("/administrador/"+ adminisitrador.getId() + "/crear_org/" + organizacion.getId());
        return null;
    }
}
