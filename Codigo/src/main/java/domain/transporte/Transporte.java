package domain.transporte;

import domain.services.adapters.ServicioGeodds;
import domain.ubicacion.Ubicacion;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.io.IOException;
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Transporte {
    private ServicioGeodds distanciaAPI;
    private String id;

    public String getId() {
        return id;
    }

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
