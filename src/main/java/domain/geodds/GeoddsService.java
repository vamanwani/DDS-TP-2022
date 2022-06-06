package domain.geodds;

import domain.services.entities.Distancia;
import domain.services.entities.ListaDeLocalidades;
import domain.services.entities.Localidad;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

import java.util.List;

public interface GeoddsService {

    @GET("localidades")
    Call<List<Localidad>> localidades(
            //@Header("accept") String jsonAccept,
            @Header("Authorization") String token,
            @Query("offset") int offset, @Query("municipioId") int idMunicipio

    );

    @GET("distancia")
    Call<Distancia> distancia(
            @Header("Authorization") String token,
            @Query("localidadOrigenId") int localidadOrigenId, @Query("calleOrigen") String calleOrigen,
            @Query("alturaOrigen") int alturaOrigen, @Query("localidadDestinoId") int localidadDestinoId,
            @Query("calleDestino") String calleDestino, @Query("alturaDestino") int alturaDestino
    );
}
