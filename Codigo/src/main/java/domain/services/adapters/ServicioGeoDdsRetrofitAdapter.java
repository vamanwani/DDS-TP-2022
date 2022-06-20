package domain.services.adapters;

import domain.services.entities.Distancia;
import domain.services.entities.Localidad;
import domain.ubicacion.Ubicacion;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;


public class ServicioGeoDdsRetrofitAdapter implements  GeoddsServceAdapter{
    private static ServicioGeoDdsRetrofitAdapter instancia=null;
    private static String llaveAutorizacion;
    private static final String urlApi ="https://ddstpa.com.ar/api/";
    private Retrofit retrofit;
    //private OkHttpClient cliente;

    public static void setLlaveAutorizacion(String llaveAutorizacion) {
        ServicioGeoDdsRetrofitAdapter.llaveAutorizacion = llaveAutorizacion;
    }

    public static String getLlaveAutorizacion() {
        return llaveAutorizacion;
    }

    public ServicioGeoDdsRetrofitAdapter() throws IOException {
        tokenDeAutorizacion();
        this.retrofit = new Retrofit.Builder()
                .baseUrl(urlApi)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ServicioGeoDdsRetrofitAdapter getInstance() throws IOException {
        if(instancia == null){
            instancia = new ServicioGeoDdsRetrofitAdapter();
        }
        return instancia;
    }
    public Distancia distancia(Ubicacion origen, Ubicacion destino) throws IOException {
        GeoddsService geoddsService=this.retrofit.create(GeoddsService.class);
        Call<Distancia> requestDistancia=geoddsService.distancia(
                llaveAutorizacion,
                origen.idLocalidad(),
                origen.getCalle(), origen.getAltura(),
                destino.idLocalidad(),destino.getCalle(),
                destino.getAltura());
        Response<Distancia> responseDistancia=requestDistancia.execute();

        return responseDistancia.body();
    }
    public int distanciaRespuesta(Ubicacion origen, Ubicacion destino) throws IOException {
        GeoddsService geoddsService=this.retrofit.create(GeoddsService.class);
        Call<Distancia> requestDistancia=geoddsService.distancia(
                llaveAutorizacion,
                origen.idLocalidad(),
                origen.getCalle(), origen.getAltura(),
                destino.idLocalidad(),destino.getCalle(),
                destino.getAltura());
        Response<Distancia> responseDistancia=requestDistancia.execute();
        return responseDistancia.code();
    }

    private void tokenDeAutorizacion() throws IOException {
        File tokenArchivo = new File("target/classes/tokenAutorizacion.txt");
        BufferedReader tokenLinea = new BufferedReader(new FileReader(tokenArchivo));
        setLlaveAutorizacion("Bearer " + tokenLinea.readLine());
    }

    public List<Localidad> localidades() throws IOException {
        GeoddsService geoddsService=this.retrofit.create(GeoddsService.class);
        Call<List<Localidad>> requestlocalidades=geoddsService.localidades(
                //"application/json",
                llaveAutorizacion,
                1,241);
        Response<List<Localidad>> responselocalidades=requestlocalidades.execute();
        return responselocalidades.body();
    }

    public int localidadesRespuesta() throws IOException{
        GeoddsService geoddsService=this.retrofit.create(GeoddsService.class);
        Call<List<Localidad>> requestlocalidades=geoddsService.localidades(
                //"application/json",
                llaveAutorizacion,
                1,241);
        Response<List<Localidad>> responselocalidades=requestlocalidades.execute();
        return responselocalidades.code();
    }


}
