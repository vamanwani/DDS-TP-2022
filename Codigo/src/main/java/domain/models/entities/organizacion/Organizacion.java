package domain.models.entities.organizacion; //TODO ORGANIZACION RECIBE LAS RECOMENDACIONES

import domain.models.entities.calculoHC.CalculadoraHCOrganizacion;
import domain.models.entities.calculoHC.CalculdoraHCMiembro;
import domain.models.entities.consumo.Consumo;
import domain.models.entities.consumo.PeriodoDeImputacion;
import domain.models.entities.miembro.Miembro;
import domain.models.entities.miembro.SolicitudVinculacion;
import domain.models.entities.miembro.Usuario;
import domain.models.entities.ubicacion.Ubicacion;
import domain.models.repos.RepositorioDeConsumos;

import javax.persistence.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "organizacion")
public class Organizacion {
    @Transient
    RepositorioDeConsumos repositorioDeConsumos = new RepositorioDeConsumos();

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "clasificacion_org_id")
    private ClasificaciónDeOrg clasificacionDeOrg;

    public Organizacion(TipoDeOrganizacion tipoDeOrganizacion, ClasificaciónDeOrg clasificacionDeOrg, String razonSocial, Ubicacion ubicacion, Usuario usuario) {
        this.tipoDeOrganizacion = tipoDeOrganizacion;
        this.clasificacionDeOrg = clasificacionDeOrg;
        this.razonSocial = razonSocial;
        this.ubicacion = ubicacion;
        this.usuario = usuario;
        this.contactos = new HashSet<>();
        this.sectores = new ArrayList<>();
//        this.consumos = new ArrayList<>();
    }

    public ClasificaciónDeOrg getClasificacionDeOrg() {
        return clasificacionDeOrg;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organizacion")
    private List<Sector> sectores;

    @Column(name = "razon_social")
    private String razonSocial;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ubicacion_id")
    private Ubicacion ubicacion;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

//    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//    @JoinColumn(name = "organizacion_id")
//    private List<Consumo> consumos;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "miembro_id")
    private Set<Miembro> contactos; //DEFINIDOS POR EL ADMININSTANCIAR EN CONSTRUCTOR

    public String getLinkRecomendacion() {
        return linkRecomendacion;
    }

    @Column(name = "link_recomendacion")
    private String linkRecomendacion; //LINK DEL .PDF DE RECOMENDACION


    public List<Consumo> getConsumos() {
        return repositorioDeConsumos.buscarConsumosDeOrg(this);
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }
    public Organizacion() {
        this.contactos = new HashSet<>();
        this.sectores = new ArrayList<>();
//        this.consumos = new ArrayList<>();
    }

    public void agregarContacto(Miembro nuevoContacto){
        this.contactos.add(nuevoContacto);
    }
    public void notificarContactos(String link){
        //TODO Se contacta con la api para mandar mails y mensajes de wpp
        this.contactos.forEach(contacto -> contacto.recibirRecomendacion(link));
    }

    public List<Miembro> listarMiembros(){
        List<Miembro> miembroSet = new ArrayList<>();
        for (Sector unSector : this.sectores)
        {
            for(Miembro unMiembro : unSector.getMiembros())
            {
                miembroSet.add(unMiembro);
            }
        }
        //miembroList=  this.sectores.stream().map(sector -> sector.getMiembros()).collect(Collectors.toList());
        return (List<Miembro>) miembroSet;
    }
  public void setTipoDeOrganizacion(TipoDeOrganizacion tipoDeOrganizacion) {
      this.tipoDeOrganizacion = tipoDeOrganizacion;
  }

  public void setClasificacionDeOrg(ClasificaciónDeOrg clasificacionDeOrg){ this.clasificacionDeOrg = clasificacionDeOrg;}

    public Set<Miembro> getContactos() {
        return contactos;
    }

    public void agregarConsumo(Consumo consumo){
//        this.consumos.add(consumo);
  }

    public void agregarSectores(Sector sector){
        this.sectores.add(sector);
    }

    public double calcularHCOrganizacion(PeriodoDeImputacion periodoACalcular) throws IOException {
        return new CalculadoraHCOrganizacion().calcularHC(getConsumos(), periodoACalcular) + calcularHCMiembrosSegunPeriodo(periodoACalcular);
        //+ sectores.stream().mapToDouble(sector -> sector.calcularHCSector()).sum();
    }

    private double calcularHCMiembrosSegunPeriodo(PeriodoDeImputacion periodoACalcular) throws IOException {
        double hc = 0;
        for(Miembro miembro : listarMiembros()){
            hc += miembro.calcularHCMiembro(periodoACalcular);
        }
        return hc;
    }

    public double calcularHCOrgHistorico() throws IOException{
        double hc = 0;
        for (int i = 0; i < getConsumos().size(); i++){
            hc += this.calcularHCOrganizacion(getConsumos().get(i).getPeriodicidad());
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
    public void agregarMiembro(Miembro miembro){
        this.contactos.add(miembro);
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public TipoDeOrganizacion getTipo() {
        return tipoDeOrganizacion;
    }

    public String getNombre() {
        return razonSocial;
    }

    public String getClasificacion() {
        return clasificacionDeOrg.getNombre();
    }

}
