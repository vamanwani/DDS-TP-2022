package domain.organizacion;

import domain.consumo.Consumo;
import domain.miembro.Miembro;
import domain.miembro.Usuario;
import domain.ubicacion.Ubicacion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Organizacion {
    private TipoDeOrganizacion tipoDeOrganizacion;
    private ClasificaciónDeOrg clasificacionDeOrg;
    private List<Sector> sectores;
    private String razonSocial;
    private Ubicacion ubicacion;
    private Usuario usuario;
    private List<Consumo> consumos;

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

  public double calcularHCOrganizacion(){
    //Todo
  }




}
