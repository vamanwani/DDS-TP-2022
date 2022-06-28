package domain.sugerencias;

import domain.miembro.Miembro;

public class NotificarPorEmail implements Comando {
    private AdapterRecomendacionMail adapter;

    @Override
    public void notificar(String link, Miembro contacto){
        adapter.notificarContactoPorMail(link, contacto);
    }
}
