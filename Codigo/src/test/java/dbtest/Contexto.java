package dbtest;

import domain.models.entities.miembro.Miembro;
import domain.models.entities.miembro.TipoUsuario;
import domain.models.entities.miembro.Usuario;
import domain.models.entities.organizacion.ClasificaciónDeOrg;
import domain.models.entities.organizacion.Organizacion;
import domain.models.entities.organizacion.Sector;
import domain.models.entities.organizacion.TipoDeOrganizacion;
import domain.models.entities.sectorTerritorial.AgenteSectorial;
import domain.services.dbManager.EntityManagerHelper;
import org.junit.Test;

import static domain.services.dbManager.EntityManagerHelper.*;
import static org.junit.Assert.assertNotNull;

public class Contexto {
    @Test
    public void contextUp() {
        assertNotNull(entityManager());
    }
    @Test
    public void contextUpWithTransaction() throws Exception {
        withTransaction(() -> {});
    }
    @Test
    public void persistirUsuario() {
        Usuario usuario = new Usuario("vmanwani", "contra", "atolaba@utn.frba.utn.ar", "1112345678", TipoUsuario.MIEMBRO);
        Miembro miembro = new Miembro("Manwani", "Vignesh", "12345679", "DNI", null);
        miembro.setUsuario(usuario);

        Usuario usuarioOrg = new Usuario("org", "organizacion", "fasdf@sdfsa.com", "12345434", TipoUsuario.ORGANIZACION);
        Organizacion organizacion = new Organizacion();
        organizacion.setRazonSocial("UTN");
        organizacion.setTipoDeOrganizacion(TipoDeOrganizacion.Institucion);
        ClasificaciónDeOrg clasificaciónDeOrg = new ClasificaciónDeOrg("Educacion");
        organizacion.setClasificacionDeOrg(clasificaciónDeOrg);
        organizacion.setUsuario(usuarioOrg);

        Usuario usuarioAgSec = new Usuario("agsec", "agente", "agsec@gmail.com", "45062020", TipoUsuario.AGENTESECTORIAL);
        AgenteSectorial agenteSectorial = new AgenteSectorial("agente", usuarioAgSec);

        Sector sector1 = new Sector("RR.HH");
        Sector sector2 = new Sector("Finanzas");
        sector1.setOrganizacion(organizacion);
        sector2.setOrganizacion(organizacion);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(usuario);
        EntityManagerHelper.getEntityManager().persist(miembro);
        EntityManagerHelper.getEntityManager().persist(usuarioOrg);
        EntityManagerHelper.getEntityManager().persist(clasificaciónDeOrg);
//        EntityManagerHelper.getEntityManager().persist(rrhh);
        EntityManagerHelper.getEntityManager().persist(organizacion);
        EntityManagerHelper.getEntityManager().persist(usuarioAgSec);
        EntityManagerHelper.getEntityManager().persist(agenteSectorial);
        EntityManagerHelper.getEntityManager().persist(sector1);
        EntityManagerHelper.getEntityManager().persist(sector2);
        EntityManagerHelper.commit();

        // ahora con un miembro para probar las fk

    }

    @Test
    public void persistirOrg(){
        Organizacion organizacion = new Organizacion();
        organizacion.setTipoDeOrganizacion(TipoDeOrganizacion.Empresa);
        ClasificaciónDeOrg clasificaciónDeOrg = new ClasificaciónDeOrg("fasdfsa");
        organizacion.setClasificacionDeOrg(clasificaciónDeOrg);
        organizacion.setRazonSocial("razonSocial");
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(clasificaciónDeOrg);
        EntityManagerHelper.getEntityManager().persist(organizacion);
        EntityManagerHelper.commit();
    }

    @Test
    public void persistirOrgConSector(){
        Organizacion organizacion = new Organizacion();
        organizacion.setTipoDeOrganizacion(TipoDeOrganizacion.Empresa);
        ClasificaciónDeOrg clasificaciónDeOrg = new ClasificaciónDeOrg("Organizacion con Sector");
        organizacion.setClasificacionDeOrg(clasificaciónDeOrg);
        organizacion.setRazonSocial("razonSocial");
        Sector unSector = new Sector("sector cualquiera");
        Sector segundoSector = new Sector("sector cualquiera segundo");
        Sector tercerSector = new Sector("sector cualquiera tercero");
        unSector.setOrganizacion(organizacion);
        segundoSector.setOrganizacion(organizacion);
        tercerSector.setOrganizacion(organizacion);
        organizacion.agregarSectores(unSector);
        organizacion.agregarSectores(segundoSector);
        organizacion.agregarSectores(tercerSector);
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(clasificaciónDeOrg);
        EntityManagerHelper.getEntityManager().persist(organizacion);
        EntityManagerHelper.getEntityManager().persist(unSector);
        EntityManagerHelper.getEntityManager().persist(segundoSector);
        EntityManagerHelper.getEntityManager().persist(tercerSector);
        EntityManagerHelper.commit();
    }
//
//    @Test
//    public void persistirOrg1(){
//        Organizacion organizacion = new Organizacion();
//        organizacion.setTipoDeOrganizacion(TipoDeOrganizacion.Institucion);
//        ClasificaciónDeOrg clasificaciónDeOrg = new ClasificaciónDeOrg("Educacion");
//        organizacion.setClasificacionDeOrg(clasificaciónDeOrg);
//        organizacion.setRazonSocial("UBA");
//        Usuario usuarioOrg = new Usuario("orga", "password", "fasdf@sdfsa.com", "12345434", TipoUsuario.ORGANIZACION);
//        organizacion.setUsuario(usuarioOrg);
//        EntityManagerHelper.beginTransaction();
//        EntityManagerHelper.getEntityManager().persist(clasificaciónDeOrg);
//        EntityManagerHelper.getEntityManager().persist(organizacion);
//        EntityManagerHelper.getEntityManager().persist(usuarioOrg);
//        EntityManagerHelper.commit();
//    }


}