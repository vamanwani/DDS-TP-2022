package domain.consumo;

public class TipoActividad implements AsignableFE{
    private String nombre;
    private double valorParaFE;

    public void setValorParaFE(double valorParaFE) {
        this.valorParaFE = valorParaFE;
    }
}
