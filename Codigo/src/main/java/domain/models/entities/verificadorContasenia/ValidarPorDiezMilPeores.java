package domain.models.entities.verificadorContasenia;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ValidarPorDiezMilPeores extends Validacion{
    private List<String> listaValidadas;

    public ValidarPorDiezMilPeores(List<String> listaValidadas) {
        this.listaValidadas = new ArrayList<>();
        this.listaValidadas = listaValidadas;
    }

    @Override
    public boolean claveValida( String clave) throws FileNotFoundException {
        return !listaValidadas.contains(clave+"\r");
    }
}
