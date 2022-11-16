package domain.models.entities.ubicacion;

import javax.persistence.*;

@Entity
@Table(name = "parada")
public class Parada {

    @Id
    @GeneratedValue
    @Column(name = "id_parada")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "localizacion_id")
    private Ubicacion localizacion;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parada_ant_id")
    private Parada paradaAnterior;

    @Column(name = "distancia_parada_ant")
    private double distanciaParadaAnterior;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parada_sig_id")
    private Parada paradaSiguiente;

    @Column(name = "distancia_parada_sig")
    private double distanciaParadaSiguiente;

    public Long getId() {
        return id;
    }

    public Parada() {
    }

    public Parada(Ubicacion localizacion, Parada paradaAnterior, Parada paradaSiguiente) {
        this.localizacion = localizacion;
        this.paradaAnterior = paradaAnterior;
        this.paradaSiguiente = paradaSiguiente;
    }

    public Parada(Ubicacion localizacion){this.localizacion = localizacion;}

    public Ubicacion getLocalizacion(){
        return localizacion;
    }
    public Parada getParadaSiguiente(){return paradaSiguiente;}
    public double getDistanciaParadaAnterior() {
        return distanciaParadaAnterior;
    }

    public double getDistanciaParadaSiguiente() {
        return distanciaParadaSiguiente;
    }
}
