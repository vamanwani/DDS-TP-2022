package domain.sugerencias;

import domain.miembro.Miembro;
import domain.organizacion.Organizacion;

public class NotificarPorWhatsApp implements Comando{
    private AdapterRecomendacionWhastApp adapter;

    @Override
    public void notificar(String link, Organizacion contacto) {
        //contacto.listarMiembros().forEach(unContacto -> adapter.notificarContactoPorWhatsApp(link,unContacto));
        adapter.notificarContactoPorWhatsApp(link, contacto);
    }
}
