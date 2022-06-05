package domain.geodds;

import domain.services.entities.Distancia;
import domain.services.entities.ListaDeLocalidades;
import domain.services.entities.Localidad;
import domain.ubicacion.Ubicacion;

import java.io.IOException;
import java.util.List;

public interface GeoddsServceAdapter {
    public Distancia distancia(Ubicacion origen,Ubicacion destino) throws IOException;
    public List<Localidad> localidades() throws IOException;

    public int localidadesRespuesta() throws IOException;
}
