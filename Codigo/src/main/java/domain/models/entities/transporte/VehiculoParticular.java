package domain.models.entities.transporte;

import lombok.NoArgsConstructor;

import javax.persistence.*;
@Entity
@DiscriminatorValue("Particular")
@NoArgsConstructor
public class VehiculoParticular extends Transporte{

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_vehiculo")
    private TipoVehiculoParticular vehiculo;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_combustible")
    private TipoCombustible combustible;

    public VehiculoParticular(TipoVehiculoParticular vehiculo, TipoCombustible combustible) {
        this.vehiculo = vehiculo;
        this.combustible = combustible;
        this.setNombre("Vehiculo Particular");
    }
    public void registrarConsumoDeCombustible(){
        //TODO
    }
}
