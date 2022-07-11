package calculadoraHC;

import domain.consumo.*;
import domain.organizacion.Organizacion;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.internal.matchers.Or;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static domain.consumo.TipoAlcance.EMISIONESDIRECTAS;
import static domain.consumo.TipoAlcance.EMISIONESINDIRECTASNOCONTROLADAS;

public class HCTestOrganizacion {

    public Organizacion organizacion;

    public static OtroConsumo consumo;
    public Actividad actividad;
    public PeriodoDeImputacion periodo;
    public TipoConsumo tipoConsumo;
    public TipoAlcance tipoAlcance;

    public static ConsumoLogistica consumoLogistica;
    public Actividad actividadL;
    public PeriodoDeImputacion periodoDeImputacionL;
    public TipoConsumo tipoConsumoL;
    public TipoAlcance tipoAlcanceL;

    @Before
    public void init(){
        this.organizacion = new Organizacion();
//        organizacion.agregarConsumo(HCTestConsumoLogistica.consumoLogistica);
//        organizacion.agregarConsumo(HCTestOtroConsumo.consumo);


        this.tipoAlcance = EMISIONESDIRECTAS;
        this.actividad = new Actividad(tipoAlcance, "Combustion fija");
        this.periodo =  new PeriodoDeImputacion("03/2022");
        this.tipoConsumo = new TipoConsumo("Gas Natural", Unidad.M3);
        tipoConsumo.setValorParaFE(3);
        this.consumo = new OtroConsumo(actividad, periodo, tipoConsumo, 230);
        organizacion.agregarConsumo(consumo);

        this.tipoAlcanceL = EMISIONESINDIRECTASNOCONTROLADAS;
        this.actividadL = new Actividad(tipoAlcance, "Logistica de productos y residuos");
        this.periodoDeImputacionL =  new PeriodoDeImputacion("12/2022");
        this.tipoConsumoL = new TipoConsumo("Logistica productos residuos", Unidad.KM);
        this.consumoLogistica = new ConsumoLogistica(actividadL, periodoDeImputacionL, tipoConsumoL, 500, 150,"Camion carga", "Materia prima");
        tipoConsumoL.setValorParaFE(3);
        organizacion.agregarConsumo(consumoLogistica);

    }
    @Test
    public void testConsumosOrganizacion(){

        Assert.assertTrue(consumo.getPeriodicidad().getPeriodicidad() == TipoPeriodicidad.MENSUAL);
        Assert.assertTrue(consumo.getPeriodicidad().getAnio() == 2022);
        Assert.assertTrue(consumo.getPeriodicidad().getMes() == 03);
        Assert.assertTrue(consumoLogistica.getPeriodicidad().getAnio() == 2022);


        Assert.assertEquals(organizacion.calcularHCOrganizacion(new PeriodoDeImputacion("2022")), 225690, 0);
        Assert.assertEquals(organizacion.calcularHCOrganizacion(new PeriodoDeImputacion("2023")), 0, 0);
        Assert.assertEquals(organizacion.calcularHCOrganizacion(new PeriodoDeImputacion("03/2022")), 690, 0);
        Assert.assertEquals(organizacion.calcularHCOrganizacion(new PeriodoDeImputacion("12/2022")), 225000, 0);

    }

}
