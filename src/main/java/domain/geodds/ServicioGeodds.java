package domain.geodds;

import domain.services.entities.Distancia;
import domain.services.entities.ListaDeLocalidades;
import domain.services.entities.Localidad;
import domain.ubicacion.Ubicacion;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

public class ServicioGeodds implements GeoddsServceAdapter {
    private static ServicioGeodds instancia=null;
    private static final String urlApi ="https://ddstpa.com.ar/api/";
    private Retrofit retrofit;
    private OkHttpClient cliente;


    public ServicioGeodds(){
        this.retrofit=new Retrofit.Builder().baseUrl(urlApi).addConverterFactory(GsonConverterFactory.create())

                .build();
    }

    public static ServicioGeodds getInstance(){
        if(instancia == null){
            instancia = new ServicioGeodds();
        }
        return instancia;
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

    public List<Localidad> localidades() throws IOException {
        GeoddsService geoddsService=this.retrofit.create(GeoddsService.class);
        Call<List<Localidad>> requestlocalidades=geoddsService.localidades(
                "application/json",
                "Bearer DJ0KuKA+zEOH4bC9lezcCCLS4o5E5zvnJdgoyn2tkE0=",
                1,1);
        Response<List<Localidad>> responselocalidades=requestlocalidades.execute();
        return responselocalidades.body();
    }

    public int localidadesRespuesta() throws IOException{
        GeoddsService geoddsService=this.retrofit.create(GeoddsService.class);
        Call<List<Localidad>> requestlocalidades=geoddsService.localidades(
                "application/json",
                "Bearer DJ0KuKA+zEOH4bC9lezcCCLS4o5E5zvnJdgoyn2tkE0=",
                1,1);
        Response<List<Localidad>> responselocalidades=requestlocalidades.execute();
        return responselocalidades.code();
    }
}
