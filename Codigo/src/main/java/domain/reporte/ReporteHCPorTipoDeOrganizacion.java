package domain.reporte;

import domain.organizacion.ClasificaciónDeOrg;
import domain.organizacion.Organizacion;

import java.io.IOException;
import java.util.List;

public class ReporteHCPorTipoDeOrganizacion implements ReportableHCTotal{
    private Organizacion organizacion;
    private ClasificaciónDeOrg clasificacion;
    public double contenidoReporteHC() throws IOException {
    return organizacion.calcularHCOrganizacion(null);
    }

}
