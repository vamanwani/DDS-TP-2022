package domain.server;


import domain.controllers.*;

import domain.helpers.PermisoHelper;
import domain.middlewares.AuthMiddleware;
import domain.models.entities.usuarios.Permiso;
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

        Spark.path("/login", () -> {
            Spark.get("", loginController::pantallaDeLogin, engine);
            Spark.post("", loginController::login);
            Spark.post("/logout", loginController::logout);
            // PARA LO DE CREAR LA CUENTA HAY QUE HACER LO DEL VALIDADOR DE CONTRASENIAS
        });

        Spark.path("/organizaciones/:id", () -> {
            Spark.get("/", organizacionController::mostrarMenu, engine); // MENU ORG
            Spark.post("/registrar_mediciones", organizacionController::registrarMediciones); // Regstrar mediciones
            Spark.get("/aceptar_vinculacion", organizacionController::mostrarSolicitantes, engine);
            Spark.put("/aceptar_vinculacion", organizacionController :: actualizarMiembros);
            Spark.get("/recomendaciones", organizacionController::mostrarRecomendaciones, engine);
            Spark.get("/reporte", organizacionController::mostrarReportes, engine);
        });

        Spark.path("/miembro/:id", () -> {
            Spark.get("/", miembroController::mostrarMenu, engine); // MENU MIEMBRO
            Spark.get("/trayectos", miembroController::mostrarTrayectos, engine);
            Spark.get("/trayectos/:id_trayecto/tramos", miembroController::mostrarTramosDeTrayecto, engine);
            Spark.get("/organizaciones", miembroController::mostrarOrganizaciones, engine);
            Spark.get("/reporte", miembroController::mostrarReportes, engine);
        });

        Spark.path("/reporte", () -> {

        });

        Spark.path("/agente_sectorial", () -> {
            Spark.get("/", agenteSectorialController::mostrarMenu, engine); // MENU AGENTE
            Spark.get("/recomendaciones", agenteSectorialController::mostrarRecomendaciones, engine);
            Spark.get("/reporte", agenteSectorialController::mostrarReportes, engine);
        });
    }

    private static void metodosDePrueba() {

    }
}
