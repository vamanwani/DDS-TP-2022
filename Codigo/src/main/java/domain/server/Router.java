package domain.server;


import domain.controllers.*;

import domain.middlewares.AuthMiddleware;
import domain.middlewares.Middleware;

import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import domain.spark.BooleanHelper;
import domain.spark.HandlebarsTemplateEngineBuilder;

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

        Spark.path("/login", () -> {
            Spark.get("", loginController::pantallaDeLogin, engine);
            Spark.post("", loginController::login);
            Spark.post("/signup", loginController::signup);
            Spark.post("/logout", loginController::logout);
            // PARA LO DE CREAR LA CUENTA HAY QUE HACER LO DEL VALIDADOR DE CONTRASENIAS
        });

        Spark.path("/organizaciones/:id", () -> {
            //Spark.before("", AuthMiddleware::verificarSesion);
            //Spark.before("/*", AuthMiddleware::verificarSesion);

            Spark.get("/", organizacionController::mostrarMenu, engine); // MENU ORG
            Spark.post("/registrar_mediciones", organizacionController::registrarMediciones); // TODO VINCULAR EL ARCHIVO EXCEL
            Spark.get("/aceptar_vinculacion", organizacionController::mostrarSolicitantes, engine);
            Spark.put("/aceptar_vinculacion", organizacionController :: actualizarMiembros);
            Spark.get("/recomendaciones", organizacionController::mostrarRecomendaciones, engine);
            Spark.get("/hc", organizacionController::mostrarHC,engine);

            // REPORTE
            Spark.get("/reporte", organizacionController::mostrarReportes, engine);
        });

        Spark.path("/miembro/:id", () -> {
            //Spark.before("", AuthMiddleware::verificarSesion);
            //Spark.before("/*", AuthMiddleware::verificarSesion);

            Spark.get("/", miembroController::mostrarMenu, engine); // MENU MIEMBRO, MUESTRA
            Spark.get("/organizaciones", miembroController::mostrarOrganizaciones, engine);//MUESTRA
            Spark.get("/hc", miembroController::mostrarHC);
            Spark.post("/organizaciones", miembroController::vincularAOrg);

            // REPORTE
            Spark.get("/reporte", miembroController::mostrarReportes, engine);//MUESTRA

            // MANIPULACION DE TRAYECTOS -> SIENDO TRAYECTO UN RECURSO ANIDADO
            Spark.path("/trayectos",() -> {
                Spark.get("", trayectoController::mostrarTrayectos, engine);
                Spark.get("/", trayectoController::mostrarTrayectos, engine);
                Spark.get("/:id_trayecto/tramos", trayectoController::mostrarTramosDeTrayecto, engine);
                Spark.get("/agregar", trayectoController::agregarTrayecto, engine);
                Spark.post("", trayectoController::crearTrayecto);// TODO
            });
        });

        Spark.path("/agente_sectorial", () -> {
            //Spark.before("", AuthMiddleware::verificarSesion);
            //Spark.before("/*", AuthMiddleware::verificarSesion);

            Spark.get("/", agenteSectorialController::mostrarMenu, engine); // MENU AGENTE
            Spark.get("/recomendaciones", agenteSectorialController::mostrarRecomendaciones, engine);
            Spark.get("/reporte", agenteSectorialController::mostrarReportes, engine); // REPORTE
        });
    }

    private static void metodosDePrueba() {

    }
}
