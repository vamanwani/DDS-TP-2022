package domain.models.entities.transporte;

import javax.persistence.*;
import java.io.IOException;
@Entity
@DiscriminatorValue("Analogico")
public class TransporteAnalogico extends Transporte{

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_transporte")
    private TipoTransporteAnalogico transporte;

    public TransporteAnalogico(TipoTransporteAnalogico transporte) throws IOException {
        this.transporte = transporte;
        this.setDistanciaAPI();
        this.setNombre("Transporte Analogico");
    }
    public void registrarConsumoDeCombustible(){
        //TODO
    }

}
