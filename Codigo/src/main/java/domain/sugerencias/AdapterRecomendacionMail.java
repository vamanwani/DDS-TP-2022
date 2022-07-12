package domain.sugerencias;

import domain.miembro.Miembro;
import domain.organizacion.Organizacion;

public interface AdapterRecomendacionMail {
    public void notificarContactoPorMail(String link, Organizacion contacto);

    public static int mandarMail(String link, String direccion) {
        return 404;
    }

}
