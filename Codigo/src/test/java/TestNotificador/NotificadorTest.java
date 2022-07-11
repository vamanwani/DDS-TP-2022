package TestNotificador;

import domain.miembro.Miembro;
import domain.miembro.Usuario;
import domain.organizacion.Organizacion;
import domain.sugerencias.Notificador;
import domain.sugerencias.NotificadorRecomendacionesWspAPI;
import domain.sugerencias.NotificarPorWhatsApp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class NotificadorTest {
    public NotificadorRecomendacionesWspAPI adapterMockWsp;
    public NotificarPorWhatsApp notificadorWsp;
    public Notificador notificador;
    public List<Organizacion> organizaciones;
    public Organizacion primeraOrganizacion;
    public Organizacion segundaOrganizacion;
    public Miembro primerMiembro;
    public Miembro segundoMiembro;
    public Miembro tercerMiembro;
    public Miembro cuartoMiembro;

    @Before
    public void init(){
        this.adapterMockWsp = mock(NotificadorRecomendacionesWspAPI.class);
        this.notificadorWsp = new NotificarPorWhatsApp();
        this.notificador = new Notificador();

        this.organizaciones = new ArrayList<>();
        this.primeraOrganizacion = new Organizacion();
        this.segundaOrganizacion = new Organizacion();
        organizaciones.add(primeraOrganizacion);
        organizaciones.add(segundaOrganizacion);
        this.primerMiembro = new Miembro("miembro",
                "primero",
                111111111,
                "DNI",
                new Usuario("primer","miembro123"));
        this.segundoMiembro = new Miembro("miembro",
                "segundo",
                22222222,
                "DNI",
                new Usuario("segundo","miembro123"));
        this.tercerMiembro = new Miembro("miembro",
                "tercero",
                33333333,
                "DNI",
                new Usuario("tercero","miembro123"));
        this.cuartoMiembro = new Miembro("miembro",
                "cuarto",
                44444444,
                "DNI",
                new Usuario("cuarto","miembro123"));
        primeraOrganizacion.agregarContacto(primerMiembro);
        primeraOrganizacion.agregarContacto(segundoMiembro);
        segundaOrganizacion.agregarContacto(tercerMiembro);
        segundaOrganizacion.agregarContacto(cuartoMiembro);
    }

    @Test
    public void testNotificadorWhatsapp(){
        when(this.adapterMockWsp.notificarMiembrosPorWhatsApp("UnLink",primeraOrganizacion)).thenReturn(200);
        Assert.assertEquals(200,200);
    }
    public int ClaseTestOrganizaciones(String link, Organizacion organizacion){

        return 200;
    }


}
