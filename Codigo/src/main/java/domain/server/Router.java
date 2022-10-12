//package domain.server;
//
//
//import domain.controllers.LoginController;
//
//import domain.helpers.PermisoHelper;
//import domain.middlewares.AuthMiddleware;
//import domain.models.entities.usuarios.Permiso;
//import spark.Spark;
//import spark.template.handlebars.HandlebarsTemplateEngine;
//import domain.spark.BooleanHelper;
//import domain.spark.HandlebarsTemplateEngineBuilder;
//
//public class Router {
//    private static HandlebarsTemplateEngine engine;
//
//    private static void initEngine() {
//        Router.engine = HandlebarsTemplateEngineBuilder
//                .create()
//                .withDefaultHelpers()
//                .withHelper("isTrue", BooleanHelper.isTrue)
//                .build();
//    }
//
//    public static void init() {
//        Router.initEngine();
//        Spark.staticFileLocation("/public");
//        Router.configure();
//    }
//
//    private static void configure() {
//
//        LoginController loginController = new LoginController();
//
//
//        Spark.path("/login", () -> {
//            Spark.get("", loginController::pantallaDeLogin, engine);
//            Spark.post("", loginController::login);
//            Spark.post("/logout", loginController::logout);
//            // PARA LO DE CREAR LA CUENTA HAY QUE HACER LO DEL VALIDADOR DE CONTRASENIAS
//        });
//
//        Spark.path("/organizaciones", () -> {
//            //TODO
//        });
//
//        Spark.path("/miembro", () -> {
//
//        });
//
//        Spark.path("/reporte", () -> {
//
//        });
//
//        Spark.path("/sector_territorial", () -> {
//
//        });
//    }
//
//    private static void metodosDePrueba() {
//
//    }
//}
