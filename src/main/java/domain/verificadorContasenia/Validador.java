package domain.verificadorContasenia;

import domain.verificadorContasenia.Validacion;
import domain.verificadorContasenia.validarPorDiezMilPeores;
import domain.verificadorContasenia.validarPorLongitud;
import domain.verificadorContasenia.validarPorMayuscula;
import domain.verificadorContasenia.validarPorMinuscula;
import domain.verificadorContasenia.validarPorNumero;

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
