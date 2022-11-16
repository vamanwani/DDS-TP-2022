package domain.models.repos;

import domain.models.entities.miembro.Miembro;
import domain.models.entities.recorridos.Tramo;
import domain.models.entities.recorridos.Trayecto;
import domain.models.entities.transporte.*;
import domain.models.entities.ubicacion.Ubicacion;
import domain.services.dbManager.EntityManagerHelper;

public class RepositoriosDeTransporte {

    public Transporte buscar(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Transporte.class, id);
    }

    public TransportePublico buscarTransportePublico(String linea, String tipo) {
        return (TransportePublico) EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Transporte.class.getName() + " where linea= '" + linea + "' and transporte = '" + tipo + "'")
                .getSingleResult();
    }

    public TransporteAnalogico buscarAnalogico(String transporteAnalogico) {
        return (TransporteAnalogico) EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Transporte.class.getName() + " where tipo_transporte = '" + transporteAnalogico + "'")
                .getSingleResult();
    }

    public VehiculoParticular buscarParticular(String tipoVehiculo, String tipoCombustible) {
        return (VehiculoParticular) EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Transporte.class.getName() + " where tipo_vehiculo = '" + tipoVehiculo + "' and tipo_combustible = '" + tipoCombustible +"'")
                .getSingleResult();
    }

    public ServicioContratado buscarContratado(String servicioContratado) {
        return (ServicioContratado) EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + Transporte.class.getName() + " where servicio= '" + servicioContratado + "'")
                .getSingleResult();
    }

    public void guardar(Transporte transporte) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(transporte);
        EntityManagerHelper.commit();
    }

    public void guardarSiNoExiste(Transporte transporte){
        try {
            Transporte transporteBuscado = this.buscar(Integer.valueOf(transporte.getId()));
            System.out.println("printear algo");
        } catch (Exception e){
            this.guardar(transporte);
        }
    }

    public void guardarSiNoExisteContratado(ServicioContratado transporte) {
        try {
            Transporte transporteBuscado = this.buscarContratado(transporte.getTipoServicio());
            System.out.println("printear algo");
        } catch (Exception e){
            this.guardar(transporte);
        }
    }
}
