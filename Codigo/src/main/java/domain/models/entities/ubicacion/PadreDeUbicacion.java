package domain.models.entities.ubicacion;

import domain.services.entities.ListaDeLocalidades;
import domain.services.entities.Localidad;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PadreDeUbicacion {
    //private ListaDeLocalidades localidades;
    private List<Localidad> localidades;
    public int obtenerIdLocalidad(String nombreLocalidad){
        /*return
                localidades.stream().filter(loc->loc.getNombre()==nombreLocalidad.toUpperCase()).
                        collect(Collectors.toCollection(ArrayList::new)).get(0).getId();

                           */
        for(Localidad localidad : localidades)
        {
            if(localidad.getNombre().equals(nombreLocalidad.toUpperCase()))
            {
                return localidad.getId();
            }
        }
        return -1;
    }

    public void setLocalidades(List<Localidad> localidades) {
        this.localidades = localidades;
    }

    public List<Localidad> getLocalidades() {        return localidades;
    }
}
