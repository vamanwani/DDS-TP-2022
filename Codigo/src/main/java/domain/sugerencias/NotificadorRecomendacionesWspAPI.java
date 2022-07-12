package domain.sugerencias;

import domain.miembro.Miembro;
import domain.organizacion.Organizacion;
import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.net.URI;
import java.math.BigDecimal;

public class NotificadorRecomendacionesWspAPI implements AdapterRecomendacionWhastApp{

    @Override
    public void notificarContactoPorWhatsApp(String link, Organizacion contacto) {

        contacto.listarMiembros().forEach(unContacto -> {
            enviarWhatsapp("un link", unContacto.getUsuario().getTelefono());
        });

    }

    @Override
    public int notificarMiembrosPorWhatsApp(String link, Organizacion organizacion) {
        return 0;
    }

    public static final String ACCOUNT_SID = "ACf298ad92277c008e81907979f0ba6e33";
    public static final String AUTH_TOKEN = "b86616ec0ebadd641008037f3dd149bb";

    public static void enviarWhatsapp(String link,String telefono) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber("whatsapp:" + telefono),
                        new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                        link)//join printed-train
                .create();

        System.out.println(message.getSid());
    }
}
