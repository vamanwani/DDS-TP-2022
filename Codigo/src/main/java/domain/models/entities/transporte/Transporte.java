package domain.models.entities.transporte;

import domain.models.entities.ubicacion.Ubicacion;
import domain.services.adapters.ServicioGeodds;

import javax.persistence.*;
import java.io.IOException;
//@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Transporte {
    private ServicioGeodds distanciaAPI;
    private Integer id;

    public String getId() {
        return id.toString();
    }

    public void setDistanciaAPI() throws IOException {
        this.distanciaAPI = ServicioGeodds.getInstance();
    }

    public void registrarConsumoDeCombustible(){
        //TODO
    }

    public double distancia(Ubicacion puntoInicio, Ubicacion puntoFin) throws IOException {
        return distanciaAPI.distancia(puntoInicio,puntoFin).distancia();
    }
}
