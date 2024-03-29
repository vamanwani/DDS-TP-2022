package domain.services.adapters;

import domain.models.entities.sectorTerritorial.Localidad;
import domain.services.entities.Distancia;
import domain.models.entities.ubicacion.Ubicacion;

import java.io.IOException;
import java.util.List;

public interface GeoddsServceAdapter {
    public Distancia distancia(Ubicacion origen,Ubicacion destino) throws IOException;
    public int distanciaRespuesta(Ubicacion origen,Ubicacion destino) throws IOException;
    public List<Localidad> localidades() throws IOException;
    public int localidadesRespuesta() throws IOException;
}
