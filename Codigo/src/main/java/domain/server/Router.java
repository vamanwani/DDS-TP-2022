package domain.server;


import domain.controllers.*;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import domain.spark.BooleanHelper;
import domain.spark.HandlebarsTemplateEngineBuilder;

import java.nio.channels.spi.SelectorProvider;

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



        Spark.path("/", () -> { //TODO cambiar a /inicio_sesion o similar para que no rompa en heroku
            Spark.get("", loginController::pantallaDeLogin, engine);
            Spark.post("/log_in", loginController::login);
            Spark.post("/log_in/signup", loginController::signup);
            Spark.get("/log_in/fail", loginController::fail, engine);
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
            Spark.post("/registrar_mediciones", organizacionController::registrarMediciones, engine); // TODO VINCULAR EL ARCHIVO EXCEL
            Spark.get("/aceptar_vinculacion", organizacionController::mostrarSolicitantes, engine);
            Spark.get("/aceptar_vinculacion/success", organizacionController::mostrarSolicitantesSuccess, engine);
            Spark.get("/aceptar_vinculacion/fail", organizacionController::mostrarSolicitantesFail, engine);
            Spark.put("/aceptar_vinculacion", organizacionController :: actualizarMiembros);
            Spark.get("/recomendaciones", organizacionController::mostrarRecomendaciones, engine);
            Spark.get("/hc", organizacionController::mostrarHC,engine);
            Spark.get("/hc/:valor", organizacionController::mostrarHC,engine);
            Spark.post("/aceptar_vinculacion/:id_solicitud/aceptar", organizacionController::aceptarSolicitud);
            Spark.post("/aceptar_vinculacion/:id_solicitud/rechazar", organizacionController::rechazarSolicitud);
            Spark.post("/hc", organizacionController::calcularHC,engine);
            Spark.post("/reportes", organizacionController::obtenerReportes,engine);


            // REPORTE
            Spark.get("/reportes", organizacionController::mostrarReportes, engine);
        });

        Spark.path("/miembro/:id", () -> {
            //Spark.before("", AuthMiddleware::verificarSesion);
            //Spark.before("/*", AuthMiddleware::verificarSesion);
            Spark.get("", miembroController::mostrarMenu, engine); // MENU MIEMBRO, MUESTRA
            Spark.get("/organizaciones", miembroController::mostrarOrganizaciones, engine);//MUESTRA
            Spark.get("/organizaciones/solicitada", miembroController::mostrarOrganizacionesS, engine);
            Spark.get("/hc", miembroController::mostrarHC, engine);
            Spark.post("/organizaciones/:id_organizacion/unirse", miembroController::vincularAOrg);


            // REPORTE
            Spark.get("/reportes", miembroController::mostrarReportes, engine);//MUESTRA
//            Spark.post("/reportes", miembroController::obtenerReportes,engine);

            // MANIPULACION DE TRAYECTOS -> SIENDO TRAYECTO UN RECURSO ANIDADO
            Spark.path("/trayectos",() -> {
                Spark.get("", trayectoController::mostrarTrayectos, engine);
                Spark.get("/", trayectoController::mostrarTrayectos, engine);
                Spark.get("/eliminated", trayectoController::mostrarTrayectosEliminated, engine);
                Spark.get("/agregar", trayectoController::crearTrayecto);
                Spark.get("/:id_trayecto/agregar", trayectoController::agregarTrayecto, engine);
                Spark.get("/:id_trayecto/editar", trayectoController::mostarTrayecto, engine);
                Spark.post("/:id_trayecto", trayectoController::definirTrayecto);
                Spark.post("/:id_trayecto/eliminar", trayectoController::eliminarTrayecto);

                Spark.get("/:id_trayecto/tramos", trayectoController::mostrarTramosDeTrayecto, engine);
                Spark.post("/:id_trayecto/tramos/agregar_tramo", trayectoController::crearTramo);
                Spark.post("/:id_trayecto/tramos/agregar_tramo_existente/:id_tramo", trayectoController::agregarTramoExistente);
                Spark.get("/:id_trayecto/tramos/:id_tramo/editar_tramo", trayectoController::editarTramo, engine);
                Spark.post("/:id_trayecto/tramos/:id_tramo/editar_tramo", trayectoController::actualizarTramo);
                Spark.get("/:id_trayecto/agregar_tramo", trayectoController::agregarTramo, engine);
                Spark.post("/:id_trayecto/tramos/:id_tramo/cambiar_a_existente/:id", trayectoController::cambiarTramoAExistente);
            });
        });

        Spark.path("/agente_sectorial/:id", () -> {
            //Spark.before("", AuthMiddleware::verificarSesion);
            //Spark.before("/*", AuthMiddleware::verificarSesion);
            Spark.get("", agenteSectorialController::mostrarMenu, engine); // MENU AGENTE
            Spark.get("/recomendaciones", agenteSectorialController::mostrarRecomendaciones, engine);
            Spark.get("/reporte", agenteSectorialController::mostrarReportes, engine); // REPORTE
            Spark.post("/reportes", agenteSectorialController::obtenerReporte,engine);
        });

        Spark.path("/administrador/:id", () -> {
            Spark.get("", administradorController::mostrarMenu, engine);

            Spark.get("/crear_agente", administradorController::crearAgente, engine);
            Spark.post("/crear_agente", administradorController::generar_agente);


            Spark.post("/crear_org", administradorController::crearOrg);
            Spark.get("/crear_org/:id_organizacion", administradorController::gestionarOrgNueva, engine);
            Spark.post("/crear_org/:id_organizacion/agregar_sector", administradorController::agregarSector);
            Spark.post("/crear_org/:id_organizacion", administradorController::generar_org);


            Spark.get("/gestionar_fe_tipos_consumo", administradorController::gestionarFETiposConsumo, engine);
            Spark.get("/gestionar_fe_transportes", administradorController::gestionarFETransportes, engine);
            Spark.post("/actualizar_fe/:id_tipo", administradorController::actualizarFE);
            Spark.post("/actualizar_fe_transporte/:id_transporte", administradorController::actualizarFETransporte);

            Spark.post("/crear_transporte_publico", administradorController::crearTransportePublico);
            Spark.get("/crear_transporte_publico/:id_transporte", administradorController::gestionNuevoTransporte, engine);
            Spark.post("/crear_transporte_publico/:id_transporte", administradorController::definirDatosTransporte);
            Spark.post("/crear_transporte_publico/:id_transporte/agregar_parada", administradorController::agregarParada);
            //Spark.post("/crear_transporte_publico", administradorController::actualizarFETransporte);
        });

        Spark.path("/prohibido",()->{
            Spark.get("", loginController::prohibido, engine);
        });
    }

    private static void metodosDePrueba() {

    }


}
