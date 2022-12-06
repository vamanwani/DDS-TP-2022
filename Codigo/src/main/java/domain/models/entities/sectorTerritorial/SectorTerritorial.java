package domain.models.entities.sectorTerritorial;

import domain.models.entities.consumo.PeriodoDeImputacion;
import domain.models.entities.organizacion.Organizacion;

import javax.persistence.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "sectorTerritorial")
public class SectorTerritorial {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @OneToOne
    @JoinColumn(name = "agente_sectorial_id")
    private AgenteSectorial agenteSectorial;

    public void setAgenteSectorial(AgenteSectorial agenteSectorial) {
        this.agenteSectorial = agenteSectorial;
    }

    @Column(name = "link_recomendacion")
    private String link_recomendacion;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "localidad_id")
    private List<Localidad> localidades;

    public SectorTerritorial() {
        this.organizaciones = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizacion_id")
    private List<Organizacion> organizaciones;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipoSector")
    private TipoSectorTerritorial tipoSector;

    public SectorTerritorial(String nombre){
        this.organizaciones = new ArrayList<>();
        this.nombre = nombre;
    }

    public void setTipoSector(TipoSectorTerritorial tipoSector) {
        this.tipoSector = tipoSector;
    }

    public TipoSectorTerritorial getTipoSector() {
        return tipoSector;
    }

    public void agregarOrganizacion(Organizacion organizacion){
        if(localidades.contains(organizacion.getUbicacion().idLocalidad()) && !organizaciones.contains(organizacion)){
            organizaciones.add(organizacion);
        }
    }

    public void setOrganizaciones(Organizacion organizacion) {
        this.organizaciones.add(organizacion);
    }

    public Boolean esProvincia(){
        return this.tipoSector == TipoSectorTerritorial.PROVINCIAS;
    }

    public double calcularHCSectorTerritorial(PeriodoDeImputacion periodoDeImputacion){
        return organizaciones.stream().mapToDouble(o -> {
            try {
                return o.calcularHCOrganizacion(periodoDeImputacion);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).sum();
    }

    public double calcularHCSectorHistorico() throws IOException{
        double hc = 0;
        for (Organizacion organizacion : organizaciones){
            hc += organizacion.calcularHCOrgHistorico();
        }
        return hc;
    }

    public List<Organizacion> getOrganizaciones() {
        return organizaciones;
    }

    public String getLink_recomendacion() {
        return link_recomendacion;
    }

}
