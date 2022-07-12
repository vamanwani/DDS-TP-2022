package domain.organizacion;

import domain.calculoHC.CalculadoraHCOrganizacion;
import domain.consumo.Consumo;
import domain.consumo.PeriodoDeImputacion;
import domain.consumo.TipoPeriodicidad;
import domain.miembro.Miembro;
import domain.miembro.Usuario;
import domain.ubicacion.Ubicacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Organizacion {
    private TipoDeOrganizacion tipoDeOrganizacion;
    private ClasificaciónDeOrg clasificacionDeOrg;
    private List<Sector> sectores;
    private String razonSocial;
    private Ubicacion ubicacion;
    private Usuario usuario;
    private List<Consumo> consumos = new ArrayList<Consumo>();

    public List<Consumo> getConsumos() {
        return consumos;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public Set<Miembro> listarMiembros(){
        Set<Miembro> miembroSet;
        miembroSet= (Set<Miembro>) this.sectores.stream().map(sector -> sector.getMiembros());
        return miembroSet;
    }

    public void setTipoDeOrganizacion(TipoDeOrganizacion tipoDeOrganizacion) {
      this.tipoDeOrganizacion = tipoDeOrganizacion;
    }

    public void agregarConsumo(Consumo consumo){
        this.consumos.add(consumo);
    }

    public double calcularHCOrganizacion (PeriodoDeImputacion periodoACalcular) {
        return new CalculadoraHCOrganizacion().calcularHC(getConsumos(), periodoACalcular);
                //+ sectores.stream().mapToDouble(sector -> sector.calcularHCSector()).sum();
    }
}
