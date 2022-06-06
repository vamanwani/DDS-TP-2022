package domain.miembro;

import domain.organizacion.Sector;
import domain.recorridos.Trayecto;

import java.util.ArrayList;

public class Miembro {
    private String apellido;
    private String nombre;
    private int numeroDeDocumento;
    private String tipoDeDocumento;
    private ArrayList<Sector> trabajos;
    private Usuario usuario;
    private ArrayList<Trayecto> trayectos;

    public Miembro(
            String apellido,
            String nombre,
            int numeroDeDocumento,
            String tipoDeDocumento,
            ArrayList<Sector> trabajos,
            Usuario usuario,
            ArrayList<Trayecto> trayectos) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.numeroDeDocumento = numeroDeDocumento;
        this.tipoDeDocumento = tipoDeDocumento;
        this.trabajos = trabajos;
        this.usuario = usuario;
        this.trayectos = trayectos;
    }

    public double calcularDistanciaTotal(){

        return trayectos.stream().mapToDouble(trayecto -> trayecto.calculoDistanciaTotal()).sum();

    }

}
