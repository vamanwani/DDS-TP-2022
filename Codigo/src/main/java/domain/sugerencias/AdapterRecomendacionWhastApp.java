package domain.sugerencias;

import domain.miembro.Miembro;
import domain.organizacion.Organizacion;

public interface AdapterRecomendacionWhastApp {
    public void notificarContactoPorWhatsApp(String link, Organizacion contacto);
    public int notificarMiembrosPorWhatsApp(String link, Organizacion organizacion);
}
