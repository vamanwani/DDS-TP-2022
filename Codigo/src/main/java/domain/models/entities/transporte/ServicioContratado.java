package domain.models.entities.transporte;

import javax.persistence.*;
import java.io.IOException;
@Entity
@DiscriminatorValue("Servicio Contratado")
public class ServicioContratado extends Transporte{

    @Column(name = "servicio")
    private String tipoServicio;

    public void registrarConsumoDeCombustible(){
        //TODO
    }
    public ServicioContratado() throws IOException {
        this.setDistanciaAPI();
        this.setNombre("Servicio Contratado");
    }


}
