package domain.models.entities.transporte;

import domain.models.entities.consumo.FactorDeEmision;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.IOException;
@Entity
@NoArgsConstructor
@DiscriminatorValue("Servicio Contratado")
public class ServicioContratado extends Transporte{

    @Column(name = "servicio")
    private String tipoServicio;

    public String getModelo(){return "-";}

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
