package domain.consumo;

public class Actividad implements AsignableFE {
    private TipoAlcance alcance;
    private String nombre;
    private double valorParaFE;

    public void setValorParaFE(double valorParaFE) {
        this.valorParaFE = valorParaFE;
    }
}
