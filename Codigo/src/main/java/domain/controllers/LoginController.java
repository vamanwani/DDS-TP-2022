package domain.controllers;

import com.sun.org.apache.xpath.internal.operations.Or;
import domain.models.entities.miembro.Miembro;
import domain.models.entities.miembro.Usuario;
import domain.models.entities.miembro.tipoUsuario;
import domain.models.entities.organizacion.Organizacion;
import domain.models.entities.sectorTerritorial.AgenteSectorial;
import domain.models.entities.verificadorContasenia.Validador;
import domain.models.repos.RepositorioDeAgentesSectoriales;
import domain.models.repos.RepositorioDeMiembros;
import domain.models.repos.RepositorioDeOrganizaciones;
import domain.services.dbManager.EntityManagerHelper;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.FileNotFoundException;

public class LoginController {

    RepositorioDeOrganizaciones repositorioDeOrganizaciones = new RepositorioDeOrganizaciones();
    RepositorioDeAgentesSectoriales repositorioDeAgentesSectoriales = new RepositorioDeAgentesSectoriales();
    RepositorioDeMiembros repositorioDeMiembros = new RepositorioDeMiembros();

    // LOGIN
    // LOGUT
    // SIGNUP

    public ModelAndView pantallaDeLogin(Request request, Response response) {
        return new ModelAndView(null, "/Login/login.hbs");
    }
    public Response login(Request request, Response response) {
        try {
            String query = "from "
                    + Usuario.class.getName()
                    + " WHERE nombre = '"
                    + request.queryParams("nombre_de_usuario")
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
                if (usuario.getTipoUsuario() == tipoUsuario.MIEMBRO){
                    String queryMiembro = "from "+ Miembro.class.getName() +" where usuario_id=" + usuario.getId();
                    Miembro miembro = (Miembro) EntityManagerHelper
                            .getEntityManager()
                            .createQuery(queryMiembro)
                            .getSingleResult();
                    response.redirect("/Miembro/miembro/"+miembro.getId());
                } else if (usuario.getTipoUsuario() == tipoUsuario.ORGANIZACION){
                    String queryOrg = "from "+ Organizacion.class.getName() +" where usuario_id=" + usuario.getId();
                    Organizacion organizacion = (Organizacion) EntityManagerHelper
                            .getEntityManager()
                            .createQuery(queryOrg)
                            .getSingleResult();
                    response.redirect("/Organizacion/organizacion"+organizacion.getId());
                } else if (usuario.getTipoUsuario() == tipoUsuario.AGENTESECTORIAL){
                    String queryAgente = "from agente_sectorial where usuario_id=" + usuario.getId();
                    AgenteSectorial agenteSectorial = (AgenteSectorial) EntityManagerHelper
                            .getEntityManager()
                            .createQuery(queryAgente)
                            .getSingleResult();
                    response.redirect("//organizacion"+queryAgente.getId());
                }
            }
            else {
                response.redirect("/login");}
            }

        catch (Exception ex) {
            response.redirect("/login");
        }
        return response;
    }

    public Response logout(Request request, Response response) {
        request.session().invalidate();
        response.redirect("login");
        return response;
    }

    public ModelAndView prohibido(Request request, Response response) {
        return new ModelAndView(null, "prohibido.hbs");
    }
    public Response signup(Request request,Response response) throws FileNotFoundException {
        String nombreUsuario=request.queryParams("nombre_de_usuario");
        String contrasenia=request.queryParams("contrasenia");
        Validador validador= new Validador();
//        validador.usarTodosLosValidadores();

        try {
            String query = "from "
                    + Usuario.class.getName()
                    + " WHERE nombre = '"
                    + request.queryParams("nombre_de_usuario")
                    + "'";

            Usuario usuario = (Usuario) EntityManagerHelper
                    .getEntityManager()
                    .createQuery(query)
                    .getSingleResult();

            if(usuario != null) {
                response.redirect("prohibido.hbs");
            }
            else {

                    Usuario usuarioCreado=new Usuario(nombreUsuario,contrasenia);
//                    String tipoUsuario=request.params("tipo_usuario");

                    EntityManagerHelper.beginTransaction();
                    EntityManagerHelper.getEntityManager().persist(usuarioCreado);
                    EntityManagerHelper.commit();

//                    if (tipoUsuario == "miembro"){
//                        Miembro miembro = new Miembro();
//                        miembro.setUsuario(usuarioCreado);
////                        miembro.setNombre(new MiembroController::llenarDatos);
//                        repositorioDeMiembros.guardar(miembro);
//
//                    } else if (tipoUsuario == "organizacion"){
//                        Organizacion organizacion=new Organizacion();
//                        organizacion.setUsuario(usuarioCreado);
////                        organizacion.setRazonSocial(new OrganizacionController::llenarDatos);
//                        repositorioDeOrganizaciones.guardar(organizacion);
//
//                    } else if (tipoUsuario == "agenteSec"){
//                        AgenteSectorial agenteSectorial=new AgenteSectorial();
//                        agenteSectorial.setUsuario(usuarioCreado);
//                        repositorioDeAgentesSectoriales.guardar(agenteSectorial);
//                    } else {
//                        response.redirect("templates/prohibido.hbs");
//                    }
                }
                response.redirect("/login");

        }
        catch (Exception ex) {
            response.redirect("/login/login.hbs");
        }
        return response;
    }
}
