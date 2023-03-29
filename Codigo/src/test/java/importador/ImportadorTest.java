package importador;

import domain.models.entities.consumo.Consumo;
import domain.models.entities.consumo.ConsumoLogistica;
import domain.models.entities.obtieneMediciones.ImportarDeExcel;
import domain.models.entities.organizacion.Organizacion;
import domain.services.dbManager.EntityManagerHelper;
import org.junit.Test;

import javax.persistence.Table;
import java.io.IOException;

public class ImportadorTest {

//    @Test
//    public void testImportacion() throws IOException {
//        ImportarDeExcel importador = new ImportarDeExcel();
//        Organizacion unaOrganizacion = new Organizacion();
//        /*Consumo consumoLogistica = new ConsumoLogistica(null,null,null,11,11,"prueba","prueba");
//        unaOrganizacion.agregarConsumo(consumoLogistica);
//        EntityManagerHelper.beginTransaction();
//        EntityManagerHelper.persist(unaOrganizacion);
//        EntityManagerHelper.persist(consumoLogistica);
//        EntityManagerHelper.commit();*/
//        importador.importar("prueba",unaOrganizacion);
//    }
}
