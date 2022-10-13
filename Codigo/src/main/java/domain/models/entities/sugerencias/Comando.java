package domain.models.entities.sugerencias;

import domain.models.entities.organizacion.Organizacion;

public interface Comando {
    public void notificar(String link, Organizacion contacto);
}
