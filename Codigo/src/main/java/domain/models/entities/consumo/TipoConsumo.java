package domain.models.entities.consumo;


import javax.persistence.*;

@Entity
@Table(name = "tipoConsumo")
public class TipoConsumo implements AsignableFE{
    @Id
    @GeneratedValue
    @JoinColumn(name = "id_consumo")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(name = "unidad")
    private Unidad unidad;

    @OneToOne
    @JoinColumn(name = "fe_id")
    private FactorDeEmision fe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoConsumo() {
    }

    public TipoConsumo(String nombre, Unidad unidad) {
        this.nombre = nombre;
        this.unidad = unidad;
    }

//    public void setValorParaFE(double valorParaFE) {
//        this.valorParaFE = valorParaFE;
//    }

    public String getNombre() {
        return nombre;
    }

    public double getValorParaFE() {
        return fe.getValorFE();
    }

    public void setFe(FactorDeEmision fe) {
        this.fe = fe;
    }

    @Override
    public void setValorParaFE(double valor) {

    }
}
