package domain.models.entities.transporte;

import javax.persistence.*;

@Entity
@Table(name = "vehiculo_particular")
public class VehiculoParticular extends Transporte{
    @Id
    @GeneratedValue
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_vehiculo")
    private TipoVehiculoParticular vehiculo;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_combustible")
    private TipoCombustible combustible;

    public VehiculoParticular(TipoVehiculoParticular vehiculo, TipoCombustible combustible) {
        this.vehiculo = vehiculo;
        this.combustible = combustible;
    }
    public void registrarConsumoDeCombustible(){
        //TODO
    }
}
