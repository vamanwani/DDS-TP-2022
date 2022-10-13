package calculadoraHC;

import domain.consumo.*;
import domain.models.entities.consumo.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static domain.models.entities.consumo.TipoAlcance.EMISIONESDIRECTAS;

public class HCTestOtroConsumo {

    public OtroConsumo consumo;
    public Actividad actividad;
    public PeriodoDeImputacion periodoDeImputacion;
    public TipoConsumo tipoConsumo;
    public TipoAlcance tipoAlcance;

    @Before
    public void init(){
        this.tipoAlcance = EMISIONESDIRECTAS;
        this.actividad = new Actividad(tipoAlcance, "Combustion fija");
        this.periodoDeImputacion =  new PeriodoDeImputacion("02/2022");
        this.tipoConsumo = new TipoConsumo("Gas Natural", Unidad.M3);
        this.consumo = new OtroConsumo(actividad, periodoDeImputacion, tipoConsumo, 230);

    }

    @Test
    public void calculadoraHCOtroConsumo(){
        tipoConsumo.setValorParaFE(3);
        Assert.assertEquals(consumo.calcularHC(),690, 0.00);
        tipoConsumo.setValorParaFE(4);
        Assert.assertNotEquals(consumo.calcularHC(), 690, 0.00);
        //Assert.assertEquals(periodoDeImputacion.getPeriodicidad(), TipoPeriodicidad.ANUAL);
    }

}
