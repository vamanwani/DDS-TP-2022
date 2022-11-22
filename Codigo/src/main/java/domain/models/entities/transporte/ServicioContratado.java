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

    public void registrarConsumoDeCombustible(){
        //TODO
    }
    public ServicioContratado(String tipoServicio, FactorDeEmision fe) throws IOException {
        this.setDistanciaAPI();
        this.setNombre("Servicio Contratado");
        this.tipoServicio = tipoServicio;
        this.setFactorDeEmision(fe);
    }

    public String getTipoServicio() {
        return tipoServicio;
    }
}
