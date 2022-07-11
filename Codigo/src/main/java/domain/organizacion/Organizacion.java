package domain.organizacion;

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

        //if (tipo periodicidad == mes)
            //filter segun ese mes/anio
        // else // si o si va a ser anio
            //filter segun ese anio
        //hc de lo filtrado;

        List<Consumo> auxiliar = null;

        if (periodoACalcular.getPeriodicidad() == TipoPeriodicidad.ANUAL){
            auxiliar = this.getConsumos().stream().filter(consumo -> consumo.getPeriodicidad().getAnio() == periodoACalcular.getAnio()).collect(Collectors.toList());
        } else if (periodoACalcular.getPeriodicidad() == TipoPeriodicidad.MENSUAL){
            auxiliar = this.getConsumos().stream().filter(consumo ->  (consumo.getPeriodicidad().getAnio() == periodoACalcular.getAnio()
            && consumo.getPeriodicidad().getMes() == periodoACalcular.getMes())).collect(Collectors.toList());
        }

        return auxiliar.stream().mapToDouble(c -> c.calcularHC()).sum();
    }
}
