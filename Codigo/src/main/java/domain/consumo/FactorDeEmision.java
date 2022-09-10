package domain.consumo;

import domain.transporte.Transporte;

import javax.persistence.*;

@Entity
@Table(name = "factor_emision")
public class FactorDeEmision{
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "valor_fe")
    private double valorFE;


    public Double calcularFE(Actividad actividad, TipoConsumo consumo){
        //TODO
        return null;
    }

    public Double calcularFETransporte (Transporte medioTransporte){
        //TODO
        return null;
    }
}
