package domain.models.entities.transporte;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.IOException;
@Entity
@NoArgsConstructor
@DiscriminatorValue("Servicio Contratado")
public class ServicioContratado extends Transporte{

    @Column(name = "servicio")
    private String tipoServicio;

    public void registrarConsumoDeCombustible(){
        //TODO
    }
    public ServicioContratado(String tipoServicio) throws IOException {
        this.setDistanciaAPI();
        this.setNombre("Servicio Contratado");
        this.tipoServicio = tipoServicio;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }
}
