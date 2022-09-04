package domain.sectorTerritorial;

import domain.consumo.PeriodoDeImputacion;
import domain.organizacion.Organizacion;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "sectorTerritorial")
public class SectorTerritorial {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
    @Transient
    private AgenteSectorial agenteSectorial;
    @Transient
    private List<Localidad> localidades;
    @Transient
    private List<Organizacion> organizaciones;
    @Transient
    private TipoSectorTerritorial tipoSector;

    public void agregarOrganizacion(Organizacion organizacion){
        if(localidades.contains(organizacion.getUbicacion().idLocalidad()) && !organizaciones.contains(organizacion)){
            organizaciones.add(organizacion);
        }
    }

    public double calcularHCSectorTerritorial(){
        PeriodoDeImputacion periodoDeImputacion = null;
        return organizaciones.stream().mapToDouble(o -> o.calcularHCOrganizacion(periodoDeImputacion)).sum();
    }

}
