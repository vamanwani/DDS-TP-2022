package domain.sugerencias;

import domain.miembro.Miembro;
import domain.organizacion.Organizacion;

public class NotificadorRecomendacionesWspAPI implements AdapterRecomendacionWhastApp{

    @Override
    public void notificarContactoPorWhatsApp(String link, Organizacion contacto) {

        contacto.listarMiembros().forEach(unContacto -> {
            //TODO hacerFuncionConAPI(unContacto.nro)
        });
        //TODO Wassenger
    }
}
