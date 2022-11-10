package domain.models.entities.obtieneMediciones;

import java.util.ArrayList;
import java.util.List;

public class FilaConsumo {
    private List<String> datosString;
    public FilaConsumo(){
        this.datosString = new ArrayList<>();
    }
    public void setDatosString(List<String> listaAAgregar){
        this.datosString.addAll(listaAAgregar);
    }

    public List<String> getDatosString() {
        return datosString;
    }
}
