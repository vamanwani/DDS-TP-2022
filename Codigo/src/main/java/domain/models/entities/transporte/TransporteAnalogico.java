package domain.models.entities.transporte;

import domain.models.entities.consumo.FactorDeEmision;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.IOException;
@Entity
@NoArgsConstructor
@DiscriminatorValue("Analogico")
public class TransporteAnalogico extends Transporte{

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_transporte")
    private TipoTransporteAnalogico transporte;

    public TransporteAnalogico(TipoTransporteAnalogico transporte, FactorDeEmision fe) throws IOException {
        this.transporte = transporte;
        this.setDistanciaAPI();
        this.setNombre("Transporte Analogico");
        this.setFactorDeEmision(fe);
    }
    public void registrarConsumoDeCombustible(){
        //TODO
    }

}
