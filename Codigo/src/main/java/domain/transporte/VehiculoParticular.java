package domain.transporte;

public class VehiculoParticular extends Transporte{
    private TipoVehiculoParticular vehiculo;
    private TipoCombustible combustible;

    public VehiculoParticular(TipoVehiculoParticular vehiculo, TipoCombustible combustible) {
        this.vehiculo = vehiculo;
        this.combustible = combustible;
    }
    public void registrarConsumoDeCombustible(){
        //TODO
    }
}
