package domain.models.entities.sectorTerritorial;

import domain.models.entities.consumo.PeriodoDeImputacion;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "pais")
public class Pais {

    @GeneratedValue
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "nombre")
    private String nombre;



    public Pais(){

    }

    public Pais(String nombre) {
        this.nombre = nombre;
    }

    public double calcularHCPais(Set<SectorTerritorial> sectoresTerritoriales, PeriodoDeImputacion periodoDeImputacion){
        return sectoresTerritoriales.stream().mapToDouble(s -> s.calcularHCSectorTerritorial(periodoDeImputacion)).sum();
    }

    public int getId() {
        return id;
    }
}
