package domain.organizacion;

import domain.miembro.Miembro;

import java.io.IOException;
import java.util.*;

public class Sector {
    private Miembro agenteSectorial; // REVISAR
    private List<Miembro> miembros;
    private String nombre;
    private Organizacion organizacion;

    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public List<Miembro> getMiembros() {
        return miembros;
    }

    public Sector(Miembro agenteSectorial, String nombre) {
        this.agenteSectorial = agenteSectorial;
        this.miembros = new ArrayList<>();
        this.nombre = nombre;
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

    public double calculoHCSector() {
        return miembros.stream().mapToDouble( m -> {
            try {
                return m.calcularHCMiembro();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return 0;
        }).sum();
    }

    public double HCPerCapita(Sector sector){
        return this.calculoHCSector() / miembros.size();
    }

}
