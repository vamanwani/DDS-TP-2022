package domain.verificadorContasenia;

import java.util.List;


public class Validador {

    public List<Validacion> getValidaciones() {
        return validaciones;
    }

    public void setValidaciones(List<Validacion> validaciones) {
        this.validaciones = validaciones;
    }

    private List<Validacion> validaciones;

}
