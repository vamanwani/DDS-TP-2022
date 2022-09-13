package domain.reporte;

import domain.organizacion.Organizacion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReporteHCEvolucionOrganizacion implements ReportableEvolucion{
    private Organizacion organizacion;
    private List<Actualizacion> actualizaciones;
    public List<Actualizacion> contenidoReporteEvolucion(){
        List<Actualizacion> actualizados = new ArrayList<>();
        for(Actualizacion act : actualizaciones){
            try {
                new Actualizacion(null, organizacion.calcularHCOrganizacion(null));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            actualizados.add(act);

        }
        return actualizados;
    }
}
