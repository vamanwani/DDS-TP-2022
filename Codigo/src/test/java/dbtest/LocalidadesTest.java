package dbtest;

import domain.models.entities.sectorTerritorial.LocalidadesJson;
import org.junit.Test;

import java.io.FileNotFoundException;

public class LocalidadesTest {

    @Test
    public void testDeLocalidades() throws FileNotFoundException {
        LocalidadesJson localidadesAPersistir= new LocalidadesJson("src/main/resources/CabaLocalidades.json");
        localidadesAPersistir.instanciarLocalidades();
        localidadesAPersistir.persistirLocalidades();
    }

    @Test
    public void testDeProvincias()
}
