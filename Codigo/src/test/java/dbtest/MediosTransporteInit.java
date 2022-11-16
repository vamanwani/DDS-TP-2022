package dbtest;

import domain.models.entities.sectorTerritorial.Localidad;
import domain.models.entities.transporte.*;
import domain.models.entities.ubicacion.Parada;
import domain.models.entities.ubicacion.Ubicacion;
import domain.services.dbManager.EntityManagerHelper;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;

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

    @Test
    public void instanciarTransportesAnalogicos() throws IOException {
        TransporteAnalogico pie = new TransporteAnalogico(TipoTransporteAnalogico.PIE);
        TransporteAnalogico bici = new TransporteAnalogico(TipoTransporteAnalogico.BICICLETA);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.persist(pie);
        EntityManagerHelper.persist(bici);
        EntityManagerHelper.commit();
    }

    @Test
    public void instanciarLineaD() throws IOException {
        Localidad localidadInicial = new Localidad("San Nicolas");
        Localidad localidadFinal = new Localidad("Belgrano");
        Ubicacion ubicacionInicial = new Ubicacion("San Martin", 1, localidadInicial);
        Ubicacion ubicacionFinal = new Ubicacion("Avenida Cabildo", 2800, localidadFinal);
        Parada paradaInicial = new Parada(ubicacionInicial);
        Parada paradaFinal = new Parada(ubicacionFinal);
        TransportePublico lineaD = new TransportePublico("D", TipoTransportePublico.lineaSubte, paradaInicial, paradaFinal);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.persist(localidadInicial);
        EntityManagerHelper.persist(localidadFinal);
        EntityManagerHelper.persist(ubicacionInicial);
        EntityManagerHelper.persist(ubicacionInicial);
//        EntityManagerHelper.commit();
        EntityManagerHelper.persist(paradaInicial);
        EntityManagerHelper.persist(paradaFinal);
        EntityManagerHelper.persist(lineaD);
        EntityManagerHelper.commit();

    }


}
