package domain.sugerencias;

import domain.miembro.Miembro;

public interface Comando {
    public void notificar(String link, Miembro contacto);
}
