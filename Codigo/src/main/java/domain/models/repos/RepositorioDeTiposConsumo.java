package domain.models.repos;

import domain.models.entities.consumo.TipoConsumo;
import domain.models.entities.recorridos.Tramo;
import domain.services.dbManager.EntityManagerHelper;

import java.util.List;

public class RepositorioDeTiposConsumo {
    public TipoConsumo buscar(Integer id){
        return (TipoConsumo) EntityManagerHelper
                .getEntityManager()
                .createQuery("FROM " + TipoConsumo.class.getName() + " WHERE id = "+id)
                .getSingleResult();
    }

    public void guardar(TipoConsumo tipoConsumo) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(tipoConsumo);
        EntityManagerHelper.commit();
    }

    public List<TipoConsumo> buscarTodos() {
        return EntityManagerHelper.getEntityManager()
                .createQuery("from " + TipoConsumo.class.getName())
                .getResultList();
    }

    public TipoConsumo buscarLogistica() {
        return (TipoConsumo) EntityManagerHelper.getEntityManager()
                .createQuery("from " + TipoConsumo.class.getName() + " where nombre = 'LOGISITICA'")
                .getSingleResult();

    }
}
