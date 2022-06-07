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
    public ArrayList<Miembro> miembrosQueDicenQuePertenecen(ArrayList<Miembro> miembrosAVerificar){
        ArrayList<Miembro> miembrosQuePertenecen = new ArrayList<>();
        ArrayList<Miembro> miembrosDelSector = this.getMiembros();
        for (Miembro miembro : miembrosAVerificar){
            if(miembrosDelSector.contains(miembro))
            {
                miembrosQuePertenecen.add(miembro);
            }
        }
        return miembrosQuePertenecen;
    }
}
