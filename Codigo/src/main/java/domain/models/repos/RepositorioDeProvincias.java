package domain.models.repos;

import domain.models.entities.miembro.SolicitudVinculacion;
import domain.models.entities.sectorTerritorial.Provincia;
import domain.models.entities.ubicacion.Parada;
import domain.services.dbManager.EntityManagerHelper;

public class RepositorioDeProvincias {

    public Provincia buscarSegunNombre(String nombre){
        return (Provincia) EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Provincia.class.getName() + " where nombre = '" + nombre + "'")
                .getSingleResult();
    }

    public Provincia buscar(Integer id) {
        return (Provincia) EntityManagerHelper
                .getEntityManager()
                .find(Provincia.class, id);
    }

    public RepositorioDeProvincias() {
    }
}
