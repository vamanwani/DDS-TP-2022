package domain.models.entities.transporte;

import domain.models.entities.consumo.FactorDeEmision;
import domain.models.entities.ubicacion.Ubicacion;
import domain.services.adapters.ServicioGeodds;

import javax.persistence.*;
import java.io.IOException;
@Entity
@Table(name = "transporte")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public abstract class Transporte {

    @Transient
    private ServicioGeodds distanciaAPI;

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "fe")
    private double factorDeEmision;

    @Column(name = "nombre")
    private String nombre;

    public String getId() {
        return id.toString();
    }

    public void setDistanciaAPI() throws IOException {
        this.distanciaAPI = ServicioGeodds.getInstance();
    }

    public void registrarConsumoDeCombustible(){
        //TODO
    }

    public double distancia(Ubicacion puntoInicio, Ubicacion puntoFin) throws IOException {
        distanciaAPI = ServicioGeodds.getInstance();
        return distanciaAPI.distancia(puntoInicio,puntoFin).getValor();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getFe() {
        return factorDeEmision;
    }

    public String getModelo(){

        return null;
    }



    public void setFactorDeEmision(double factorDeEmision) {
        this.factorDeEmision = factorDeEmision;
    }
}
