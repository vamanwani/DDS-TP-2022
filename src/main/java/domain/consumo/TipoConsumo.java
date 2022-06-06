package domain.consumo;

public class TipoConsumo implements AsignableFE{
    private String nombre;
    private Unidad unidad;

    private double valorParaFE;

    public void setValorParaFE(double valorParaFE) {
        this.valorParaFE = valorParaFE;
    }

    public String getNombre() {
        return nombre;
    }


}
