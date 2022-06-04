package domain.geodds;

import domain.services.entities.Distancia;
import domain.services.entities.ListaDeLocalidades;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeoddsService {
    @GET("localidades")
    Call<ListaDeLocalidades> localidades();

    @GET("distancia")
    Call<Distancia> distancia(
            @Query("localidadOrigenId") int localidadOrigenId, @Query("calleOrigen") String calleOrigen,
            @Query("alturaOrigen") int alturaOrigen, @Query("localidadDestinoId") int localidadDestinoId,
            @Query("calleDestino") String calleDestino, @Query("alturaDestino") int alturaDestino
    );
}
