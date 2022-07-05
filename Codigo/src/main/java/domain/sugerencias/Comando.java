package domain.sugerencias;

import domain.miembro.Miembro;
import domain.organizacion.Organizacion;

public interface Comando {
    public void notificar(String link, Organizacion contacto);
}
