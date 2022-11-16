package domain.models.entities.recorridos;

import domain.models.entities.ubicacion.Ubicacion;

public interface AdapterDistancia {
    public double calculoDistancia(Ubicacion inicio, Ubicacion fin);
}
