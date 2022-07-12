package domain.miembro;

import domain.organizacion.Sector;
import domain.recorridos.Trayecto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Miembro {
    private String apellido;
    private String nombre;
    private int numeroDeDocumento;
    private String tipoDeDocumento;
    private List<Sector> trabajos;
    private Usuario usuario;
    private List<Trayecto> trayectos;
    private String mail;
    private String telefono;
    private String linkRecomendacion;


    public Miembro(
            String apellido,
            String nombre,
            int numeroDeDocumento,
            String tipoDeDocumento,
            Usuario usuario)
    {
        this.apellido = apellido;
        this.nombre = nombre;
        this.numeroDeDocumento = numeroDeDocumento;
        this.tipoDeDocumento = tipoDeDocumento;
        this.trabajos = new ArrayList<>();
        this.usuario = usuario;
        this.trayectos = new ArrayList<>();
    }

    public void setTrayectos(List<Trayecto> trayectos) {
        this.trayectos = trayectos;
    }

    public void setTrabajos(List<Sector> trabajos) {
        this.trabajos = trabajos;
    }


    public double calcularDistanciaTotal() {

        return trayectos.stream().mapToDouble(trayecto -> {
            try {
                return trayecto.calculoDistanciaTotal();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).sum();

    }

    public void recibirRecomendacion(String link) {
        this.linkRecomendacion = link;
    }
    public String getLinkRecomendacion(){
        return linkRecomendacion;
    }
}
