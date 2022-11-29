package dbtest;

import domain.models.entities.consumo.FactorDeEmision;
import domain.models.entities.sectorTerritorial.Localidad;
import domain.models.entities.sectorTerritorial.Provincia;
import domain.models.entities.transporte.*;
import domain.models.entities.ubicacion.Parada;
import domain.models.entities.ubicacion.Ubicacion;
import domain.services.dbManager.EntityManagerHelper;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;

    public class MediosTransporteInit {

    @Test
    public void instanciarVehiculosParticulares() throws IOException {
        FactorDeEmision fe = new FactorDeEmision(2.00);
        VehiculoParticular autoGNC = new VehiculoParticular(TipoVehiculoParticular.AUTO, TipoCombustible.GNC, fe);
        VehiculoParticular autoGasoil = new VehiculoParticular(TipoVehiculoParticular.AUTO, TipoCombustible.Gasoil, fe);
        VehiculoParticular autoNafta = new VehiculoParticular(TipoVehiculoParticular.AUTO, TipoCombustible.Nafta, fe);
        VehiculoParticular autoElectrico = new VehiculoParticular(TipoVehiculoParticular.AUTO, TipoCombustible.Electrico, fe);

        VehiculoParticular motoGNC = new VehiculoParticular(TipoVehiculoParticular.MOTO, TipoCombustible.GNC, fe);
        VehiculoParticular motoGasoil = new VehiculoParticular(TipoVehiculoParticular.MOTO, TipoCombustible.Gasoil, fe);
        VehiculoParticular motoNafta = new VehiculoParticular(TipoVehiculoParticular.MOTO, TipoCombustible.Nafta, fe);
        VehiculoParticular motoElectrico = new VehiculoParticular(TipoVehiculoParticular.MOTO, TipoCombustible.Electrico, fe);

        VehiculoParticular camionetaGNC = new VehiculoParticular(TipoVehiculoParticular.CAMIONETA, TipoCombustible.GNC, fe);
        VehiculoParticular camionetaGasoil = new VehiculoParticular(TipoVehiculoParticular.CAMIONETA, TipoCombustible.Gasoil, fe);
        VehiculoParticular camionetaNafta = new VehiculoParticular(TipoVehiculoParticular.CAMIONETA, TipoCombustible.Nafta, fe);
        VehiculoParticular camionetaElectrico = new VehiculoParticular(TipoVehiculoParticular.CAMIONETA, TipoCombustible.Electrico, fe);


        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.persist(fe);
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
        FactorDeEmision fe = new FactorDeEmision(0.00);
        TransporteAnalogico pie = new TransporteAnalogico(TipoTransporteAnalogico.PIE, fe);
        TransporteAnalogico bici = new TransporteAnalogico(TipoTransporteAnalogico.BICICLETA, fe);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.persist(fe);
        EntityManagerHelper.persist(pie);
        EntityManagerHelper.persist(bici);
        EntityManagerHelper.commit();
    }

    @Test
    public void instanciarLineaD() throws IOException {
        FactorDeEmision fe = new FactorDeEmision(1.00);
        Provincia provincia = new Provincia("Buenos Aires");
        Localidad localidadInicial = new Localidad("San Nicolas", provincia);
        Localidad localidadFinal = new Localidad("Belgrano", provincia);
        Ubicacion ubicacionInicial = new Ubicacion("San Martin", 1, localidadInicial);
        Ubicacion ubicacionFinal = new Ubicacion("Avenida Cabildo", 2800, localidadFinal);
        Parada paradaInicial = new Parada(ubicacionInicial);
        Parada paradaFinal = new Parada(ubicacionFinal);
        TransportePublico lineaD = new TransportePublico("D", TipoTransportePublico.lineaSubte, paradaInicial, paradaFinal, fe);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.persist(fe);
        EntityManagerHelper.persist(provincia);
        EntityManagerHelper.persist(localidadInicial);
        EntityManagerHelper.persist(localidadFinal);
        EntityManagerHelper.persist(ubicacionInicial);
        EntityManagerHelper.persist(ubicacionInicial);
        EntityManagerHelper.persist(paradaInicial);
        EntityManagerHelper.persist(paradaFinal);
        EntityManagerHelper.persist(lineaD);
        EntityManagerHelper.commit();
    }



}
