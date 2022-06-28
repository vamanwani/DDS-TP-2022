package domain.sugerencias;

import domain.miembro.Miembro;

public class NotificarPorWhatsApp implements Comando{
    private AdapterRecomendacionWhastApp adapter;

    @Override
    public void notificar(String link, Miembro contacto) {
        adapter.notificarContactoPorWhatsApp(link, contacto);
    }
}
