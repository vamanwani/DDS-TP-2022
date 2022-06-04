package domain.geodds;

import domain.services.entities.Distancia;
import domain.services.entities.ListaDeLocalidades;
import domain.ubicacion.Ubicacion;

import java.io.IOException;

public interface GeoddsServceAdapter {
    public Distancia distancia(Ubicacion origen,Ubicacion destino) throws IOException;
    public ListaDeLocalidades localidades() throws IOException;
}
