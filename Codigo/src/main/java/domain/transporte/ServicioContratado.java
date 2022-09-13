package domain.transporte;

import javax.persistence.*;
import java.io.IOException;

@Entity
@Table(name = "servicio_contratado")
public class ServicioContratado extends Transporte{
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "servicio")
    private String tipoServicio;

    public void registrarConsumoDeCombustible(){
        //TODO
    }
    public ServicioContratado() throws IOException {
        this.setDistanciaAPI();
    }

}
