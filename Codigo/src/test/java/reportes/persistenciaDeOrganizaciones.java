package reportes;

import domain.services.dbManager.EntityManagerHelper;
import org.junit.Test;

public class persistenciaDeOrganizaciones extends ReportesInit{
    @Test
    public void persistir(){
        EntityManagerHelper.beginTransaction();
        //EntityManagerHelper.getEntityManager().persist(clasificaciónDeOrg1);
        //EntityManagerHelper.getEntityManager().persist(clasificaciónDeOrg2);
        EntityManagerHelper.getEntityManager().persist(unaOrganizacion);
        EntityManagerHelper.getEntityManager().persist(otraOrganizacion);
        EntityManagerHelper.getEntityManager().persist(primerTipoConsumo);
        EntityManagerHelper.getEntityManager().persist(primerConsumo);
        EntityManagerHelper.getEntityManager().persist(primerUsuario);
        EntityManagerHelper.getEntityManager().persist(primerMiembro);
        EntityManagerHelper.getEntityManager().persist(segundoTipoConsumo);
        //EntityManagerHelper.getEntityManager().persist(segundoConsumo);
        EntityManagerHelper.getEntityManager().persist(segundoUsuario);
        EntityManagerHelper.getEntityManager().persist(segundoMiembro);
        EntityManagerHelper.getEntityManager().persist(tercerTipoConsumo);
        //EntityManagerHelper.getEntityManager().persist(tercerConsumo);
        EntityManagerHelper.getEntityManager().persist(tercerUsuario);
        EntityManagerHelper.getEntityManager().persist(tercerMiembro);
        //EntityManagerHelper.getEntityManager().persist(primerSector);
        //EntityManagerHelper.getEntityManager().persist(segundoSector);
        //EntityManagerHelper.getEntityManager().persist(tercerSector);
        //EntityManagerHelper.getEntityManager().persist(primerST);
        //EntityManagerHelper.getEntityManager().persist(segundoST);
        EntityManagerHelper.commit();
    }
}
