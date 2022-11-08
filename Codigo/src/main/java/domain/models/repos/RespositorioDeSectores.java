package domain.models.repos;

import domain.models.entities.organizacion.Sector;
import domain.models.entities.ubicacion.Ubicacion;
import domain.services.dbManager.EntityManagerHelper;

public class RespositorioDeSectores {
    public Sector buscar(String id){
        return (Sector) EntityManagerHelper
                .getEntityManager()
                .createQuery("FROM " + Sector.class.getName() + " WHERE id = "+id)
                .getSingleResult();
    }
}
