package dbtest;

import domain.models.entities.sectorTerritorial.LocalidadesJson;
import domain.models.entities.sectorTerritorial.ProvinciaJson;
import org.junit.Test;

import java.io.FileNotFoundException;

public class LocalidadesTest {

    @Test
    public void testDeLocalidadesBsas() throws FileNotFoundException {
        LocalidadesJson localidadesAPersistir= new LocalidadesJson("src/main/resources/localidades/BsasLocalidades.json");
        localidadesAPersistir.instanciarMunicipios();
        localidadesAPersistir.persistirLocalidades();
    }

    @Test
    public void testDeLocalidadesCaba() throws FileNotFoundException {
        LocalidadesJson localidadesAPersistir= new LocalidadesJson("src/main/resources/localidades/CabaLocalidades.json");
        localidadesAPersistir.instanciarLocalidades();
        localidadesAPersistir.persistirLocalidades();
    }

    @Test
    public void testDeLocalidadesCatamarca() throws FileNotFoundException {
        LocalidadesJson localidadesAPersistir= new LocalidadesJson("src/main/resources/localidades/CatamarcaLocalidades.json");
        localidadesAPersistir.instanciarMunicipios();
        localidadesAPersistir.persistirLocalidades();
    }

    @Test
    public void testDeLocalidadesChaco() throws FileNotFoundException {
        LocalidadesJson localidadesAPersistir= new LocalidadesJson("src/main/resources/localidades/ChacoLocalidades.json");
        localidadesAPersistir.instanciarMunicipios();
        localidadesAPersistir.persistirLocalidades();
    }

    @Test
    public void testDeLocalidadesChubut() throws FileNotFoundException {
        LocalidadesJson localidadesAPersistir= new LocalidadesJson("src/main/resources/localidades/ChubutLocalidades.json");
        localidadesAPersistir.instanciarMunicipios();
        localidadesAPersistir.persistirLocalidades();
    }

    @Test
    public void testDeLocalidadesCordoba() throws FileNotFoundException {
        LocalidadesJson localidadesAPersistir= new LocalidadesJson("src/main/resources/localidades/CordobaLocalidades.json");
        localidadesAPersistir.instanciarMunicipios();
        localidadesAPersistir.persistirLocalidades();
    }

    @Test
    public void testDeLocalidadesCorrientes() throws FileNotFoundException {
        LocalidadesJson localidadesAPersistir= new LocalidadesJson("src/main/resources/localidades/CorrientesLocalidades.json");
        localidadesAPersistir.instanciarMunicipios();
        localidadesAPersistir.persistirLocalidades();
    }

    @Test
    public void testDeLocalidadesEntreRios() throws FileNotFoundException {
        LocalidadesJson localidadesAPersistir= new LocalidadesJson("src/main/resources/localidades/EntreRiosLocalidades.json");
        localidadesAPersistir.instanciarMunicipios();
        localidadesAPersistir.persistirLocalidades();
    }

    @Test
    public void testDeLocalidadesFormosa() throws FileNotFoundException {
        LocalidadesJson localidadesAPersistir= new LocalidadesJson("src/main/resources/localidades/FormosaLocalidades.json");
        localidadesAPersistir.instanciarMunicipios();
        localidadesAPersistir.persistirLocalidades();
    }

    @Test
    public void testDeLocalidadesJujuy() throws FileNotFoundException {
        LocalidadesJson localidadesAPersistir= new LocalidadesJson("src/main/resources/localidades/JujuyLocalidades.json");
        localidadesAPersistir.instanciarMunicipios();
        localidadesAPersistir.persistirLocalidades();
    }

    @Test
    public void testDeLocalidadesLaPampa() throws FileNotFoundException {
        LocalidadesJson localidadesAPersistir= new LocalidadesJson("src/main/resources/localidades/LaPampaLocalidades.json");
        localidadesAPersistir.instanciarMunicipios();
        localidadesAPersistir.persistirLocalidades();
    }

    @Test
    public void testDeLocalidadesLaRioja() throws FileNotFoundException {
        LocalidadesJson localidadesAPersistir= new LocalidadesJson("src/main/resources/localidades/LaRiojaLocalidades.json");
        localidadesAPersistir.instanciarMunicipios();
        localidadesAPersistir.persistirLocalidades();
    }

    @Test
    public void testDeLocalidadesMendoza() throws FileNotFoundException {
        LocalidadesJson localidadesAPersistir= new LocalidadesJson("src/main/resources/localidades/MendozaLocalidades.json");
        localidadesAPersistir.instanciarMunicipios();
        localidadesAPersistir.persistirLocalidades();
    }

    @Test
    public void testDeLocalidadesMisiones() throws FileNotFoundException {
        LocalidadesJson localidadesAPersistir= new LocalidadesJson("src/main/resources/localidades/MisionesLocalidades.json");
        localidadesAPersistir.instanciarMunicipios();
        localidadesAPersistir.persistirLocalidades();
    }

    @Test
    public void testDeLocalidadesNeuquen() throws FileNotFoundException {
        LocalidadesJson localidadesAPersistir= new LocalidadesJson("src/main/resources/localidades/NeuquenLocalidades.json");
        localidadesAPersistir.instanciarMunicipios();
        localidadesAPersistir.persistirLocalidades();
    }

    @Test
    public void testDeLocalidadesRioNegro() throws FileNotFoundException {
        LocalidadesJson localidadesAPersistir= new LocalidadesJson("src/main/resources/localidades/RioNegroLocalidades.json");
        localidadesAPersistir.instanciarMunicipios();
        localidadesAPersistir.persistirLocalidades();
    }

    @Test
    public void testDeLocalidadesSalta() throws FileNotFoundException {
        LocalidadesJson localidadesAPersistir= new LocalidadesJson("src/main/resources/localidades/SaltaLocalidades.json");
        localidadesAPersistir.instanciarMunicipios();
        localidadesAPersistir.persistirLocalidades();
    }

    @Test
    public void testDeLocalidadesSanJuan() throws FileNotFoundException {
        LocalidadesJson localidadesAPersistir= new LocalidadesJson("src/main/resources/localidades/SanJuanLocalidades.json");
        localidadesAPersistir.instanciarMunicipios();
        localidadesAPersistir.persistirLocalidades();
    }

    @Test
    public void testDeLocalidadesSanLuis() throws FileNotFoundException {
        LocalidadesJson localidadesAPersistir= new LocalidadesJson("src/main/resources/localidades/SanLuisLocalidades.json");
        localidadesAPersistir.instanciarMunicipios();
        localidadesAPersistir.persistirLocalidades();
    }

    @Test
    public void testDeLocalidadesSantaCruz() throws FileNotFoundException {
        LocalidadesJson localidadesAPersistir= new LocalidadesJson("src/main/resources/localidades/SantaCruzLocalidades.json");
        localidadesAPersistir.instanciarMunicipios();
        localidadesAPersistir.persistirLocalidades();
    }

    @Test
    public void testDeLocalidadesSantaFe() throws FileNotFoundException {
        LocalidadesJson localidadesAPersistir= new LocalidadesJson("src/main/resources/localidades/SantaFeLocalidades.json");
        localidadesAPersistir.instanciarMunicipios();
        localidadesAPersistir.persistirLocalidades();
    }

    @Test
    public void testDeLocalidadesSantiagoDelEstero() throws FileNotFoundException {
        LocalidadesJson localidadesAPersistir= new LocalidadesJson("src/main/resources/localidades/SantiagoDelEsteroLocalidades.json");
        localidadesAPersistir.instanciarMunicipios();
        localidadesAPersistir.persistirLocalidades();
    }

    @Test
    public void testDeLocalidadesTierraDelFuego() throws FileNotFoundException {
        LocalidadesJson localidadesAPersistir= new LocalidadesJson("src/main/resources/localidades/TierraDelFuegoLocalidades.json");
        localidadesAPersistir.instanciarMunicipios();
        localidadesAPersistir.persistirLocalidades();
    }

    @Test
    public void testDeLocalidadesTucuman() throws FileNotFoundException {
        LocalidadesJson localidadesAPersistir= new LocalidadesJson("src/main/resources/localidades/TucumanLocalidades.json");
        localidadesAPersistir.instanciarMunicipios();
        localidadesAPersistir.persistirLocalidades();
    }

    @Test
    public void testDeProvincias() throws FileNotFoundException{
        ProvinciaJson provinciasAPersistir = new ProvinciaJson("src/main/resources/ProvinciasArgentinas.json");
        provinciasAPersistir.instanciarProvincias();
        provinciasAPersistir.persistirProvincias();

    }
}
