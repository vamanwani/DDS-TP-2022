package dbtest;

import domain.models.entities.consumo.FactorDeEmision;
import domain.models.entities.sectorTerritorial.Localidad;
import domain.models.entities.sectorTerritorial.Provincia;
import domain.models.entities.transporte.*;
import domain.models.entities.ubicacion.Parada;
import domain.models.entities.ubicacion.Ubicacion;
import domain.models.repos.RepositorioDeLocalidades;
import domain.services.dbManager.EntityManagerHelper;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MediosTransporteInit {

    RepositorioDeLocalidades repositorioDeLocalidades = new RepositorioDeLocalidades();

    @Test
    public void instanciarVehiculosParticulares() throws IOException {
        FactorDeEmision fe = new FactorDeEmision(2.00);
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
        TransporteAnalogico pie = new TransporteAnalogico(TipoTransporteAnalogico.PIE);
        TransporteAnalogico bici = new TransporteAnalogico(TipoTransporteAnalogico.BICICLETA);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.persist(fe);
        EntityManagerHelper.persist(pie);
        EntityManagerHelper.persist(bici);
        EntityManagerHelper.commit();
    }

    @Test
    public void instanciarLineaD() throws IOException { // TODO 1/12
        List<Parada> paradas = new ArrayList<>();
        Localidad san_nicolas =  this.repositorioDeLocalidades.buscar(5364);
        Localidad recoleta = this.repositorioDeLocalidades.buscarNombre("RECOLETA");
        Localidad belgrano = this.repositorioDeLocalidades.buscar(5337);

        System.out.println(san_nicolas.getNombre());
        System.out.println(recoleta.getNombre());
        System.out.println(belgrano.getNombre());

        Ubicacion ubicacionInicial = new Ubicacion("San Martin", 1, san_nicolas);
        Parada paradaInicial = new Parada(ubicacionInicial);
        paradas.add(paradaInicial);

        Ubicacion nueveDeJulio = new Ubicacion("Carlos Pellegrini", 300, san_nicolas);
        Parada nueveDeJulioParada = new Parada(nueveDeJulio);
        paradas.add(nueveDeJulioParada);

        Ubicacion tribunales = new Ubicacion("Talacahuano", 600, san_nicolas);
        Parada tribunalesParada = new Parada(tribunales);
        paradas.add(tribunalesParada);

        Ubicacion callao = new Ubicacion("Avenida Cordoba", 1800, san_nicolas);
        Parada callaoParada = new Parada(callao);
        paradas.add(callaoParada);

        Ubicacion facultad = new Ubicacion("Avenida Cordoba", 2100, recoleta);
        Parada facultadParada = new Parada(facultad);
        paradas.add(facultadParada);

        Ubicacion pueyrredon = new Ubicacion("Avenida Santa Fe", 2500, recoleta);
        Parada pueyrredonParada = new Parada(pueyrredon);
        paradas.add(pueyrredonParada);

        Ubicacion aguero = new Ubicacion("Avenida Santa Fe", 2900, recoleta);
        Parada agueroParada = new Parada(aguero);
        paradas.add(agueroParada);

        Localidad palermo = this.repositorioDeLocalidades.buscarNombre("PALERMO");
        Ubicacion bulnes = new Ubicacion("Avenida Santa Fe", 3300, palermo);
        Parada bulnesParada = new Parada(bulnes);
        paradas.add(bulnesParada);

        Ubicacion scalabrini = new Ubicacion("Avenida Santa Fe", 3700, palermo);
        Parada scalabriniParada = new Parada(scalabrini);
        paradas.add(scalabriniParada);

        Ubicacion plazaItalia = new Ubicacion("Avenida Santa Fe", 3700, palermo);
        Parada plazaItaliaParada = new Parada(plazaItalia);
        paradas.add(plazaItaliaParada);

        Ubicacion palermoUbic = new Ubicacion("Avenida Santa Fe", 4100, palermo);
        Parada palermoParada = new Parada(palermoUbic);
        paradas.add(palermoParada);

        Ubicacion ministroCarranza = new Ubicacion("Avenida Santa Fe", 5300, belgrano);
        Parada ministroCarranzaParada = new Parada(ministroCarranza);
        paradas.add(ministroCarranzaParada);

        Ubicacion olleros = new Ubicacion("Avenida Cabildo", 700, belgrano);
        Parada ollerosParada = new Parada(olleros);
        paradas.add(ollerosParada);

        Ubicacion joseHernandez = new Ubicacion("Avenida Cabildo", 1700, belgrano);
        Parada joseHernandezParada = new Parada(joseHernandez);
        paradas.add(joseHernandezParada);

        Ubicacion juramento = new Ubicacion("Avenida Cabildo", 2000, belgrano);
        Parada juramentoParada = new Parada(juramento);
        paradas.add(juramentoParada);

        Ubicacion ubicacionFinal = new Ubicacion("Avenida Cabildo", 2800, belgrano);
        Parada paradaFinal = new Parada(ubicacionFinal);
        paradas.add(paradaFinal);

        TransportePublico lineaD = new TransportePublico("D", TipoTransportePublico.Subte, paradaInicial, paradaFinal);
        lineaD.setParadas(paradas);
        EntityManagerHelper.beginTransaction();

        EntityManagerHelper.persist(ubicacionInicial);
        EntityManagerHelper.persist(nueveDeJulio);
        EntityManagerHelper.persist(tribunales);
        EntityManagerHelper.persist(callao);
        EntityManagerHelper.persist(facultad);
        EntityManagerHelper.persist(pueyrredon);
        EntityManagerHelper.persist(aguero);
        EntityManagerHelper.persist(bulnes);
        EntityManagerHelper.persist(scalabrini);
        EntityManagerHelper.persist(plazaItalia);
        EntityManagerHelper.persist(palermo);
        EntityManagerHelper.persist(ministroCarranza);
        EntityManagerHelper.persist(olleros);
        EntityManagerHelper.persist(joseHernandez);
        EntityManagerHelper.persist(juramento);
        EntityManagerHelper.persist(ubicacionFinal);

        EntityManagerHelper.persist(paradaInicial);
        EntityManagerHelper.persist(nueveDeJulioParada);
        EntityManagerHelper.persist(tribunalesParada);
        EntityManagerHelper.persist(callaoParada);
        EntityManagerHelper.persist(facultadParada);
        EntityManagerHelper.persist(pueyrredonParada);
        EntityManagerHelper.persist(agueroParada);
        EntityManagerHelper.persist(bulnesParada);
        EntityManagerHelper.persist(scalabriniParada);
        EntityManagerHelper.persist(plazaItaliaParada);
        EntityManagerHelper.persist(palermoParada);
        EntityManagerHelper.persist(ministroCarranzaParada);
        EntityManagerHelper.persist(ollerosParada);
        EntityManagerHelper.persist(joseHernandezParada);
        EntityManagerHelper.persist(juramentoParada);
        EntityManagerHelper.persist(paradaFinal);

        EntityManagerHelper.persist(lineaD);
        EntityManagerHelper.commit();
    }



}
