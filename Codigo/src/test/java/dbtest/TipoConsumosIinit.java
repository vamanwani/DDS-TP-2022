package dbtest;

import domain.models.entities.consumo.Actividad;
import domain.models.entities.consumo.FactorDeEmision;
import domain.models.entities.consumo.TipoConsumo;
import domain.models.entities.consumo.Unidad;
import domain.services.dbManager.EntityManagerHelper;
import org.junit.Test;

public class TipoConsumosIinit {

    @Test
    public void instanciasActividad(){
        Actividad combustionFija = new Actividad();
    }


    @Test
    public void instanciarTipoConsumo(){
        FactorDeEmision feGasNatural = new FactorDeEmision(2.00);
        TipoConsumo gasNatural = new TipoConsumo("GAS_NATURAL", Unidad.M3);
        gasNatural.setFe(feGasNatural);



        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.persist(feGasNatural);
        EntityManagerHelper.persist(gasNatural);
        EntityManagerHelper.commit();
    }
}
