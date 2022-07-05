package domain.sugerencias;

import domain.organizacion.Organizacion;

public class NotificadorRecomendacionesMailAPI implements  AdapterRecomendacionMail{
    @Override
    public void notificarContactoPorMail(String link, Organizacion contacto) {

        contacto.listarMiembros().forEach(unContacto -> {
            //TODO hacerFuncionConAPI(unContacto.nro)
        });
    }
}
