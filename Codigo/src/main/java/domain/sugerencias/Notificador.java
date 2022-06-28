package domain.sugerencias;

import domain.miembro.Miembro;
import domain.organizacion.Organizacion;

import java.util.ArrayList;
import java.util.List;

public class Notificador {
    private List<Organizacion> organizaciones;
    private List<Comando> comandos;
    /*
    *
    *
    * */
    public void notificarOrganizaciones(String link){
        this.organizaciones.forEach(org -> org.notificarContactos(link));
    }

}
