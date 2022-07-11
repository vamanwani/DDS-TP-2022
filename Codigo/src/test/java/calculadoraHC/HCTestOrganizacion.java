package calculadoraHC;

import domain.consumo.PeriodoDeImputacion;
import domain.organizacion.Organizacion;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.matchers.Or;

public class HCTestOrganizacion {

    public Organizacion organizacion;
    public PeriodoDeImputacion periodoDeImputacion;

    @Before
    public void init(){
        organizacion = new Organizacion();
        organizacion.agregarConsumo(HCTestOtroConsumo.consumo);
        organizacion.agregarConsumo(HCTestConsumoLogistica.consumoLogistica);
        periodoDeImputacion = new PeriodoDeImputacion("02/2022");
    }
    @Test
    public void testConsumosOrganizacion(){
        //Assert.assertEquals(organizacion.consumos.size(), 2);
        Assert.assertEquals(organizacion.calcularHCOrganizacion(periodoDeImputacion), 3400, 0.00);
    }

}
