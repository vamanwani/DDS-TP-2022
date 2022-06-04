package domain.geodds;

import domain.services.entities.Distancia;
import domain.services.entities.ListaDeLocalidades;
import domain.ubicacion.Ubicacion;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
public class ServicioGeodds implements GeoddsServceAdapter {
    ServicioGeodds instancia=null;
    private static final String urlApi ="https://ddstpa.com.ar/api/";
    private Retrofit retrofit;

    private ServicioGeodds(){
        this.retrofit=new Retrofit.Builder().baseUrl(urlApi).addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Distancia distancia(Ubicacion origen, Ubicacion destino) throws IOException {
        GeoddsService geoddsService=this.retrofit.create(GeoddsService.class);
        Call<Distancia> requestDistancia=geoddsService.distancia(origen.idLocalidad(),
                origen.getCalle(), origen.getAltura(),
                destino.idLocalidad(),destino.getCalle(),
                destino.getAltura());
        Response<Distancia> responseDistancia=requestDistancia.execute();
        return responseDistancia.body();

    }

    public ListaDeLocalidades localidades() throws IOException {
        GeoddsService geoddsService=this.retrofit.create(GeoddsService.class);
        Call<ListaDeLocalidades> requestlocalidades=geoddsService.localidades();
        Response<ListaDeLocalidades>responselocalidades=requestlocalidades.execute();
        return responselocalidades.body();
    }
}
