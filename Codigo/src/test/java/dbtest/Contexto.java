package dbtest;

import domain.miembro.Miembro;
import domain.miembro.Usuario;
import domain.services.dbManager.EntityManagerHelper;
import org.junit.Test;

import javax.persistence.Entity;

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
        //Usuario usuario = new Usuario("vmanwani", "password", "atolaba@utn.frba.utn.ar", "1112345678");
        Miembro miembro = new Miembro("Manwani", "Vignesh", 12345679, "DNI", null);

        EntityManagerHelper.beginTransaction();
        //EntityManagerHelper.getEntityManager().persist(usuario);
        EntityManagerHelper.getEntityManager().persist(miembro);
        EntityManagerHelper.commit();

        // ahora con un miembro para probar las fk





    }

}