package reportes;

import domain.consumo.*;
import domain.miembro.Miembro;
import domain.organizacion.Organizacion;
import org.junit.Before;

public class ReportesInit {

    public Organizacion unaOrganizacion;
    public ConsumoLogistica primerConsumo;
    public ConsumoLogistica segundoConsumo;
    public OtroConsumo tercerConsumo;
    public TipoConsumo primerTipoConsumo;
    public TipoConsumo segundoTipoConsumo;
    public TipoConsumo tercerTipoConsumo;
    public Miembro primerMiembro;
    public Miembro segundoMiembro;
    public Miembro tercerMiembro;
    @Before
    public void init(){
        unaOrganizacion = new Organizacion();
        primerConsumo = new ConsumoLogistica();
        primerTipoConsumo = new TipoConsumo("Nafta",Unidad.LT);
        primerTipoConsumo.setValorParaFE(2.0);
        primerConsumo.setTipoConsumo(primerTipoConsumo);
        primerConsumo.setDistancia(24.0);
        primerConsumo.setPeso(33.0);

        segundoTipoConsumo = new TipoConsumo("Nafta",Unidad.LT);
        segundoTipoConsumo.setValorParaFE(3.0);
        segundoConsumo.setTipoConsumo(segundoTipoConsumo);
        segundoConsumo.setDistancia(77.9);
        segundoConsumo.setPeso(67.1);

        tercerTipoConsumo = new TipoConsumo("Nafta",Unidad.LT);
        tercerTipoConsumo.setValorParaFE(4.0);
        tercerConsumo.setTipoConsumo(tercerTipoConsumo);
        tercerConsumo.setValorConsumo(2.0);

        unaOrganizacion.agregarConsumo(primerConsumo);
        unaOrganizacion.agregarConsumo(segundoConsumo);
        unaOrganizacion.agregarConsumo(tercerConsumo);

        primerMiembro = new Miembro();

        segundoMiembro = new Miembro();

        tercerMiembro = new Miembro();
    }
}
