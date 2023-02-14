package domain.controllers;

import domain.models.entities.miembro.Adminisitrador;
import domain.models.entities.miembro.Miembro;
import domain.models.entities.miembro.Usuario;
import domain.models.entities.miembro.TipoUsuario;
import domain.models.entities.organizacion.Organizacion;
import domain.models.entities.sectorTerritorial.AgenteSectorial;
import domain.models.entities.verificadorContasenia.Validador;
import domain.models.repos.*;
import domain.services.dbManager.EntityManagerHelper;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class LoginController {

    RepositorioDeOrganizaciones repositorioDeOrganizaciones = new RepositorioDeOrganizaciones();
    RepositorioDeAgentesSectoriales repositorioDeAgentesSectoriales = new RepositorioDeAgentesSectoriales();
    RepositorioDeMiembros repositorioDeMiembros = new RepositorioDeMiembros();
    RepositorioDeUsuarios repositorioDeUsuarios = new RepositorioDeUsuarios();
    private RepositorioDeAdministradores repositorioDeAdministradores = new RepositorioDeAdministradores();

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

            request.session().attribute("usuario_logueado", null);

            if(usuario != null) {

                request.session(true);
                request.session().attribute("id", usuario.getId());

                request.session().attribute("usuario_logueado", usuario);

                switch (usuario.getTipoUsuario()) {
                    case MIEMBRO:
                        request.session().attribute("tipo_usuario", "miembro");
                        break;
                    case ORGANIZACION:
                        request.session().attribute("tipo_usuario", "organizacion");
                        break;
                    case ADMIN:
                        request.session().attribute("tipo_usuario", "administrador");
                        break;
                    case AGENTESECTORIAL:
                        request.session().attribute("tipo_usuario", "agente_sectorial");
                        break;
                }

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
                else if (usuario.getTipoUsuario() == TipoUsuario.ADMIN) {
                    Adminisitrador adminisitrador = this.repositorioDeAdministradores.buscarSegunUsuarioId(usuario.getId());
                    response.redirect("administrador/" + adminisitrador.getId());
                }

                request.session().attribute("usuario_logueado", usuario);

            }
            else {
                response.redirect("/log_in/fail");
            }
        }
        catch (Exception ex) {
            response.redirect("/log_in/fail");
        }
        return response;
    }

    public Response logout(Request request, Response response) {
        request.session().invalidate();
        response.redirect("/");
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
        String nombre = request.queryParams("nombre");
        String apellido = request.queryParams("apellido");
        String tipoDoc = request.queryParams("tipoDoc");
        String nroDoc = request.queryParams("nroDoc");

        System.out.println(nombre);
        System.out.println(apellido);
        System.out.println(tipoDoc);
        System.out.println(nroDoc);


        Validador validador= new Validador();
       // validador.usarTodosLosValidadores();

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
                    Miembro miembro = new Miembro(apellido, nombre, nroDoc, tipoDoc, usuario);

                    repositorioDeUsuarios.guardar(usuario);
                    repositorioDeMiembros.guardar(miembro);
                } else {
                    //TODO tirar mensaje de que elija otra contrasenia

                    response.redirect("/contraNoValida");
                }
            }
                response.redirect("/");
        }
        catch (Exception ex) {
            response.redirect("/login/login.hbs");
        }
        return response;
    }

    public ModelAndView fail(Request request, Response response){
        return new ModelAndView(new HashMap<String, Object>(){{
            put("fail", true);
        }}, "/Login/login.hbs");


    }
}