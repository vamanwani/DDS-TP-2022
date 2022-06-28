package domain.sugerencias;

import domain.miembro.Miembro;

public interface AdapterRecomendacionWhastApp {
    public void notificarContactoPorWhatsApp(String link, Miembro contacto);
}
