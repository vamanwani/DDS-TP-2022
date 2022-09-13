package domain.reporte;

import java.io.IOException;
import java.util.List;

public interface ReportableComposicion {
    public List<Double> contenidoReporteComposicion() throws IOException;
}
