package domain.models.entities.transporte;

import domain.models.entities.consumo.FactorDeEmision;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.IOException;
@Entity
@DiscriminatorValue("Analogico")
public class TransporteAnalogico extends Transporte{

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_transporte")
    private TipoTransporteAnalogico transporte;

    public String getModelo(){return "-";}

    public TransporteAnalogico(TipoTransporteAnalogico transporte) throws IOException {
        this.transporte = transporte;
        this.setDistanciaAPI();
        this.setNombre("Transporte Analogico");
    }
    public TransporteAnalogico() throws IOException
    {
        setDistanciaAPI();
    }
    public void registrarConsumoDeCombustible(){
        //TODO
    }

}
