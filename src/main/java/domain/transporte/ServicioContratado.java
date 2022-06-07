package domain.transporte;

import java.io.IOException;

public class ServicioContratado extends Transporte{
    private String tipoServicio;

    public void registrarConsumoDeCombustible(){
        //TODO
    }
    public ServicioContratado() throws IOException {
        this.setDistanciaAPI();
    }

}
