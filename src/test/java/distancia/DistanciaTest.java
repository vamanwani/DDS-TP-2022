package distancia;

import domain.geodds.ServicioGeodds;
import domain.services.entities.Distancia;
import domain.ubicacion.Ubicacion;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class DistanciaTest {
    public ServicioGeodds servicioGeodds;
    public Ubicacion primeraUbicacion;
    public Ubicacion segundaUbicacion;
    public Ubicacion terceraUbicacion;
    public Distancia distancia;
    @Before
    public void init(){
        this.servicioGeodds = new ServicioGeodds();
        this.primeraUbicacion = new Ubicacion("Humberto Primo", 785,"CABA");
        this.segundaUbicacion = new Ubicacion("Constitucion", 1500, "CABA");
        this.terceraUbicacion = new Ubicacion("Mitre",750,"CABA");

    }


    /*public void distanciaEntreDosUbicaciones() throws IOException {
        Distancia unaDistancia = servicioGeodds.distancia(primeraUbicacion,segundaUbicacion);
        Assert.assertTrue(unaDistancia.distancia() == 1);
    }
     */
    @Test
    public void localidadesTestRespuesta() throws IOException {
        Assert.assertEquals(servicioGeodds.localidadesRespuesta(),200);
    }

    @Test
    public void datosLocalidadTestContenido() throws IOException{
        Assert.assertFalse(servicioGeodds.localidades().isEmpty());
    }
    @Test
    public void contenidoElementoDeListaTest() throws IOException{
        Assert.assertEquals(servicioGeodds.localidades().get(1).nombre,"BUENOS AIRES");
    }
}
