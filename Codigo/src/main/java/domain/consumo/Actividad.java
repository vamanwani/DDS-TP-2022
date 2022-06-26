package domain.consumo;

public class Actividad implements AsignableFE {
    private TipoAlcance alcance;
    private String nombre;
    private double valorParaFE;

    public Actividad(TipoAlcance alcance, String nombre) {
        this.alcance = alcance;
        this.nombre = nombre;
    }

    public void setValorParaFE(double valorParaFE) {
        this.valorParaFE = valorParaFE;
    }
}
