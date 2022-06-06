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
    public void init() throws IOException {
        this.servicioGeodds = new ServicioGeodds();
        this.primeraUbicacion = new Ubicacion("Humberto Primo", 785,"San telmo");
        primeraUbicacion.setLocalidades(servicioGeodds.localidades());
        this.segundaUbicacion = new Ubicacion("Constitucion", 1500, "Constitucion");
        segundaUbicacion.setLocalidades(servicioGeodds.localidades());
        this.terceraUbicacion = new Ubicacion("Mitre",750,"Ciudad de buenos aires");

    }

    @Test
    public void distanciaEntreDosUbicacionesMensaje() throws IOException {
        //Distancia unaDistancia = servicioGeodds.distancia(primeraUbicacion,segundaUbicacion);
        Assert.assertEquals(servicioGeodds.distanciaRespuesta(primeraUbicacion,segundaUbicacion),200);
    }
    @Test
    public void distanciaEntreDosUbicacionesTest() throws IOException{
        Assert.assertEquals(servicioGeodds.distancia(primeraUbicacion,segundaUbicacion).distancia(),0,5);
    }
    @Test
    public void testObtenerIdLocalidad() throws IOException{
        Assert.assertEquals(primeraUbicacion.obtenerIdLocalidad("San telmo"),2);
    }
    @Test
    public void testLocalidad(){
        Assert.assertEquals(primeraUbicacion.getLocalidades().get(1).getId(),5364);
    }

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
