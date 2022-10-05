package domain.sectorTerritorial;

import java.util.Set;

public class Pais {
    public void setSectoresTerritoriales(Set<SectorTerritorial> sectoresTerritoriales) {
        this.sectoresTerritoriales = sectoresTerritoriales;
    }


    private Set<SectorTerritorial> sectoresTerritoriales;

    public Pais(){

    }

    public double calcularHCPais(){
        return sectoresTerritoriales.stream().mapToDouble(s -> s.calcularHCSectorTerritorial(null)).sum();
    }

    public Set<SectorTerritorial> getSectoresTerritoriales() {
        return sectoresTerritoriales;
    }

}
