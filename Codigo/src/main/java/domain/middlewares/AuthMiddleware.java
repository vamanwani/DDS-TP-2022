package domain.middlewares;

import domain.models.entities.organizacion.Organizacion;
import domain.models.repos.RepositorioDeOrganizaciones;
import spark.Request;
import spark.Response;

public class AuthMiddleware {


    public static Response verificarSesion(Request request, Response response) {
        if(request.session().isNew() || request.session().attribute("id") == null) {
            response.redirect("/prohibido");
        }
        return response;
    }
}
