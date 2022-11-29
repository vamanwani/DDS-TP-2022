package domain.controllers;

import domain.models.entities.consumo.TipoConsumo;
import domain.models.entities.miembro.Adminisitrador;
import domain.models.entities.miembro.TipoUsuario;
import domain.models.entities.miembro.Usuario;
import domain.models.entities.organizacion.ClasificaciónDeOrg;
import domain.models.entities.organizacion.Organizacion;
import domain.models.entities.organizacion.TipoDeOrganizacion;
import domain.models.entities.sectorTerritorial.AgenteSectorial;
import domain.models.entities.sectorTerritorial.Localidad;
import domain.models.entities.ubicacion.Ubicacion;
import domain.models.repos.*;
import org.hibernate.tuple.entity.EntityTuplizer;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;

public class AdministradorController {
    RepositorioDeOrganizaciones repositorioDeOrganizaciones = new RepositorioDeOrganizaciones();
    RepositorioDeLocalidades repositorioDeLocalidades = new RepositorioDeLocalidades();
    RepositorioDeAgentesSectoriales repositorioDeAgentesSectoriales = new RepositorioDeAgentesSectoriales();
    RepositorioDeTiposConsumo repositorioDeTiposConsumo = new RepositorioDeTiposConsumo();
    RepositorioDeAdministradores repositorioDeAdministradores = new RepositorioDeAdministradores();

    public ModelAndView mostrarMenu(spark.Request request, spark.Response response){
        Adminisitrador adminisitrador = this.repositorioDeAdministradores.buscar(Integer.valueOf(request.params("id")));
        return new ModelAndView(new HashMap<String, Object>(){{
            put("admin", adminisitrador);
        }} , "/Admin/indexAdmin.hbs");// el viewname que sea igual al home de organizacion
    }

    public Response generar_org(Request request, Response response) {
        TipoDeOrganizacion tipoDeOrganizacion = TipoDeOrganizacion.valueOf(request.queryParams("tipo_org"));
        ClasificaciónDeOrg clasificaciónDeOrg = new ClasificaciónDeOrg(request.queryParams("clasificacion"));
        String razonSocial = request.queryParams("razon_social");
        String localidadId = request.queryParams("localidad_id");
        Localidad localidad = this.repositorioDeLocalidades.buscar(Integer.valueOf(localidadId));
        Ubicacion ubicacion = new Ubicacion(request.queryParams("calle"), Integer.valueOf(request.queryParams("altura")), localidad);
        Usuario usuario = new Usuario(request.queryParams("nombre_usuario"),
                request.queryParams("contrasenia"), request.queryParams("email"),
                request.queryParams("telfono"), TipoUsuario.ORGANIZACION);
        Organizacion nueva_org = new Organizacion(tipoDeOrganizacion, clasificaciónDeOrg, razonSocial, ubicacion, usuario);
        repositorioDeOrganizaciones.guardar(nueva_org);
        response.redirect("");//TODO
        return response;
    }

    public Response generar_agente(Request request, Response response) {
        String nombre = request.queryParams("nombre");
        Usuario usuario = new Usuario(request.queryParams("nombre_usuario"),
                request.queryParams("contrasenia"), request.queryParams("email"),
                request.queryParams("telfono"), TipoUsuario.AGENTESECTORIAL);
        AgenteSectorial agenteSectorial = new AgenteSectorial(nombre, usuario);
        repositorioDeAgentesSectoriales.guardar(agenteSectorial);
        response.redirect("");//TODO
        return response;
    }

    public ModelAndView gestionarFE(Request request, Response response){
        Adminisitrador adminisitrador = this.repositorioDeAdministradores.buscar(Integer.valueOf(request.params("id")));
        List<TipoConsumo> tiposConsumos = this.repositorioDeTiposConsumo.buscarTodos();
        return new ModelAndView(new HashMap<String,Object>(){{
            put("admin", adminisitrador);
            put("tipos", tiposConsumos);
        }}, "/Admin/gestionFE.hbs"); // TODO
    }

    public Response actualizarFE(Request request, Response response){
        Adminisitrador adminisitrador = this.repositorioDeAdministradores.buscar(Integer.valueOf(request.params("id")));
        TipoConsumo tipoConsumo = this.repositorioDeTiposConsumo.buscar(Integer.valueOf(request.params("id_tipo")));
        tipoConsumo.setFe(Double.parseDouble(request.queryParams("valor_fe")));
        this.repositorioDeTiposConsumo.guardar(tipoConsumo);
        response.redirect("/administrador/"+adminisitrador.getId()+"/gestionar_fe");
        return response;
    }
}
