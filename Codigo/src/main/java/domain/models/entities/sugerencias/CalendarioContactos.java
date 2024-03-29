package domain.models.entities.sugerencias;

import domain.models.entities.organizacion.Organizacion;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class CalendarioContactos extends TimerTask {
    private List<Comando> comandos;
    private List<Organizacion> organizaciones;
    public void setOrganizaciones(List<Organizacion> organizaciones){
        this.organizaciones = organizaciones;
    }

    public void setComandos(List<Comando> comandos) {
        this.comandos = comandos;
    }

    public void CalendarioContactos(){
        this.comandos = new ArrayList<>();
        this.organizaciones = new ArrayList<>();
    }


    public void setComandos(Comando unNotificador){
        this.comandos.add(unNotificador);
    }
    @Override
    public void run() {
        for(Organizacion org : organizaciones){

            comandos.forEach(unComando -> unComando.notificar("link", org));
        }
    }
}
