package domain.organizacion;

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

    public Set<Miembro> listarMiembros(){
        Set<Miembro> miembroSet;
        //miembroSet= (Set<Miembro>) this.sectores.stream().flatMap(sector -> sector.getMiembros());
        return miembroSet;
    }
}
