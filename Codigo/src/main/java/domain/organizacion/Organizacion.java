package domain.organizacion; //TODO ORGANIZACION RECIBE LAS RECOMENDACIONES

import domain.calculoHC.CalculadoraHCOrganizacion;
import domain.consumo.Consumo;
import domain.consumo.PeriodoDeImputacion;
import domain.consumo.TipoPeriodicidad;
import domain.miembro.Miembro;
import domain.miembro.Usuario;
import domain.ubicacion.Ubicacion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Organizacion {
    private TipoDeOrganizacion tipoDeOrganizacion;
    private ClasificaciónDeOrg clasificacionDeOrg;
    private List<Sector> sectores;
    private String razonSocial;
    private Ubicacion ubicacion;
    private Usuario usuario;
    private List<Consumo> consumos = new ArrayList<Consumo>();;
    private Set<Miembro> contactos; //DEFINIDOS POR EL ADMININSTANCIAR EN CONSTRUCTOR
    private String linkRecomendacion; //LINK DEL .PDF DE RECOMENDACION


    public List<Consumo> getConsumos() {
        return consumos;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }
    public Organizacion() {
        this.contactos = new HashSet<>();
        this.sectores = new ArrayList<>();
    }

    public void agregarContacto(Miembro nuevoContacto){
        this.contactos.add(nuevoContacto);
    }
    public void notificarContactos(String link){
        //TODO Se contacta con la api para mandar mails y mensajes de wpp
        this.contactos.forEach(contacto -> contacto.recibirRecomendacion(link));
    }

    public Set<Miembro> listarMiembros(){
        Set<Miembro> miembroSet = new HashSet<>();
        for (Sector unSector : this.sectores)
        {
            for(Miembro unMiembro : unSector.getMiembros())
            {
                miembroSet.add(unMiembro);
            }
        }
        //miembroList=  this.sectores.stream().map(sector -> sector.getMiembros()).collect(Collectors.toList());
        return miembroSet;
    }
  public void setTipoDeOrganizacion(TipoDeOrganizacion tipoDeOrganizacion) {
      this.tipoDeOrganizacion = tipoDeOrganizacion;
  }

    public Set<Miembro> getContactos() {
        return contactos;
    }

    public void agregarConsumo(Consumo consumo){
        this.consumos.add(consumo);
  }

    public void agregarSectores(Sector sector){
        this.sectores.add(sector);
    }
    public double calcularHCOrganizacion (PeriodoDeImputacion periodoACalcular) {
        return new CalculadoraHCOrganizacion().calcularHC(getConsumos(), periodoACalcular);
        //+ sectores.stream().mapToDouble(sector -> sector.calcularHCSector()).sum();
    }


}
