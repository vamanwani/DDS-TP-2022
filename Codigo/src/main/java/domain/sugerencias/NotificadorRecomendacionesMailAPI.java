package domain.sugerencias;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.*;
import domain.organizacion.Organizacion;

import java.io.IOException;

public class NotificadorRecomendacionesMailAPI implements  AdapterRecomendacionMail {
    @Override
    public void notificarContactoPorMail(String link, Organizacion contacto) {

        contacto.listarMiembros().forEach(unContacto -> {
            try {
                mandarMail("unLink", unContacto.getUsuario().getMail());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void mandarMail(String link, String direccion) throws IOException {
        Email from = new Email("adtolaba@frba.utn.edu.ar");
        String subject = "Sending with SendGrid is Fun";
        Email to = new Email(direccion);
        Content content = new Content("text/plain", link);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid("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");
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
            //return response.getStatusCode();
        } catch (IOException ex) {
            throw ex;
        }
    }

    public static int mandarMailCodigoHttp(String link, String direccion) throws IOException {
        Email from = new Email("adtolaba@frba.utn.edu.ar");
        String subject = "Sending with SendGrid is Fun";
        Email to = new Email(direccion);
        Content content = new Content("text/plain", link);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid("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");
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
            return response.getStatusCode();
        } catch (IOException ex) {
            throw ex;
        }
    }
}
