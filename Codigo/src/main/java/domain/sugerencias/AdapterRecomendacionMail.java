package domain.sugerencias;

import domain.miembro.Miembro;

public interface AdapterRecomendacionMail {
    public void notificarContactoPorMail(String link, Miembro contacto);
}
