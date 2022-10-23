package domain.controllers;

import domain.models.entities.miembro.TipoUsuario;
import domain.models.entities.miembro.Usuario;
import domain.models.entities.organizacion.ClasificaciónDeOrg;
import domain.models.entities.organizacion.Organizacion;
import domain.models.entities.organizacion.TipoDeOrganizacion;
import domain.models.entities.sectorTerritorial.AgenteSectorial;
import domain.models.entities.sectorTerritorial.Localidad;
import domain.models.entities.ubicacion.Ubicacion;
import domain.models.repos.RepositorioDeAgentesSectoriales;
import domain.models.repos.RepositorioDeOrganizaciones;
import spark.Request;
import spark.Response;

public class AdministradorController {
    RepositorioDeOrganizaciones repositorioDeOrganizaciones = new RepositorioDeOrganizaciones();
    RepositorioDeAgentesSectoriales repositorioDeAgentesSectoriales = new RepositorioDeAgentesSectoriales();

    public Response generar_org(Request request, Response response) {
        TipoDeOrganizacion tipoDeOrganizacion = TipoDeOrganizacion.valueOf(request.queryParams("tipo_org"));
        ClasificaciónDeOrg clasificaciónDeOrg = new ClasificaciónDeOrg(request.queryParams("clasificacion"));
        String razonSocial = request.queryParams("razon_social");
        Ubicacion ubicacion = new Ubicacion(request.queryParams("calle"), Integer.valueOf(request.queryParams("altura")), new Localidad(request.queryParams("localidad")));
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
}
