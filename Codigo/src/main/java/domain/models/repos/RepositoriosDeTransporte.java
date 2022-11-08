package domain.models.repos;

import domain.models.entities.miembro.Miembro;
import domain.models.entities.transporte.ServicioContratado;
import domain.models.entities.transporte.TransporteAnalogico;
import domain.models.entities.transporte.TransportePublico;
import domain.models.entities.transporte.VehiculoParticular;
import domain.services.dbManager.EntityManagerHelper;

public class RepositoriosDeTransporte {
    public TransportePublico buscarTransportePublico(String linea, String tipo) {
        return (TransportePublico) EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + TransportePublico.class.getName() + " where linea=" + linea + " and transporte = " + tipo)
                .getSingleResult();
    }

    public TransporteAnalogico buscarAnalogico(String transporteAnalogico) {
        return (TransporteAnalogico) EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + TransporteAnalogico.class.getName() + " where tipo_transporte = " + transporteAnalogico)
                .getSingleResult();
    }

    public VehiculoParticular buscarParticular(String tipoVehiculo, String tipoCombustible) {
        return (VehiculoParticular) EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + VehiculoParticular.class.getName() + " where tipo_vehiculo=" + tipoVehiculo + " and tipo_combustible = " + tipoCombustible)
                .getSingleResult();
    }

    public ServicioContratado buscarContratado(String servicioContratado) {
        return (ServicioContratado) EntityManagerHelper
                .getEntityManager()
                .createQuery("from " + ServicioContratado.class.getName() + " where servicio=" + servicioContratado)
                .getSingleResult();
    }
}
