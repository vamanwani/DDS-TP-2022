package domain.organizacion;

import domain.consumo.Consumo;
import domain.miembro.Miembro;
import domain.miembro.Usuario;
import domain.ubicacion.Ubicacion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Organizacion {
    private TipoDeOrganizacion tipoDeOrganizacion;
    private ClasificaciónDeOrg clasificacionDeOrg;
    private ArrayList<Sector> sectores;
    private String razonSocial;
    private Ubicacion ubicacion;
    private Usuario usuario;
    private ArrayList<Consumo> consumos;

    private static Organizacion instancia = null;

    public static Organizacion getInstancia(){ // patron singleton
        if (instancia == null){
            instancia = new Organizacion();
        }
        return instancia;
    }
    private Organizacion(){ // constructor privado por singleton
    }

    public Set<Miembro> listarMiembros(){
        Set<Miembro> miembroSet;
        miembroSet= (Set<Miembro>) this.sectores.stream().map(sector -> sector.getMiembros());
        return miembroSet;
    }
  public void setTipoDeOrganizacion(TipoDeOrganizacion tipoDeOrganizacion) {
      this.tipoDeOrganizacion = tipoDeOrganizacion;
  }






}
