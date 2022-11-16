package domain.models.repos;

import domain.models.entities.organizacion.Sector;
import domain.models.entities.ubicacion.Ubicacion;
import domain.services.dbManager.EntityManagerHelper;

public class RespositorioDeSectores {
    public Sector buscar(int id){
        return (Sector) EntityManagerHelper
                .getEntityManager()
                .createQuery("FROM " + Sector.class.getName() + " WHERE id = "+id)
                .getSingleResult();
    }

    public Sector buscarSegunNombre(String sector) {
        return (Sector) EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Sector.class.getName() + "where nombre = '" + sector + "'")
                .getSingleResult();
    }
}
