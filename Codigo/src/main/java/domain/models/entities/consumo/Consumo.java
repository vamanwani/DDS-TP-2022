package domain.models.entities.consumo;

import domain.models.entities.organizacion.Organizacion;

import javax.persistence.*;

@Entity
@Table(name = "consumo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "clase")
public abstract class Consumo {
    @Id
    @GeneratedValue
    @Column(name = "consumo_id")
    private int id;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "actividad_id")
    protected Actividad actividad;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "periodo_imputacion_id")
    protected PeriodoDeImputacion periodicidad;
    @Column(name = "proxima_acreditacion")
    protected int proximaAcreditacionConsumo;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "tipo_consumo_id")
    protected TipoConsumo tipoConsumo;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "organizacion_id")
    protected Organizacion organizacion;

    public void setPeriodicidad(PeriodoDeImputacion periodicidad) {
        this.periodicidad = periodicidad;
    }

    public Double factorEmision(){
        String tipoConsumo = this.tipoConsumo.getNombre();
        String tipoActividad = this.actividad.getNombre();
        //Dependiendo de los dos tipos, se saca el FE (todavia no sabemos como)
        return null;
    }

    public void setTipoConsumo(TipoConsumo consumo){
        tipoConsumo = consumo;
    }
    public PeriodoDeImputacion getPeriodicidad() {
        return periodicidad;
    }



    public double calcularHC(){
        return 1;
    };

    public Actividad getActividad() {
        return actividad;
    }

    public int getProximaAcreditacionConsumo() {
        return proximaAcreditacionConsumo;
    }

    public TipoConsumo getTipoConsumo() {
        return tipoConsumo;
    }

    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
    }
}
