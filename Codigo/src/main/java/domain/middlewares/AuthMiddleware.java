package domain.middlewares;

import domain.models.entities.miembro.Miembro;
import domain.models.entities.miembro.TipoUsuario;
import domain.models.entities.miembro.Usuario;
import domain.models.entities.organizacion.Organizacion;
import domain.models.entities.sectorTerritorial.AgenteSectorial;
import domain.models.repos.RepositorioDeAgentesSectoriales;
import domain.models.repos.RepositorioDeMiembros;
import domain.models.repos.RepositorioDeOrganizaciones;
import domain.models.repos.RepositorioDeUsuarios;
import spark.Request;
import spark.Response;

public class AuthMiddleware {

    static RepositorioDeMiembros repositorioDeMiembros = new RepositorioDeMiembros();
    static RepositorioDeUsuarios repositorioDeUsuarios = new RepositorioDeUsuarios();
    static RepositorioDeOrganizaciones repositorioDeOrg = new RepositorioDeOrganizaciones();
    static RepositorioDeAgentesSectoriales repositorioDeAgentes = new RepositorioDeAgentesSectoriales();

    public static Response verificarSesion(Request request, Response response) {
        if(request.session().isNew() || request.session().attribute("id") == null) {
            response.redirect("/prohibido");
        }
        return response;
    }

    public static Response verificarTipoMiembro(Request request, Response response){

        Usuario usuario = repositorioDeUsuarios.buscarSegunId(request.session().attribute("id"));

//        if (request.session().attribute("tipo_usuario").toString().equals("miembro") ){
//            response.redirect("/prohibido");
//        }

        if (usuario.getTipoUsuario() != TipoUsuario.MIEMBRO){
            response.redirect("/prohibido");
        }
        return response;
    }

    public static Response verificarTipoOrg(Request request, Response response) {

        Usuario usuario = repositorioDeUsuarios.buscarSegunId(request.session().attribute("id"));

        if (usuario.getTipoUsuario() != TipoUsuario.ORGANIZACION) {
            response.redirect("/prohibido");
        }
        return response;
    }

    public static Response verificarTipoAgente(Request request, Response response){

            Usuario usuario = repositorioDeUsuarios.buscarSegunId(request.session().attribute("id"));


            if (usuario.getTipoUsuario() != TipoUsuario.AGENTESECTORIAL){
                response.redirect("/prohibido");
            }
            return response;
    }

    public static Response verificarIdMiembro(Request request, Response response){

        Miembro miembro = repositorioDeMiembros.buscar(Integer.valueOf(request.params("id")));
        Usuario usuario = request.session().attribute("usuario_logueado");

        if (usuario.getId() != miembro.getUsuario().getId()){
            response.redirect("/prohibido");
        }
        return response;
    }

    public static Response verificarIdOrg(Request request, Response response){

        Organizacion organizacion = repositorioDeOrg.buscar(Integer.valueOf(request.params("id")));
        Usuario usuario = request.session().attribute("usuario_logueado");

        if (usuario.getId() != organizacion.getUsuario().getId()){
            response.redirect("/prohibido");
        }
        return response;
    }

    public static Response verificarIdAgente(Request request, Response response) {
        AgenteSectorial agenteSectorial = repositorioDeAgentes.buscar(Integer.valueOf(request.params("id")));
        Usuario usuario = request.session().attribute("usuario_logueado");

        if (usuario.getId() != agenteSectorial.getUsuario().getId()){
            response.redirect("/prohibido");
        }
        return response;
    }
}
