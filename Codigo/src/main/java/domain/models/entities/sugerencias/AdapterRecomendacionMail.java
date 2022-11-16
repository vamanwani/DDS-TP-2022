package domain.models.entities.sugerencias;

import domain.models.entities.organizacion.Organizacion;

public interface AdapterRecomendacionMail {
    public void notificarContactoPorMail(String link, Organizacion contacto);

    public static int mandarMail(String link, String direccion) {
        return 404;
    }

}
