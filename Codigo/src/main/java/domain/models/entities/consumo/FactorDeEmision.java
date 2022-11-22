package domain.models.entities.consumo;

import javax.persistence.*;

@Entity
@Table(name = "factor_emision")
public class FactorDeEmision{
    @Id
    @GeneratedValue
    @Column(name = "id_factorEmision")
    private Integer id;

    @Column(name = "valor_fe")
    private double valorFE;

    public FactorDeEmision() {
    }

    public FactorDeEmision(double valorFE) {
        this.valorFE = valorFE;
    }

    //borramos metodos calcularFE, calcularFETransporte

    public double getValorFE() {
        return valorFE;
    }

    public void setValor(double valorFE){
        this.valorFE = valorFE;
    }
}
