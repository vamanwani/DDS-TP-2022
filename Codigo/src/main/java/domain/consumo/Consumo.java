package domain.consumo;

import javax.persistence.*;

@Entity
@Table(name = "consumo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "clase")
public abstract class Consumo {
    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne
    @JoinColumn(name = "actividad_id")
    protected Actividad actividad;
    @ManyToOne
    @JoinColumn(name = "periodo_imputacion_id")
    protected PeriodoDeImputacion periodicidad;
    @Column(name = "proxima_acreditacion")
    protected int proximaAcreditacionConsumo;
    @ManyToOne
    @JoinColumn(name = "tipo_consumo_id")
    protected TipoConsumo tipoConsumo;

    public Double factorEmision(){
        String tipoConsumo = this.tipoConsumo.getNombre();
        String tipoActividad = this.actividad.getNombre();
        //Dependiendo de los dos tipos, se saca el FE (todavia no sabemos como)
        return null;
    }

    public PeriodoDeImputacion getPeriodicidad() {
        return periodicidad;
    }



    public double calcularHC(){
        return 1;
    };
}
