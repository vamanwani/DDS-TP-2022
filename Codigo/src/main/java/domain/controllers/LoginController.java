package domain.controllers;

import domain.miembro.Usuario;
import domain.services.dbManager.EntityManagerHelper;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class LoginController {

    public ModelAndView pantallaDeLogin(Request request, Response response) {
        return new ModelAndView(null, "login.hbs");
    }

    public Response login(Request request, Response response) {
        try {
            String query = "from "
                    + Usuario.class.getName()
                    + " WHERE nombre = '"
                    + request.queryParams("nombre")
                    + "' AND contrasenia='"
                    + request.queryParams("contrasenia")
                    + "'";

            Usuario usuario = (Usuario) EntityManagerHelper
                    .getEntityManager()
                    .createQuery(query)
                    .getSingleResult();

            if(usuario != null) {
                request.session(true);
                request.session().attribute("id", usuario.getId());
                response.redirect("/servicios");
            }
            else {
                response.redirect("/login");
            }
        }
        catch (Exception ex) {
            response.redirect("/login");
        }
        return response;
    }

    public Response logout(Request request, Response response) {
        request.session().invalidate();
        response.redirect("/login");
        return response;
    }

    public ModelAndView prohibido(Request request, Response response) {
        return new ModelAndView(null, "prohibido.hbs");
    }
}
