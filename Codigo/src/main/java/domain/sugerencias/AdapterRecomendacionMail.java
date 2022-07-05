package domain.sugerencias;

import domain.miembro.Miembro;
import domain.organizacion.Organizacion;

public interface AdapterRecomendacionMail {
    public void notificarContactoPorMail(String link, Organizacion contacto);
}
