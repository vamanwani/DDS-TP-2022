package domain.server;


import domain.controllers.*;

import domain.middlewares.AuthMiddleware;
import domain.middlewares.Middleware;

import domain.models.entities.miembro.Adminisitrador;
import spark.Route;
import spark.Spark;
import spark.TemplateViewRoute;
import spark.template.handlebars.HandlebarsTemplateEngine;
import domain.spark.BooleanHelper;
import domain.spark.HandlebarsTemplateEngineBuilder;

import javax.servlet.MultipartConfigElement;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Router {
    private static HandlebarsTemplateEngine engine;

    private static void initEngine() {
        Router.engine = HandlebarsTemplateEngineBuilder
                .create()
                .withDefaultHelpers()
                .withHelper("isTrue", BooleanHelper.isTrue)
                .build();
    }

    public static void init() {
        Router.initEngine();
        Spark.staticFileLocation("/public");
        Router.configure();
    }

    private static void configure() {

        LoginController loginController = new LoginController();
        OrganizacionController organizacionController = new OrganizacionController();
        AgenteSectorialController agenteSectorialController = new AgenteSectorialController();
        MiembroController miembroController = new MiembroController();
        TrayectoController trayectoController = new TrayectoController();
        AdministradorController administradorController = new AdministradorController();

        Spark.path("/login", () -> {
            Spark.get("", loginController::pantallaDeLogin, engine);
            Spark.post("", loginController::login);
            Spark.post("/signup", loginController::signup);
            // PARA LO DE CREAR LA CUENTA HAY QUE HACER LO DEL VALIDADOR DE CONTRASENIAS
        });

        Spark.path("/logout", () -> {
            Spark.get("", loginController::logout);
        });


        Spark.path("/organizaciones/:id", () -> {
            //Spark.before("", AuthMiddleware::verificarSesion);
            //Spark.before("/*", AuthMiddleware::verificarSesion);

            Spark.get("", organizacionController::mostrarMenu, engine); // MENU ORG
            Spark.get("/registro_mediciones", organizacionController::registroMediciones, engine);
            Spark.post("/registrar_mediciones", organizacionController::registrarMediciones); // TODO VINCULAR EL ARCHIVO EXCEL
            Spark.get("/aceptar_vinculacion", organizacionController::mostrarSolicitantes, engine);
            Spark.put("/aceptar_vinculacion", organizacionController :: actualizarMiembros);
            Spark.get("/recomendaciones", organizacionController::mostrarRecomendaciones, engine);
            Spark.get("/hc", organizacionController::mostrarHC,engine);
            Spark.post("/aceptar_vinculacion/:id_solicitud/aceptar", organizacionController::aceptarSolicitud);
            Spark.post("/aceptar_vinculacion/:id_solicitud/rechazar", organizacionController::rechazarSolicitud);

            // REPORTE
            Spark.get("/reporte", organizacionController::mostrarReportes, engine);
        });

        Spark.path("/miembro/:id", () -> {
            //Spark.before("", AuthMiddleware::verificarSesion);
            //Spark.before("/*", AuthMiddleware::verificarSesion);
            Spark.get("", miembroController::mostrarMenu, engine); // MENU MIEMBRO, MUESTRA
            Spark.get("/organizaciones", miembroController::mostrarOrganizaciones, engine);//MUESTRA
            Spark.get("/hc", miembroController::mostrarHC);
            Spark.post("/organizaciones/:id_organizacion/unirse", miembroController::vincularAOrg);


            // REPORTE
            Spark.get("/reporte", miembroController::mostrarReportes, engine);//MUESTRA

            // MANIPULACION DE TRAYECTOS -> SIENDO TRAYECTO UN RECURSO ANIDADO
            Spark.path("/trayectos",() -> {
                Spark.get("", trayectoController::mostrarTrayectos, engine);
                Spark.get("/", trayectoController::mostrarTrayectos, engine);
                Spark.get("/agregar", trayectoController::crearTrayecto);
                Spark.get("/:id_trayecto/agregar", trayectoController::agregarTrayecto, engine);
                Spark.get("/:id_trayecto/editar", trayectoController::mostarTrayecto, engine);
                Spark.get("/:id_trayecto/editar/:id_tramo/editar", trayectoController::mostrarTramo, engine);
                Spark.post("/:id_trayecto", trayectoController::definirTrayecto);

                Spark.get("/:id_trayecto/tramos", trayectoController::mostrarTramosDeTrayecto, engine);
                Spark.post("/:id_trayecto/tramos/agregar_tramo", trayectoController::crearTramo);
                Spark.post("/:id_trayecto/tramos/:id_tramo/editar_tramo", trayectoController::editarTramo);
            });
        });

        Spark.path("/agente_sectorial/:id", () -> {
            //Spark.before("", AuthMiddleware::verificarSesion);
            //Spark.before("/*", AuthMiddleware::verificarSesion);
            Spark.get("", agenteSectorialController::mostrarMenu, engine); // MENU AGENTE
            Spark.get("/recomendaciones", agenteSectorialController::mostrarRecomendaciones, engine);
            Spark.get("/reporte", agenteSectorialController::mostrarReportes, engine); // REPORTE
        });

        Spark.path("/administrador/:id", () -> {
            Spark.post("/generar_org", administradorController::generar_org);
            Spark.post("/generar_agente", administradorController::generar_agente);
        });

        Spark.path("/prohibido",()->{
            Spark.get("", loginController::prohibido, engine);
        });
    }

    private static void metodosDePrueba() {

    }
}
