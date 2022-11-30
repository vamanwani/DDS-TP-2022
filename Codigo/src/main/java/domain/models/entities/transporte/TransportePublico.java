package domain.models.entities.transporte;

import domain.models.entities.consumo.FactorDeEmision;
import domain.models.entities.ubicacion.Parada;
import domain.models.entities.ubicacion.Ubicacion;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Entity
@NoArgsConstructor
@DiscriminatorValue("Publico")
public class TransportePublico extends Transporte {


    @Column(name = "linea")
    private String linea;

    @Enumerated(EnumType.STRING)
    @Column(name = "transporte")
    private TipoTransportePublico transporte;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "paradas_id")
    private List<Parada> paradas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paradaInicio_id")
    private Parada paradaInicial;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paradaFinal_id")
    private Parada paradaFinal;

    @Override
    public String getModelo(){
        return String.valueOf(transporte);
    }


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
        this.setNombre("Transporte Publico");
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
