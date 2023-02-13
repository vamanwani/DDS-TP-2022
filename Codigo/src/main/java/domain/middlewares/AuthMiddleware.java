package domain.middlewares;

import domain.models.entities.miembro.TipoUsuario;
import domain.models.entities.miembro.Usuario;
import domain.models.entities.organizacion.Organizacion;
import domain.models.repos.RepositorioDeMiembros;
import domain.models.repos.RepositorioDeOrganizaciones;
import domain.models.repos.RepositorioDeUsuarios;
import spark.Request;
import spark.Response;

public class AuthMiddleware {

    static RepositorioDeUsuarios repositorioDeUsuarios = new RepositorioDeUsuarios();

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
}
