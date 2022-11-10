package importador;

import domain.models.entities.obtieneMediciones.ImportarDeExcel;
import domain.models.entities.organizacion.Organizacion;
import org.junit.Test;

import javax.persistence.Table;
import java.io.IOException;

public class ImportadorTest {

    @Test
    public void testImportacion() throws IOException {
        ImportarDeExcel importador = new ImportarDeExcel();
        importador.importar("prueba",new Organizacion());
    }
}
