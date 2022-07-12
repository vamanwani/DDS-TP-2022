package calculadoraHC;

import domain.consumo.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static domain.consumo.TipoAlcance.EMISIONESINDIRECTASNOCONTROLADAS;

public class HCTestConsumoLogistica {

    public static ConsumoLogistica consumoLogistica;
    public Actividad actividad;
    public PeriodoDeImputacion periodoDeImputacion;
    public TipoConsumo tipoConsumo;
    public TipoAlcance tipoAlcance;

    @Before
    public void init(){
        this.tipoAlcance = EMISIONESINDIRECTASNOCONTROLADAS;
        this.actividad = new Actividad(tipoAlcance, "Logistica de productos y residuos");
        this.periodoDeImputacion =  new PeriodoDeImputacion("2022");
        this.tipoConsumo = new TipoConsumo("Logistica productos residuos", Unidad.KM);
        this.consumoLogistica = new ConsumoLogistica(actividad, periodoDeImputacion, tipoConsumo, 500, 150,"Camion carga", "Materia prima");
    }

    @Test
    public void calculadoraHCLogistica(){
        tipoConsumo.setValorParaFE(3);
        Assert.assertEquals(consumoLogistica.calcularHC(), 225000, 0);
        tipoConsumo.setValorParaFE(4);
        Assert.assertNotEquals(consumoLogistica.calcularHC(), 225000, 0);
    }
}
