package domain.controllers;

import domain.models.entities.miembro.Miembro;
import domain.models.entities.miembro.Usuario;
import domain.models.entities.miembro.TipoUsuario;
import domain.models.entities.organizacion.Organizacion;
import domain.models.entities.sectorTerritorial.AgenteSectorial;
import domain.models.entities.verificadorContasenia.Validador;
import domain.models.repos.RepositorioDeAgentesSectoriales;
import domain.models.repos.RepositorioDeMiembros;
import domain.models.repos.RepositorioDeOrganizaciones;
import domain.models.repos.RepositorioDeUsuarios;
import domain.services.dbManager.EntityManagerHelper;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.Console;
import java.io.FileNotFoundException;

public class LoginController {

    RepositorioDeOrganizaciones repositorioDeOrganizaciones = new RepositorioDeOrganizaciones();
    RepositorioDeAgentesSectoriales repositorioDeAgentesSectoriales = new RepositorioDeAgentesSectoriales();
    RepositorioDeMiembros repositorioDeMiembros = new RepositorioDeMiembros();
    RepositorioDeUsuarios repositorioDeUsuarios = new RepositorioDeUsuarios();

    // LOGIN
    // LOGUT
    // SIGNUP

    public ModelAndView pantallaDeLogin(Request request, Response response) {
        return new ModelAndView(null, "/Login/login.hbs");
    }
    public Response login(Request request, Response response) {
        try {
            String nombreDeUsuario = request.queryParams("nombre_de_usuario");
            String contrasenia = request.queryParams("contrasenia");

            Usuario usuario = this.repositorioDeUsuarios.buscar(nombreDeUsuario, contrasenia);

            if(usuario != null) {

                request.session(true);
                request.session().attribute("id", usuario.getId());

                if (usuario.getTipoUsuario() == TipoUsuario.MIEMBRO){
                    Miembro miembro = this.repositorioDeMiembros.buscarSegunUsuarioId(usuario.getId());
                    response.redirect("/miembro/"+miembro.getId());

                } else if (usuario.getTipoUsuario() == TipoUsuario.ORGANIZACION) {
                    Organizacion organizacion = this.repositorioDeOrganizaciones.buscarSegunUsuarioId(usuario.getId());
                    response.redirect("/organizaciones/" + organizacion.getId());

                } else if (usuario.getTipoUsuario() == TipoUsuario.AGENTESECTORIAL){
                    System.out.println("usuario id: " + usuario.getId());
                    AgenteSectorial agenteSectorial = this.repositorioDeAgentesSectoriales.buscarSegunUsuarioId(usuario.getId());
                    response.redirect("/agente_sectorial/"+agenteSectorial.getId()); // CONSULTAR
                }
            }
            else {
                response.redirect("/SeMeteAlElse");
            }
        }
        catch (Exception ex) {
            response.redirect("/seMeteAlCatch");
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
    public Response signup(Request request,Response response) throws FileNotFoundException {

        String nombreUsuario=request.queryParams("nombre_de_usuario");
        String contrasenia=request.queryParams("contrasenia");
        String email = request.queryParams("email");
        String telefono = request.queryParams("telefono");

        Validador validador= new Validador();
        validador.usarTodosLosValidadores();

        try {
            try{
                String query = "from "
                        + Usuario.class.getName()
                        + " WHERE nombre = '"
                        + request.queryParams("nombre_de_usuario")
                        + "'";
                Usuario user = (Usuario) EntityManagerHelper.getEntityManager().createQuery(query).getSingleResult();
                System.out.println(user.getId());
                // TODO decir que elija otro username
            } catch (Exception exception){

                if (validador.todosLosValidadores(contrasenia)){

                    Usuario usuario = new Usuario(nombreUsuario, contrasenia, email, telefono, TipoUsuario.MIEMBRO);
                    Miembro miembro = new Miembro("32532", "124", 12, "", usuario);

                    repositorioDeUsuarios.guardar(usuario);
                    repositorioDeMiembros.guardar(miembro);
                } else {
                    //TODO tirar mensaje de que elija otra contrasenia
                    response.redirect("/contraNoValida");
                }
            }
                response.redirect("/login");
        }
        catch (Exception ex) {
            response.redirect("/login/login.hbs");
        }
        return response;
    }
}