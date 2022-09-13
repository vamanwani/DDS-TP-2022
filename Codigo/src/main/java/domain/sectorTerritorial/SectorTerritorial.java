package domain.sectorTerritorial;

import domain.consumo.PeriodoDeImputacion;
import domain.organizacion.Organizacion;

import javax.persistence.*;
import java.io.IOException;
import java.util.List;
@Entity
@Table(name = "sectorTerritorial")
public class SectorTerritorial {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "nombre")
    private String nombre;



    @Embedded
    private AgenteSectorial agenteSectorial;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "localidad_id")
    private List<Localidad> localidades;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizacion_id")
    private List<Organizacion> organizaciones;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipoSector")
    private TipoSectorTerritorial tipoSector;

    public void agregarOrganizacion(Organizacion organizacion){
        if(localidades.contains(organizacion.getUbicacion().idLocalidad()) && !organizaciones.contains(organizacion)){
            organizaciones.add(organizacion);
        }
    }

    public double calcularHCSectorTerritorial(){
        PeriodoDeImputacion periodoDeImputacion = null;
        return organizaciones.stream().mapToDouble(o -> {
            try {
                return o.calcularHCOrganizacion(periodoDeImputacion);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).sum();
    }

    public List<Organizacion> getOrganizaciones() {
        return organizaciones;
    }

}
