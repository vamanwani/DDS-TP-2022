package domain.ubicacion;

import javax.persistence.*;

@Entity
@Table(name = "parada")
public class Parada {
    @Id
    @Column(name = "id_parada", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
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

    public void setLocalizacion(Ubicacion localizacion) {
        this.localizacion = localizacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Parada() {
    }

    //TODO Hacer constructores opcionales por si no existe paradaAnterior o paradaSiguiente
    public Parada(Ubicacion localizacion, Parada paradaAnterior, Parada paradaSiguiente) {
        this.localizacion = localizacion;
        this.paradaAnterior = paradaAnterior;
        this.paradaSiguiente = paradaSiguiente;
    }

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
