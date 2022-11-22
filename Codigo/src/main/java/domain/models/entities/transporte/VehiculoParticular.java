package domain.models.entities.transporte;

import domain.models.entities.consumo.FactorDeEmision;
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

    public VehiculoParticular(TipoVehiculoParticular vehiculo, TipoCombustible combustible, FactorDeEmision factorDeEmision) {
        this.vehiculo = vehiculo;
        this.combustible = combustible;
        this.setNombre("Vehiculo Particular");
        this.setFactorDeEmision(factorDeEmision);
    }
    public void registrarConsumoDeCombustible(){
        //TODO
    }
}
