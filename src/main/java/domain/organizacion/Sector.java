package domain.organizacion;

import domain.miembro.Miembro;

import java.util.ArrayList;

public class Sector {
    private Miembro agenteSectorial;
    private ArrayList<Miembro> miembros;
    private String nombre;

    public ArrayList<Miembro> getMiembros() {
        return miembros;
    }

    public Sector(Miembro agenteSectorial, ArrayList<Miembro> miembros, String nombre) {
        this.agenteSectorial = agenteSectorial;
        this.miembros = miembros;
        this.nombre = nombre;
    }
}
