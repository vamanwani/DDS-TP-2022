package domain.sectorTerritorial;

import domain.consumo.PeriodoDeImputacion;
import domain.organizacion.Organizacion;
import domain.organizacion.Sector;
import javafx.beans.binding.BooleanExpression;

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

    @Embedded
    private AgenteSectorial agenteSectorial;

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
        return this.tipoSector == TipoSectorTerritorial.PROCVINCIAS;
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


}
