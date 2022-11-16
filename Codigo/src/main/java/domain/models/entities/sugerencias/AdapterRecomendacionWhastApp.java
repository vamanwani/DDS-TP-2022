package domain.models.entities.sugerencias;

import domain.models.entities.organizacion.Organizacion;

public interface AdapterRecomendacionWhastApp {
    public void notificarContactoPorWhatsApp(String link, Organizacion contacto);
    public int notificarMiembrosPorWhatsApp(String link, Organizacion organizacion);
}
