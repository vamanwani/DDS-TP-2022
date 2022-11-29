package distancia;

import domain.models.entities.sectorTerritorial.Localidad;
import domain.models.entities.sectorTerritorial.Provincia;
import domain.models.entities.transporte.TransporteAnalogico;
import domain.models.entities.transporte.TransportePublico;
import domain.services.adapters.GeoddsServceAdapter;
import domain.services.adapters.ServicioGeodds;
import domain.services.entities.Distancia;
import domain.models.entities.ubicacion.Ubicacion;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class DistanciaTest {
    public ServicioGeodds servicioGeodds;
    public GeoddsServceAdapter adapterMock;
    public Ubicacion primeraUbicacion;
    public Ubicacion segundaUbicacion;
    public Ubicacion terceraUbicacion;
    public Distancia distancia;

    @Before
    public void init() throws IOException {
        /*this.adapterMock = mock(GeoddsServceAdapter.class);
        this.servicioGeodds = ServicioGeodds.getInstance();
        //this.adapterPrueba = new ServicioGeoDdsRetrofitAdapter();
        this.servicioGeodds.setAdapter(this.adapterMock);
        this.primeraUbicacion = new Ubicacion("Humberto Primo", 785,"San telmo");
        primeraUbicacion.setLocalidades(servicioGeodds.localidades());
        this.segundaUbicacion = new Ubicacion("Constitucion", 1500, "Constitucion");
        segundaUbicacion.setLocalidades(servicioGeodds.localidades());
        this.terceraUbicacion = new Ubicacion("Mitre",750,"Ciudad de buenos aires");
*/
    }

//    @BeforeEach
//    public void init() throws IOException {
//        this.adapterMock = mock(ServicioGeoDdsRetrofitAdapter.class);
//        this.servicioGeodds = ServicioGeodds.getInstance();
//        //this.servicioGeodds.setAdapter(this.adapterMock);
//
//        this.servicioGeodds = new ServicioGeodds();
//        this.primeraUbicacion = new Ubicacion("Humberto Primo", 785,"San telmo");
//        primeraUbicacion.setLocalidades(servicioGeodds.localidades());
//        this.segundaUbicacion = new Ubicacion("Constitucion", 1500, "Constitucion");
//        segundaUbicacion.setLocalidades(servicioGeodds.localidades());
//        this.terceraUbicacion = new Ubicacion("Mitre",750,"Ciudad de buenos aires");
//    }


    @Test
    public void distanciaEntreDosUbicacionesTest() throws IOException{
        Distancia distanciaUbicaciones = this.distanciaMock();
        Distancia distanciaMockeada = mock(Distancia.class);

        when(distanciaMockeada.distancia()).thenReturn(distanciaUbicaciones.distancia());
        when(this.adapterMock.distancia(primeraUbicacion,segundaUbicacion)).thenReturn(distanciaMockeada);
        Assert.assertEquals(this.servicioGeodds.distancia(primeraUbicacion,segundaUbicacion).distancia(),677,5);


        //Assert.assertEquals(primeraUbicacion,null);
    }

    private Distancia distanciaMock(){
        Distancia distancia = new Distancia("22","KM");
        return distancia;
    }
    @Test
    public void testObtenerIdLocalidad() throws IOException{
        Assert.assertEquals(primeraUbicacion.obtenerIdLocalidad("San telmo"),2);
    }
    @Test
    public void testLocalidad(){
        Assert.assertEquals(Optional.ofNullable(primeraUbicacion.getLocalidades().get(1).getId()),5364);
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
        Assert.assertEquals(servicioGeodds.localidades().get(1).getNombre(),"BUENOS AIRES");
    }

    @Test //calculo de distancia funcional de ejemplo
    public void distanciaTransporte() throws IOException
    {
        Localidad unaLocalidad = new Localidad("Barracas", new Provincia("BUENOS AIRES"));
        unaLocalidad.setId(1);
        Ubicacion unaUbicacion = new Ubicacion("Patricios", 300, unaLocalidad);
        Ubicacion otraUbicacion = new Ubicacion("Patricios", 400, unaLocalidad);
        TransporteAnalogico transporte = new TransporteAnalogico();

        Assert.assertEquals(transporte.distancia(unaUbicacion,otraUbicacion),0.0,0.0);
    }


}
