package calculadoraHC;

import static org.mockito.Mockito.mock;


public class HCTestMiembro {

//    public ServicioGeodds servicioGeodds;
//    public GeoddsServceAdapter adapterMock;
//    public Ubicacion primeraUbicacion;
//    public Ubicacion segundaUbicacion;
//    public Ubicacion terceraUbicacion;
//    public Distancia distancia;
//
//    @Before
//    public void init() throws IOException {
//        this.adapterMock = mock(GeoddsServceAdapter.class);
//        this.servicioGeodds = ServicioGeodds.getInstance();
//        //this.adapterPrueba = new ServicioGeoDdsRetrofitAdapter();
//        this.servicioGeodds.setAdapter(this.adapterMock);
//        this.primeraUbicacion = new Ubicacion("Humberto Primo", 785,"San telmo");
//        primeraUbicacion.setLocalidades(servicioGeodds.localidades());
//        this.segundaUbicacion = new Ubicacion("Constitucion", 1500, "Constitucion");
//        segundaUbicacion.setLocalidades(servicioGeodds.localidades());
//        this.terceraUbicacion = new Ubicacion("Mitre",750,"Ciudad de buenos aires");
//    }
//
//    @Test
//    public void distanciaEntreDosUbicacionesTest() throws IOException{
//        Distancia distanciaUbicaciones = this.distanciaMock();
//        Distancia distanciaMockeada = mock(Distancia.class);
//
//        when(distanciaMockeada.distancia()).thenReturn(distanciaUbicaciones.distancia());
//        when(this.adapterMock.distancia(primeraUbicacion,segundaUbicacion)).thenReturn(distanciaMockeada);
//        Assert.assertEquals(this.servicioGeodds.distancia(primeraUbicacion,segundaUbicacion).distancia(),677,5);
//
//
//        //Assert.assertEquals(primeraUbicacion,null);
//    }
//
//    private Distancia distanciaMock(){
//        Distancia distancia = new Distancia("22","KM");
//        return distancia;
//    }

//    private List<Trayecto> trayectos;
//    private Trayecto trayecto;
//    private Tramo tramo1;
//    private Tramo tramo2;
//    private Ubicacion ubicacion1;
//    private Ubicacion ubicacion2;
//
//    @Before
//    public void init() throws IOException {
//        ubicacion1 = new Ubicacion("Constitucion", 1500, "Constitucion");
//        ubicacion2 = new Ubicacion("Humberto Primo", 785,"San telmo");
//        tramo1 = new Tramo(new VehiculoParticular(TipoVehiculoParticular.auto, TipoCombustible.GNC),ubicacion1, ubicacion2);
//        tramo2 = new Tramo(new TransporteAnalogico(TipoTransporteAnalogico.bicicleta),ubicacion2,ubicacion1);
//        trayecto = new Trayecto(tramo1, tramo2);
//        trayectos = new ArrayList<Trayecto>();
//        trayectos.add(trayecto);
//    }
//
//    @Test
//    public void testCalculoHCMiembros() throws IOException {
//        Assert.assertEquals(new CalculdoraHCMiembro().calcularHC(trayectos), 250000,0);
//    }
//
//
//
//}
}