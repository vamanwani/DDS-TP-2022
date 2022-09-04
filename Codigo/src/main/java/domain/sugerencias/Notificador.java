package domain.sugerencias;

import domain.miembro.Miembro;
import domain.organizacion.Organizacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Notificador {
    private List<Organizacion> organizaciones;
    private List<Comando> comandos;
    private Miembro miembro;

    public Notificador(){
        this.comandos = new ArrayList<Comando>();
        this.organizaciones = new ArrayList<Organizacion>();
        //this.comandos.add(new NotificarPorEmail());
        this.comandos.add(new NotificarPorWhatsApp());
        Organizacion unaOrganizacion = new Organizacion();
        agregarOrganizacion(unaOrganizacion);
    }



    /*
    *
    *
    * */


    public void notificarOrganizaciones(String link){
        this.organizaciones.forEach(org -> org.notificarContactos(link));
    }
    public void agregarOrganizacion(Organizacion unaOrganizacion){
        this.organizaciones.add(unaOrganizacion);
    }

    public static void main(String[] args){
         int valorPorMinuto = 60000;
         int valorPorHora = valorPorMinuto * 60;
         int valorPorDia = valorPorHora * 24;
         int periodicidad = valorPorDia;


        Timer timer = new Timer();
        TimerTask task = new CalendarioContactos();
        timer.schedule(task, 0000, periodicidad);
    }
}
