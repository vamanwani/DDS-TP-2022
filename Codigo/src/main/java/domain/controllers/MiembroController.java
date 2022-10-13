package domain.controllers;

import domain.models.repos.RepositorioDeMiembros;
import spark.ModelAndView;

public class MiembroController {

    private RepositorioDeMiembros repo;

    public MiembroController() {
        this.repo = new RepositorioDeMiembros();
    }

    // Mostrar trayectos "miembros/:id/trayectos"
    // Editar trayectos "miembros/:id/trayectos/:id_trayecto/tramos"
    // Agregar trayectos POST DE "miembros/:id/trayectos/:id_trayecto/tramos"
    // Vincular a organizaciones "miembros/:id/organizaciones/vincular"
    // Organizaciones "miembros/:id/organizaciones"

    public ModelAndView mostrarTrayectos(){
        return new ModelAndView();
    }

    public ModelAndView editarTrayectos(){
        return new ModelAndView();
    }

    public ModelAndView mostrarOrganizaciones(){
        return new ModelAndView();
    }





}
