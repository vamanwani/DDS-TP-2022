package domain.models.entities.organizacion; //TODO ORGANIZACION RECIBE LAS RECOMENDACIONES

import domain.models.entities.calculoHC.CalculadoraHCOrganizacion;
import domain.models.entities.calculoHC.CalculdoraHCMiembro;
import domain.models.entities.consumo.Consumo;
import domain.models.entities.consumo.PeriodoDeImputacion;
import domain.models.entities.miembro.Miembro;
import domain.models.entities.miembro.SolicitudVinculacion;
import domain.models.entities.miembro.Usuario;
import domain.models.entities.ubicacion.Ubicacion;

import javax.persistence.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "organizacion")
public class Organizacion {
    @Id
    @GeneratedValue
    @Column(name = "id_organizacion")
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_org")
    private TipoDeOrganizacion tipoDeOrganizacion;

    @OneToMany
    @JoinColumn(name = "solicitud_vinculacion_id")
    private List<SolicitudVinculacion> solicitudes;

    @ManyToOne
    @JoinColumn(name = "clasificacion_org")
    private ClasificaciónDeOrg clasificacionDeOrg;

    public ClasificaciónDeOrg getClasificacionDeOrg() {
        return clasificacionDeOrg;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organizacion")
    private List<Sector> sectores;

    @Column(name = "razon_social")
    private String razonSocial;

    @OneToOne
    @JoinColumn(name = "ubicacion_id")
    private Ubicacion ubicacion;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "consumo_id")
    private List<Consumo> consumos = new ArrayList<Consumo>();;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "miembro_id")
    private Set<Miembro> contactos; //DEFINIDOS POR EL ADMININSTANCIAR EN CONSTRUCTOR

    @Column(name = "link_recomendacion")
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

  public void setClasificacionDeOrg(ClasificaciónDeOrg clasificacionDeOrg){ this.clasificacionDeOrg = clasificacionDeOrg;}

    public Set<Miembro> getContactos() {
        return contactos;
    }

    public void agregarConsumo(Consumo consumo){
        this.consumos.add(consumo);
  }

    public void agregarSectores(Sector sector){
        this.sectores.add(sector);
    }

    public double calcularHCOrganizacion(PeriodoDeImputacion periodoACalcular) throws IOException {
        return new CalculadoraHCOrganizacion().calcularHC(getConsumos(), periodoACalcular) + hcMiembrosOrganizacion();

        //+ sectores.stream().mapToDouble(sector -> sector.calcularHCSector()).sum();
    }

    public double calcularHCOrgHistorico() throws IOException{
        double hc = 0;
        for (int i = 0; i < consumos.size(); i++){
            hc += this.calcularHCOrganizacion(consumos.get(i).getPeriodicidad());
        }
        return hc;
    }

    public double hcMiembrosOrganizacion() throws IOException {
        List<Double> hcs = new ArrayList<>();
        for (Miembro m : listarMiembros()){
            hcs.add(new CalculdoraHCMiembro().calcularHC(m.getTrayectos()));
        }
        return  hcs.stream().mapToDouble(Double::doubleValue).sum();
    }

    public List<Sector> getSectores() {
        return sectores;
    }

    public String getRazonSocial() {
        return razonSocial;
    }
}
