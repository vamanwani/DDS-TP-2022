package TestNotificador;

import domain.models.entities.miembro.Miembro;
import domain.models.entities.miembro.Usuario;
import domain.models.entities.organizacion.Organizacion;
import domain.models.entities.organizacion.Sector;
import domain.models.entities.sugerencias.Notificador;
import domain.models.entities.sugerencias.NotificadorRecomendacionesWspAPI;
import domain.models.entities.sugerencias.NotificarPorWhatsApp;
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
    public Sector primerSector;
    public Sector segundoSector;
    public Sector tercerSector;


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
                "111111111",
                "DNI",
                new Usuario("primer","miembro123"));
        this.segundoMiembro = new Miembro("miembro",
                "segundo",
                "22222222",
                "DNI",
                new Usuario("segundo","miembro123"));
        this.tercerMiembro = new Miembro("miembro",
                "tercero",
                "33333333",
                "DNI",
                new Usuario("tercero","miembro123"));
        this.cuartoMiembro = new Miembro("miembro",
                "cuarto",
                "44444444",
                "DNI",
                new Usuario("cuarto","miembro123"));
        primeraOrganizacion.agregarContacto(primerMiembro);
        primeraOrganizacion.agregarContacto(segundoMiembro);
        segundaOrganizacion.agregarContacto(tercerMiembro);
        segundaOrganizacion.agregarContacto(cuartoMiembro);
        primerSector = new Sector();
        segundoSector = new Sector();
        primeraOrganizacion.agregarSectores(primerSector);
        primeraOrganizacion.agregarSectores(segundoSector);

        primerSector.agregarMiembro(primerMiembro);
        primerSector.agregarMiembro(segundoMiembro);

        notificador.agregarOrganizacion(primeraOrganizacion);
        notificador.agregarOrganizacion(segundaOrganizacion);
    }

    @Test
    public void testRecorridoMiembros(){
        //when(this.adapterMockWsp.notificarMiembrosPorWhatsApp("UnLink",primeraOrganizacion)).thenReturn(200);
        primeraOrganizacion.notificarContactos("Prueba de Link");
        //Miembro[] contactos= primeraOrganizacion.listarMiembros().toArray(new Miembro[primeraOrganizacion.listarMiembros().size()]);
        Assert.assertEquals(primeraOrganizacion.listarMiembros().iterator().next().getLinkRecomendacion(),"Prueba de Link");
        Assert.assertEquals(primeraOrganizacion.listarMiembros().iterator().next().getLinkRecomendacion(),"Prueba de Link");
        Assert.assertEquals(primerMiembro.getLinkRecomendacion(),"Prueba de Link");
        Assert.assertNotEquals(tercerMiembro.getLinkRecomendacion(),"Prueba de Link");
    }

    @Test
    public void testRecorridoTodasLasOrganizaciones(){
        notificador.notificarOrganizaciones("Prueba");
        Assert.assertEquals(primerMiembro.getLinkRecomendacion(),"Prueba");
        Assert.assertNotEquals(primerMiembro.getLinkRecomendacion(),"Asd");
        Assert.assertEquals(segundoMiembro.getLinkRecomendacion(),"Prueba");
        Assert.assertEquals(tercerMiembro.getLinkRecomendacion(),"Prueba");
    }

}
