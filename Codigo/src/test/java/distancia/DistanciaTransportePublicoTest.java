package distancia;

import domain.transporte.TipoTransportePublico;
import domain.transporte.TransportePublico;
import domain.ubicacion.Parada;
import domain.ubicacion.Ubicacion;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.imageio.IIOException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DistanciaTransportePublicoTest {
        public TransportePublico subte;
        public List<Parada> paradas;
        public Ubicacion ubicacionPrimeraParada;
        public Ubicacion ubicacionSegundaParada;
        public Ubicacion ubicacionTerceraParada;
        public Ubicacion ubicacionCuartaParada;
        public Parada primeraParada;
        public Parada segundaParada;
        public Parada terceraParada;
        public Parada cuartaParada;
        @Before
        public void init() throws IOException {
                /*ubicacionPrimeraParada = new Ubicacion("Leandro Alem", 400, "Ciudad de buenos Aires");
                ubicacionSegundaParada = new Ubicacion("Florida", 500, "Ciudad de Buenos Aires");
                ubicacionTerceraParada = new Ubicacion("Carlos pellegrini",400,"Ciudad de buenosAires");
                ubicacionCuartaParada = new Ubicacion("Av corrientes",1400,"Ciudad De Buenos Aires");
                primeraParada = new Parada(ubicacionPrimeraParada,null,null);
                segundaParada = new Parada(ubicacionSegundaParada,primeraParada,null);
                terceraParada = new Parada(ubicacionTerceraParada,segundaParada,null);
                cuartaParada = new Parada(ubicacionCuartaParada,terceraParada,null);
                paradas = new ArrayList();
                paradas.add(primeraParada);
                paradas.add(segundaParada);
                paradas.add(terceraParada);
                paradas.add(cuartaParada);

                subte = new TransportePublico("SubteB",TipoTransportePublico.lineaSubte,paradas.get(0),paradas.get(paradas.size()-1));

                subte.setParadas(paradas);

*/
                //this.subte = new TransportePublico("Subte B", //TipoTransportePublico.lineaSubte,);
        }

        @Test
        public void testEnUnSentidoTransporte() throws IOException{
                Assert.assertEquals(subte.sentido(ubicacionPrimeraParada,ubicacionTerceraParada),0,0);
        }

}
