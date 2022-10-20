package dbtest;

import domain.models.entities.calculoHC.CalculdoraHCMiembro;
import domain.models.entities.miembro.Miembro;
import domain.models.entities.miembro.TipoUsuario;
import domain.models.entities.miembro.Usuario;
import domain.models.entities.organizacion.ClasificaciónDeOrg;
import domain.models.entities.organizacion.Organizacion;
import domain.models.entities.organizacion.TipoDeOrganizacion;
import domain.models.entities.sectorTerritorial.AgenteSectorial;
import domain.services.dbManager.EntityManagerHelper;
import org.junit.Test;
import org.mockito.internal.matchers.Or;

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
        organizacion.setRazonSocial("razonRandom");
        organizacion.setTipoDeOrganizacion(TipoDeOrganizacion.Empresa);
        ClasificaciónDeOrg clasificaciónDeOrg = new ClasificaciónDeOrg("clasificacion");
        organizacion.setClasificacionDeOrg(clasificaciónDeOrg);
        organizacion.setUsuario(usuarioOrg);

        Usuario usuarioAgSec = new Usuario("agsec", "agente", "agsec@gmail.com", "45062020", TipoUsuario.AGENTESECTORIAL);
        AgenteSectorial agenteSectorial = new AgenteSectorial("agente");
        agenteSectorial.setUsuario(usuarioAgSec);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(usuario);
        EntityManagerHelper.getEntityManager().persist(miembro);
        EntityManagerHelper.getEntityManager().persist(usuarioOrg);
        EntityManagerHelper.getEntityManager().persist(clasificaciónDeOrg);
        EntityManagerHelper.getEntityManager().persist(organizacion);
//        EntityManagerHelper.getEntityManager().persist(usuarioAgSec);
//        EntityManagerHelper.getEntityManager().persist(agenteSectorial);
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

}