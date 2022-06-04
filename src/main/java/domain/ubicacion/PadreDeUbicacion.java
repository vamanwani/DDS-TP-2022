package domain.ubicacion;

import domain.services.entities.ListaDeLocalidades;
import domain.services.entities.Localidad;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class PadreDeUbicacion {
    private ListaDeLocalidades localidades;
    public int obtenerIdLocalidad(String nombreLocalidad){
        return
                localidades.getListado().stream().filter(loc->loc.getNombre()==nombreLocalidad).
                        collect(Collectors.toCollection(ArrayList::new)).get(0).getId();
    }

}
