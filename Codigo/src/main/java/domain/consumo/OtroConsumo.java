package domain.consumo;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("otroConsumo")
public class OtroConsumo extends Consumo{
    public double getValorConsumo() {
        return valorConsumo;
    }

    public void setValorConsumo(double valorConsumo) {
        this.valorConsumo = valorConsumo;
    }

    @Column(name = "valor")
    private double valorConsumo;

    public OtroConsumo() {
    }

    public OtroConsumo(Actividad actividad, PeriodoDeImputacion periodicidad, TipoConsumo tipoConsumo, double valorConsumo) {
        this.actividad = actividad;
        this.periodicidad = periodicidad;
        this.tipoConsumo = tipoConsumo;
        this.valorConsumo = valorConsumo;
    }

    @Override
    public double calcularHC(){
        return valorConsumo * tipoConsumo.getValorParaFE();
    }
}
