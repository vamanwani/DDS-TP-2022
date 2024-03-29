package domain.models.entities.verificadorContasenia;

import java.io.FileNotFoundException;

public abstract class Validacion {

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public boolean habilitado = true; // no es necesario

    public abstract boolean claveValida(String clave) throws FileNotFoundException;

}