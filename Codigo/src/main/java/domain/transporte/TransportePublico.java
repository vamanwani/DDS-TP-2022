package domain.transporte;

import domain.ubicacion.Parada;
import domain.ubicacion.Ubicacion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TransportePublico extends Transporte{
    private String linea;
    private TipoTransportePublico transporte;
    private ArrayList<Parada> paradas;
    private Parada paradaInicial;
    private Parada paradaFinal;


    public TransportePublico(String linea,
                             TipoTransportePublico transporte,
                             ArrayList<Parada> paradas,
                             Parada paradaInicial,
                             Parada paradaFinal) throws IOException {
        this.linea = linea;
        this.transporte = transporte;
        this.paradas = paradas;
        this.paradaInicial = paradaInicial;
        this.paradaFinal = paradaFinal;
        this.setDistanciaAPI();
    }
    @Override public double distancia(Ubicacion puntoInicio,Ubicacion puntoFin){
        Parada paradaInicio=paradas.stream().
                filter(parada -> parada.getLocalizacion()==puntoInicio).
                collect(Collectors.toCollection(ArrayList::new)).get(0);

        Parada paradaAux=paradaInicio.getParadaSiguiente();
        double distancia=paradaInicio.getDistanciaParadaSiguiente();

        while (paradaAux.getLocalizacion() != puntoFin){
            distancia += paradaAux.getDistanciaParadaSiguiente();
            paradaAux = paradaAux.getParadaSiguiente();
        }
        return distancia;
    }
    public void registrarConsumoDeCombustible(){
        //TODO
    }
}
