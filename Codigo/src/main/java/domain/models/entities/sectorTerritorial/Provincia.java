package domain.models.entities.sectorTerritorial;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "provincia")
public class Provincia {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "provincia_id")
    private List<Localidad> localidades;

    public Provincia() {
    }

    public Provincia(String nombre){
        localidades = new ArrayList<>();
        this.nombre = nombre;
    }

    public List<Localidad> getLocalidades() {
        return localidades;
    }

    public void setLocalidades(List<Localidad> localidades) {
        this.localidades = localidades;
    }

    public int getId() {
        return id;
    }
    
    public void setId(int unId) {this.id = unId;}

    public String getNombre() {
        return nombre;
    }
}
