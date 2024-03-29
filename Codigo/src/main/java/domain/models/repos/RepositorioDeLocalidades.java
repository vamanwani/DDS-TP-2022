package domain.models.repos;

import domain.models.entities.organizacion.Organizacion;
import domain.models.entities.sectorTerritorial.Localidad;
import domain.models.entities.sectorTerritorial.Provincia;
import domain.services.dbManager.EntityManagerHelper;

import java.util.List;

public class RepositorioDeLocalidades {
    public Localidad buscar(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Localidad.class, id);
    }

    public Localidad buscarNombre(String nombre){
        return (Localidad) EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Localidad.class.getName() + " where nombre = '" + nombre + "'")
                .getSingleResult();
    }

    public Provincia buscarProvNombre(String nombre){
        return (Provincia) EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Provincia.class.getName() + " where nombre = '" + nombre + "'")
                .getSingleResult();
    }

    public List<Localidad> retornarLocalidades(){
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from "+ Localidad.class.getName())
                .getResultList();
    }

    public List<Provincia> retornarProvincias(){
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from "+ Provincia.class.getName())
                .getResultList();
    }


}
