package domain.calculoHC;

import domain.consumo.Consumo;
import domain.consumo.OtrosConsumos;

public class CalculoOtrosConsumos implements EstrategiaDeCalculo {

    @Override
    public double calcular(Consumo consumo) {
        return consumo.factorEmision() * consumo.getValorConsumo();
    }
}
