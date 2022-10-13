package domain.models.entities.sugerencias;

import domain.models.entities.organizacion.Organizacion;

public class NotificarPorEmail implements Comando {
    private AdapterRecomendacionMail adapter;

    @Override
    public void notificar(String link, Organizacion contacto){
        adapter.notificarContactoPorMail(link, contacto);
    }
}
