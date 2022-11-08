package dbtest;

import domain.models.entities.transporte.TipoCombustible;
import domain.models.entities.transporte.TipoVehiculoParticular;
import domain.models.entities.transporte.VehiculoParticular;
import domain.services.dbManager.EntityManagerHelper;
import org.junit.Test;

public class MediosTransporteInit {

    @Test
    public void instanciarVehiculosParticulares(){
        VehiculoParticular autoGNC = new VehiculoParticular(TipoVehiculoParticular.AUTO, TipoCombustible.GNC);
        VehiculoParticular autoGasoil = new VehiculoParticular(TipoVehiculoParticular.AUTO, TipoCombustible.Gasoil);
        VehiculoParticular autoNafta = new VehiculoParticular(TipoVehiculoParticular.AUTO, TipoCombustible.Nafta);
        VehiculoParticular autoElectrico = new VehiculoParticular(TipoVehiculoParticular.AUTO, TipoCombustible.Electrico);

        VehiculoParticular motoGNC = new VehiculoParticular(TipoVehiculoParticular.MOTO, TipoCombustible.GNC);
        VehiculoParticular motoGasoil = new VehiculoParticular(TipoVehiculoParticular.MOTO, TipoCombustible.Gasoil);
        VehiculoParticular motoNafta = new VehiculoParticular(TipoVehiculoParticular.MOTO, TipoCombustible.Nafta);
        VehiculoParticular motoElectrico = new VehiculoParticular(TipoVehiculoParticular.MOTO, TipoCombustible.Electrico);

        VehiculoParticular camionetaGNC = new VehiculoParticular(TipoVehiculoParticular.CAMIONETA, TipoCombustible.GNC);
        VehiculoParticular camionetaGasoil = new VehiculoParticular(TipoVehiculoParticular.CAMIONETA, TipoCombustible.Gasoil);
        VehiculoParticular camionetaNafta = new VehiculoParticular(TipoVehiculoParticular.CAMIONETA, TipoCombustible.Nafta);
        VehiculoParticular camionetaElectrico = new VehiculoParticular(TipoVehiculoParticular.CAMIONETA, TipoCombustible.Electrico);


        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.persist(autoGNC);
        EntityManagerHelper.persist(autoGasoil);
        EntityManagerHelper.persist(autoNafta);
        EntityManagerHelper.persist(autoElectrico);
        EntityManagerHelper.persist(motoGNC);
        EntityManagerHelper.persist(motoGasoil);
        EntityManagerHelper.persist(motoNafta);
        EntityManagerHelper.persist(motoElectrico);
        EntityManagerHelper.persist(camionetaGNC);
        EntityManagerHelper.persist(camionetaGasoil);
        EntityManagerHelper.persist(camionetaNafta);
        EntityManagerHelper.persist(camionetaElectrico);
        EntityManagerHelper.commit();
    }
}
