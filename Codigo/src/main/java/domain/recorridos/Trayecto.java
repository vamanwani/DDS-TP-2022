package domain.recorridos;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//import java.util.arrayList;
//@Entity
@Entity
@Table(name = "trayecto")
public class Trayecto {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tramo> tramos = new ArrayList<>();

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    public Trayecto() {
    }

    public Trayecto(Tramo @NotNull ... tramos) {
        for(Tramo tramo : tramos){
            this.tramos.add(tramo);
        }
    }

    public double calculoDistanciaTotal() throws IOException {
        return tramos.stream().mapToDouble(tramo -> {
            try {
                return tramo.distanciaTramo();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).sum();
    }

    public List<Tramo> getTramos() {
        return tramos;
    }

}
