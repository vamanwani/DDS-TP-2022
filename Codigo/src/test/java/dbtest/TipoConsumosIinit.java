package dbtest;

import domain.models.entities.consumo.*;
import domain.services.dbManager.EntityManagerHelper;
import org.apache.commons.io.TaggedIOException;
import org.junit.Test;

public class TipoConsumosIinit {

//    @Test
//    public void instanciasActividad(){
//        Actividad combustionFija = new Actividad();
//    }


    @Test
    public void initTipoConsumos(){
        TipoConsumo gasNatural = new TipoConsumo("GAS_NATURAL", Unidad.M3);
        TipoConsumo diesel = new TipoConsumo("DIESEL", Unidad.LT);
        TipoConsumo kerosene = new TipoConsumo("KEROSENE", Unidad.LT);
        TipoConsumo fuel = new TipoConsumo("FUEL_OIL", Unidad.LT);
        TipoConsumo nafta = new TipoConsumo("NAFTA", Unidad.LT);
        TipoConsumo carbon = new TipoConsumo("CARBON", Unidad.KG);
        TipoConsumo carbonLenia = new TipoConsumo("CARBON_DE_LEÑA", Unidad.KG);
        TipoConsumo lenia = new TipoConsumo("LEÑA", Unidad.KG);
        TipoConsumo gasoil = new TipoConsumo("GASOIL", Unidad.LTS);
        TipoConsumo GNC = new TipoConsumo("GNC", Unidad.LTS);
        TipoConsumo electricidad = new TipoConsumo("ELECTRICIDAD", Unidad.KWH);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.persist(gasNatural);
        EntityManagerHelper.persist(diesel);
        EntityManagerHelper.persist(kerosene);
        EntityManagerHelper.persist(fuel);
        EntityManagerHelper.persist(nafta);
        EntityManagerHelper.persist(carbon);
        EntityManagerHelper.persist(carbonLenia);
        EntityManagerHelper.persist(lenia);
        EntityManagerHelper.persist(gasoil);
        EntityManagerHelper.persist(GNC);
        EntityManagerHelper.persist(electricidad);
        EntityManagerHelper.commit();
    }

    @Test
    public void initCamionDeCarga(){
        TipoConsumo logistica = new TipoConsumo("LOGISITICA", Unidad.M3);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.persist(logistica);
        EntityManagerHelper.commit();
    }

//    @Test
//    public void instanciarTipoConsumo(){
////        FactorDeEmision feGasNatural = new FactorDeEmision(2.00);
//        String tipoConsumo = "PROBANDO_V2";
//        TipoConsumo gasNatural = (TipoConsumo) EntityManagerHelper.getEntityManager().
//                createQuery("from " + TipoConsumo.class.getName() + " where nombre = '" + tipoConsumo + "'").getSingleResult();
////        gasNatural.setFe(feGasNatural);
//        Consumo consumo = new OtroConsumo(null, null, gasNatural, 0.00);
//
//
//
//        EntityManagerHelper.beginTransaction();
////        EntityManagerHelper.persist(feGasNatural);
////        EntityManagerHelper.persist(gasNatural);
//        EntityManagerHelper.persist(consumo);
//        EntityManagerHelper.commit();
//    }
}
