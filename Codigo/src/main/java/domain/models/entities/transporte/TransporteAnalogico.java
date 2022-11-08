package domain.models.entities.transporte;

import javax.persistence.*;
import java.io.IOException;

@Entity
@Table(name = "transporteanalogico")
public class TransporteAnalogico extends Transporte{
    @Id
    @GeneratedValue
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_transporte")
    private TipoTransporteAnalogico transporte;
    private String nombre = "Transporte Analogico";

    public TransporteAnalogico(TipoTransporteAnalogico transporte) throws IOException {
        this.transporte = transporte;
        this.setDistanciaAPI();
    }
    public void registrarConsumoDeCombustible(){
        //TODO
    }
}
