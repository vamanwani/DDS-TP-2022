package domain.transporte;

import domain.services.adapters.ServicioGeodds;
import domain.ubicacion.Ubicacion;

import java.io.IOException;

public abstract class Transporte {
    private ServicioGeodds distanciaAPI;

    public void setDistanciaAPI() throws IOException {
        this.distanciaAPI = ServicioGeodds.getInstance();
    }

    public void registrarConsumoDeCombustible(){
        //TODO
    }

    public double distancia(Ubicacion puntoInicio,Ubicacion puntoFin) throws IOException {
        return distanciaAPI.distancia(puntoInicio,puntoFin).distancia();
    }
}
