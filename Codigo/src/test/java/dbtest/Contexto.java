package dbtest;

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
}
