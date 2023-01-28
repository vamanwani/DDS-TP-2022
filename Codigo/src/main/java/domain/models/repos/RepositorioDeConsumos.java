package domain.models.repos;

import domain.models.entities.consumo.Actividad;
import domain.models.entities.consumo.Consumo;
import domain.models.entities.consumo.PeriodoDeImputacion;
import domain.models.entities.consumo.TipoConsumo;
import domain.models.entities.organizacion.Organizacion;
import domain.services.dbManager.EntityManagerHelper;

import java.util.List;

public class RepositorioDeConsumos {
    public Consumo buscar(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Consumo.class, id);
    }

    public void guardar(Consumo consumo) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(consumo);
        EntityManagerHelper.commit();
    }

    public void guardarTipoConsumo(TipoConsumo consumo) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(consumo);
        EntityManagerHelper.commit();
    }

    public void guardarActividad(Actividad consumo) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(consumo);
        EntityManagerHelper.commit();
    }

    public void guardarPeriodo(PeriodoDeImputacion consumo) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(consumo);
        EntityManagerHelper.commit();
    }


    public TipoConsumo buscarTipoConsumo(TipoConsumo tipoConsumo){
        return (TipoConsumo) EntityManagerHelper
                .getEntityManager()
                .createQuery("from "+ TipoConsumo.class.getName() +" where nombre = '" + tipoConsumo.getNombre() + "'")
                .getSingleResult();
    }

    public Actividad buscarActividad(Actividad actividadConsumoString) {
        return (Actividad) EntityManagerHelper
                .getEntityManager()
                .createQuery("from "+ Actividad.class.getName() +" where tipoActividad = '" + actividadConsumoString.getNombre() + "'")
                .getSingleResult();
    }

    public PeriodoDeImputacion buscarPeriodo(PeriodoDeImputacion periodoDeImputacion){
        return (PeriodoDeImputacion) EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + PeriodoDeImputacion.class.getName()
                        +  " where anio = " + periodoDeImputacion.getAnio()
                        + " and mes = " + periodoDeImputacion.getMes()
                        + " and periodicidad = " + periodoDeImputacion.getPeriodicidad())
                .getSingleResult();
    }

    public List<Consumo> buscarConsumosDeOrg(Organizacion organizacion){
        return EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Consumo.class.getName() + " where organizacion_id = " + organizacion.getId())
                .getResultList();
    }


    public void guardarSiNoExisteTipoConsumo(TipoConsumo tipoConsumo){
        try {
            TipoConsumo tipoConsumo1 = this.buscarTipoConsumo(tipoConsumo);
        } catch (Exception e){
            this.guardarTipoConsumo(tipoConsumo);
        }
    }

    public void guardarSiNoExisteActividad(Actividad actividad){
        try {
            Actividad actividad1 = this.buscarActividad(actividad);
        } catch (Exception e){
            this.guardarActividad(actividad);
        }
    }

    public void guardarSiNoExistePeriodicidad(PeriodoDeImputacion periodoDeImputacion){
        try {
            PeriodoDeImputacion periodo = this.buscarPeriodo(periodoDeImputacion);
        } catch (Exception e){
            this.guardarPeriodo(periodoDeImputacion);
        }
    }


}
