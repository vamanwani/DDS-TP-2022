package domain.models.entities.organizacion;

import domain.models.entities.calculoHC.CalculadoraHCSector;
import domain.models.entities.consumo.PeriodoDeImputacion;
import domain.models.entities.consumo.TipoPeriodicidad;
import domain.models.entities.miembro.Miembro;

import javax.persistence.*;
import java.io.IOException;
import java.util.*;

@Entity
@Table(name = "sector")
public class Sector {
    @Id
    @GeneratedValue
    @Column(name = "id_sector")
    private int id;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Miembro> miembros;

    @Column(name = "nombre")
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    private Organizacion organizacion;

    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
    }

    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public List<Miembro> getMiembros() {
        return miembros;
    }

    public Sector(String nombre) {
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

    public String getNombre() {
        return nombre;
    }

    public double calcularHCSector(PeriodoDeImputacion periodoDeImputacion) throws IOException {
        if(periodoDeImputacion.getPeriodicidad().equals(TipoPeriodicidad.MENSUAL)){
            return new CalculadoraHCSector().calcularHCMensual(miembros, periodoDeImputacion);
        } else {
            return new CalculadoraHCSector().calcularHCAnual(miembros, periodoDeImputacion);
        }
    }


//
//    public double HCPorCantMiembros(){
//        return this.calcularHCSector() / miembros.size();
//    }

    public int getId() {
        return id;
    }


}
