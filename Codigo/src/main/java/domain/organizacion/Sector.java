package domain.organizacion;

import domain.miembro.Miembro;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Sector {
    private Miembro agenteSectorial;
    private List<Miembro> miembros;
    private String nombre;

    public List<Miembro> getMiembros() {
        return miembros;
    }

    public Sector(Miembro agenteSectorial, String nombre) {
        this.agenteSectorial = agenteSectorial;
        this.miembros = new ArrayList<>();
        this.nombre = nombre;
    }
    public Sector(){
        this.miembros = new ArrayList<>();
    }
    public void agregarMiembro(Miembro unMiembro){
        this.miembros.add(unMiembro);
    }

    public void setMiembros(List<Miembro> miembros) {
        this.miembros = miembros;
    }

    public List<Miembro> miembrosQueDicenQuePertenecen(List<Miembro> miembrosAVerificar){
        List<Miembro> miembrosQuePertenecen = new ArrayList<Miembro>() {};
        List<Miembro> miembrosDelSector = this.getMiembros();
        for (Miembro miembro : miembrosAVerificar){
            if(miembrosDelSector.contains(miembro))
            {
                miembrosQuePertenecen.add(miembro);
            }
        }
        return miembrosQuePertenecen;
    }
}
