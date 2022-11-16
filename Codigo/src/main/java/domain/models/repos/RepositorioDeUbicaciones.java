package domain.models.repos;

import domain.models.entities.ubicacion.Ubicacion;
import domain.services.dbManager.EntityManagerHelper;

public class RepositorioDeUbicaciones {
    public Ubicacion buscar(String calle, int altura){
        return (Ubicacion) EntityManagerHelper
                .getEntityManager()
                .createQuery("FROM " + Ubicacion.class.getName() + " WHERE calle = "+calle+" AND altura = " + altura)
                .getSingleResult();
    }

    public void guardar(Ubicacion ubicacion) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(ubicacion);
        EntityManagerHelper.commit();
    }

    public void guardarSiNoExiste(Ubicacion ubic){
        try {
            Ubicacion ubicacion = this.buscar(ubic.getCalle(), ubic.getAltura());
            System.out.println("printear algo");
        } catch (Exception e){
            this.guardar(ubic);
        }
    }


}
