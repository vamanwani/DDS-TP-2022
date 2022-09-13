package domain.sectorTerritorial;

import java.util.Set;

public class Pais {
    private Set<SectorTerritorial> sectoresTerritoriales;

    public double calcularHCPais(){
        return sectoresTerritoriales.stream().mapToDouble(s -> s.calcularHCSectorTerritorial()).sum();
    }

    public Set<SectorTerritorial> getSectoresTerritoriales() {
        return sectoresTerritoriales;
    }
}
