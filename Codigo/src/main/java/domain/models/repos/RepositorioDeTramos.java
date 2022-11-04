package domain.models.repos;

import domain.models.entities.miembro.Usuario;
import domain.models.entities.recorridos.Tramo;
import domain.models.entities.ubicacion.Ubicacion;
import domain.services.dbManager.EntityManagerHelper;

public class RepositorioDeTramos {
    public Tramo buscar(Integer id){
        return (Tramo) EntityManagerHelper
                .getEntityManager()
                .createQuery("FROM " + Tramo.class.getName() + " WHERE tramo_id = '"+id)
                .getSingleResult();
    }

    public void guardar(Tramo tramo) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(tramo);
        EntityManagerHelper.commit();
    }

    public void guardarSiNoExiste(Tramo tramo){
        try {
            Tramo tramoBuscado = this.buscar(tramo.getId());
            System.out.println("printear algo");
        } catch (Exception e){
            this.guardar(tramo);
        }
    }
}
