package domain.models.entities.obtieneMediciones;

import java.util.ArrayList;
import java.util.List;

public class FilaConsumo {
    private List<String> datosString;
    public void FilaConsumo(){
        this.datosString = new ArrayList<>();
    }
    public void setDatosString(List<String> listaAAgregar){
        this.datosString = listaAAgregar;
    }

    public List<String> getDatosString() {
        return datosString;
    }
}
