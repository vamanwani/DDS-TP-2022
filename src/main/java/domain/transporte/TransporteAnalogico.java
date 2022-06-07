package domain.transporte;

import java.io.IOException;

public class TransporteAnalogico extends Transporte{
    private TipoTransporteAnalogico transporte;

    public TransporteAnalogico(TipoTransporteAnalogico transporte) throws IOException {
        this.transporte = transporte;
        this.setDistanciaAPI();
    }
    public void registrarConsumoDeCombustible(){
        //TODO
    }
}
