package reportes;

import domain.models.entities.consumo.*;
import domain.models.entities.miembro.Miembro;
import domain.models.entities.miembro.Usuario;
import domain.models.entities.organizacion.ClasificaciónDeOrg;
import domain.models.entities.organizacion.Organizacion;
import domain.models.entities.organizacion.Sector;
import domain.models.entities.reporte.Reporte;
import domain.models.entities.sectorTerritorial.Pais;
import domain.models.entities.sectorTerritorial.SectorTerritorial;
import domain.models.entities.sectorTerritorial.TipoSectorTerritorial;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

import static domain.models.entities.consumo.TipoAlcance.EMISIONESINDIRECTASNOCONTROLADAS;

public class ReportesInit {

    public Organizacion unaOrganizacion;
    public Organizacion otraOrganizacion;
    public ConsumoLogistica primerConsumo;
    public ConsumoLogistica segundoConsumo;
    public OtroConsumo tercerConsumo;
    public TipoConsumo primerTipoConsumo;
    public TipoConsumo segundoTipoConsumo;
    public TipoConsumo tercerTipoConsumo;
    public Miembro primerMiembro;
    public Usuario primerUsuario;
    public Miembro segundoMiembro;
    public Usuario segundoUsuario;
    public Miembro tercerMiembro;
    public Usuario tercerUsuario;
    public Sector primerSector;
    public Sector segundoSector;
    public Sector tercerSector;
    public SectorTerritorial primerST;
    public SectorTerritorial segundoST;
    public static ConsumoLogistica consumoLogistica;
    public Actividad actividad;
    public PeriodoDeImputacion periodoDeImputacion;
    public TipoConsumo tipoConsumo;
    public TipoAlcance tipoAlcance;
    public List<Organizacion> listaDeOrganizaciones;
    public ClasificaciónDeOrg clasificaciónDeOrg1;
    public ClasificaciónDeOrg clasificaciónDeOrg2;
    public List<ClasificaciónDeOrg> listaClasificaciones;
    public Pais pais;

    @Before
    public void init(){
        clasificaciónDeOrg1 = new ClasificaciónDeOrg("Universidad");
        clasificaciónDeOrg2 = new ClasificaciónDeOrg("Empresa del sector primario");
        listaDeOrganizaciones = new ArrayList<>();
        listaClasificaciones = new ArrayList<>();
        listaClasificaciones.add(clasificaciónDeOrg1);
        listaClasificaciones.add(clasificaciónDeOrg2);
        unaOrganizacion = new Organizacion();
        unaOrganizacion.setClasificacionDeOrg(clasificaciónDeOrg1);
        otraOrganizacion = new Organizacion();
        otraOrganizacion.setClasificacionDeOrg(clasificaciónDeOrg2);
        primerConsumo = new ConsumoLogistica();
        primerTipoConsumo = new TipoConsumo("Nafta", Unidad.LT);
        primerTipoConsumo.setValorParaFE(2.0);
        primerConsumo.setTipoConsumo(primerTipoConsumo);
        primerConsumo.setDistancia(24.0);
        primerConsumo.setPeso(33.0);
        primerConsumo.setPeriodicidad(new PeriodoDeImputacion("02/2022"));

        segundoConsumo = new ConsumoLogistica();
        segundoTipoConsumo = new TipoConsumo("Nafta",Unidad.LT);
        segundoTipoConsumo.setValorParaFE(3.0);
        segundoConsumo.setTipoConsumo(segundoTipoConsumo);
        segundoConsumo.setDistancia(77.9);
        segundoConsumo.setPeso(67.1);
        segundoConsumo.setPeriodicidad(new PeriodoDeImputacion("02/2022"));

        tercerConsumo = new OtroConsumo();
        tercerTipoConsumo = new TipoConsumo("Nafta",Unidad.LT);
        tercerTipoConsumo.setValorParaFE(4.0);
        tercerConsumo.setTipoConsumo(tercerTipoConsumo);
        tercerConsumo.setValorConsumo(2.0);
        tercerConsumo.setPeriodicidad(new PeriodoDeImputacion("2020"));

        this.tipoAlcance = EMISIONESINDIRECTASNOCONTROLADAS;
        this.actividad = new Actividad(tipoAlcance, "Logistica de productos y residuos");
        this.periodoDeImputacion =  new PeriodoDeImputacion("2022");
        this.tipoConsumo = new TipoConsumo("Logistica productos residuos", Unidad.KM);
        this.consumoLogistica = new ConsumoLogistica(actividad, periodoDeImputacion, tipoConsumo, 500, 150,"Camion carga", "Materia prima");

        unaOrganizacion.agregarConsumo(primerConsumo);
        unaOrganizacion.agregarConsumo(segundoConsumo);
        unaOrganizacion.agregarConsumo(tercerConsumo);


        otraOrganizacion.agregarConsumo(segundoConsumo);

        primerMiembro = new Miembro("tolaba","adrian",12345678,"DNI", new Usuario("atolaba", "contrasenia"));

        segundoMiembro = new Miembro("aparicio", "nicolas",11111111, "DNI", new Usuario("naparicio", "password"));

        tercerMiembro = new Miembro("bodirikyan", "cristian", 22554489, "DNI", new Usuario("cbodirikyan", "root"));

        primerSector = new Sector("primerSector");
        primerSector.agregarMiembro(primerMiembro);
        primerSector.agregarMiembro(segundoMiembro);
        primerSector.agregarMiembro(tercerMiembro);

        segundoSector = new Sector("segundoSector");
        segundoSector.agregarMiembro(segundoMiembro);
        segundoSector.agregarMiembro(tercerMiembro);

        tercerSector = new Sector("tercerSector");
        tercerSector.agregarMiembro(primerMiembro);
        tercerSector.agregarMiembro(tercerMiembro);

        unaOrganizacion.agregarSectores(primerSector);
        unaOrganizacion.agregarSectores(segundoSector);
        unaOrganizacion.agregarSectores(tercerSector);

        otraOrganizacion.agregarSectores(segundoSector);

        listaDeOrganizaciones.add(unaOrganizacion);
        listaDeOrganizaciones.add(otraOrganizacion);

        primerST = new SectorTerritorial("buenos aires");
        primerST.setOrganizaciones(unaOrganizacion);
        primerST.setTipoSector(TipoSectorTerritorial.PROCVINCIAS);

        segundoST = new SectorTerritorial();
        segundoST.setOrganizaciones(otraOrganizacion);
        segundoST.setTipoSector(TipoSectorTerritorial.PROCVINCIAS);

        pais = new Pais();
        Set<SectorTerritorial> sectorTerritorialSet = new HashSet<SectorTerritorial>();
        sectorTerritorialSet.add(primerST);
        sectorTerritorialSet.add(segundoST);
        pais.setSectoresTerritoriales(sectorTerritorialSet);
    }

    @Test
    public void pruebaReporteHCOrganizacionSegunClasificacion() throws IOException {
        Reporte unReporte = new Reporte();
        HashMap<String,Double> hasheo = unReporte.contenidoReporteHCOrganizacion(listaClasificaciones, listaDeOrganizaciones);
        System.out.println(hasheo);
        Assert.assertEquals(hasheo.size(),0);
    } // BIEN

    @Test
    public void pruebaReporteEvolucionOrganizacion() throws IOException{
        Reporte reporte = new Reporte();
        HashMap<String, Double> hash = reporte.contenidoReporteEvolucionOrganizacion(unaOrganizacion);
        System.out.println(hash);
        Assert.assertEquals(hash.size(), 0);
    } // BIEN

    @Test
    public void pruebaContenidoReporteComposicionOrganizacion(){
        Reporte unReporte = new Reporte();
        HashMap<String,Double> hasheo= unReporte.contenidoReporteComposicionOrganizacion(unaOrganizacion);
        System.out.println(hasheo);
        Assert.assertEquals(hasheo.size(),0);
    }

    @Test
    public void pruebaComposicionPais() throws IOException{ //TODO revisar
        Reporte reporte = new Reporte();
        HashMap<String, Double> hash = reporte.contenidoReporteComposicionPais(pais);
        System.out.println(hash);
        Assert.assertEquals(hash.size(), 2);
    } // BIEN

    @Test
    public void pruebaComposicionSectorTerritorial() throws IOException{
        Reporte reporte = new Reporte();
        HashMap<String, Double> hash = reporte.contenidoReporteComposicionSectorTerritorial(primerST);
        System.out.println(hash);
        Assert.assertEquals(hash.size(), 0);
    } // BIEN

    @Test
    public void pruebaEvolucionSectorTerritorial() throws IOException{
        Reporte reporte = new Reporte();
        HashMap<String, Double> hash = reporte.contenidoReporteEvolucionSectorTerritorial(primerST);
        System.out.println(hash);
        Assert.assertEquals(hash.size(), 0);
    } // BIEN

    @Test
    public void pruebaContenidoReporteHCSectorTerritorial() throws IOException{ //TODO revisar
        Reporte reporte = new Reporte();
        Double hash = reporte.contenidoReporteHCSectorTerritorial(primerST);
        System.out.println(hash);
        Assert.assertEquals(Optional.ofNullable(hash), 0.00);
    } // BIEN
}
