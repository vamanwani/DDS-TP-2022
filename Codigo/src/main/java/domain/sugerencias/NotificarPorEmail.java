package domain.sugerencias;

import domain.miembro.Miembro;
import domain.organizacion.Organizacion;

public class NotificarPorEmail implements Comando {
    private AdapterRecomendacionMail adapter;

    @Override
    public void notificar(String link, Organizacion contacto){
        adapter.notificarContactoPorMail(link, contacto);
    }
}
