package domain.transporte;

import domain.ubicacion.Parada;
import domain.ubicacion.Ubicacion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TransportePublico extends Transporte {
    private String linea;
    private TipoTransportePublico transporte;
    private List<Parada> paradas;
    private Parada paradaInicial;
    private Parada paradaFinal;


    public TransportePublico(String linea,
                             TipoTransportePublico transporte,
                             Parada paradaInicial,
                             Parada paradaFinal) throws IOException {
        this.linea = linea;
        this.transporte = transporte;
        this.paradas = new ArrayList<>();
        this.paradaInicial = paradaInicial;
        this.paradaFinal = paradaFinal;
        this.setDistanciaAPI();
    }

    public void setParadas(List<Parada> paradas) {
        this.paradas = paradas;
    }

    @Override
    public double distancia(Ubicacion puntoInicio, Ubicacion puntoFin) {
        Parada paradaInicio = paradas.stream().
                filter(parada -> parada.getLocalizacion() == puntoInicio).
                collect(Collectors.toCollection(ArrayList::new)).get(0);

        Parada paradaAux = paradaInicio.getParadaSiguiente();
        double distancia = paradaInicio.getDistanciaParadaSiguiente();

        while (paradaAux.getLocalizacion() != puntoFin) {
            distancia += paradaAux.getDistanciaParadaSiguiente();
            paradaAux = paradaAux.getParadaSiguiente();
        }
        return distancia;
    }

    public void registrarConsumoDeCombustible() {
        //TODO
    }

    public double sentido(Ubicacion puntoInicio, Ubicacion puntoFin) { //EN CASO DE QUE LA LINEA DE RECORRIDO SEA LA MISMA PARA IR Y VOLVER
        Parada paradaInicio = paradas.stream()
                .filter(parada -> parada.getLocalizacion() == puntoInicio)
                .findFirst().get();

        Parada paradaFinal = paradas.stream()
                .filter(parada -> parada.getLocalizacion() == puntoFin)
                .findFirst().get();

        int indexPuntoInicio = paradas.indexOf(paradaInicio);
        int indexPuntoFinal = paradas.indexOf(paradaFinal);

        ////List<Parada> paradasUtiles = paradas.subList(indexPuntoInicio,indexPuntoFinal);
        double distancia = 0;
        if (indexPuntoInicio < indexPuntoFinal) { //usar distanciaSiguiente
            return this.distancia(puntoInicio, puntoFin);
        } else {
            return this.distancia(puntoFin, puntoInicio);
        }
    }
}
