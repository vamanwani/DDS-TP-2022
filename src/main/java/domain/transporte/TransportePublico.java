package domain.transporte;

import domain.ubicacion.Parada;

import java.util.ArrayList;

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
                             Parada paradaFinal) {
        this.linea = linea;
        this.transporte = transporte;
        this.paradas = paradas;
        this.paradaInicial = paradaInicial;
        this.paradaFinal = paradaFinal;
    }
}
