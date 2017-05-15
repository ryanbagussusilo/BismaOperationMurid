package hackfest_bismaoperation.com.hackfest_bismaoperation.REST;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Date;

import hackfest_bismaoperation.com.hackfest_bismaoperation.Helper.ToStringConverter;
import hackfest_bismaoperation.com.hackfest_bismaoperation.Model.APIBaseResponse;
import hackfest_bismaoperation.com.hackfest_bismaoperation.Model.APIGuruData;
import hackfest_bismaoperation.com.hackfest_bismaoperation.Model.APIMuridData;
import hackfest_bismaoperation.com.hackfest_bismaoperation.Model.APIOrderData;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;


/**
 * Created by Ryan Bagus Susilo on 5/12/2017.
 */
public class RestClient {
    private static GitApiInterface gitApiInterface;
    private static String baseUrl = "http://bisma.pe.hu" ;


    public static GitApiInterface getClient(){
        if(gitApiInterface==null){

            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

            OkHttpClient okClient = new OkHttpClient();
            okClient.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Response response = chain.proceed(chain.request());
                    return response;
                }
            });

            Retrofit client = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverter(String.class, new ToStringConverter())
                    .client(okClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            gitApiInterface = client.create(GitApiInterface.class);

        }
        return gitApiInterface;
    }

    public interface GitApiInterface {

        @FormUrlEncoded
        @POST("/api/index.php/Murid/login")
        Call<APIMuridData> login(@Field("username") String username, @Field("password") String password);

        @FormUrlEncoded
        @POST("/api/index.php/Murid/signup")
        Call<APIBaseResponse> signUp(@Field("nama_depan") String nama_depan, @Field("nama_belakang") String nama_belakang, @Field("alamat") String alamat, @Field("tempat_lahir") String tempat_lahir ,
                                              @Field("nomor_telepon")String nomor_telepon, @Field("jenis_kelamin") String jenis_kelamin, @Field(" tanggal_lahir") String birthDate,
                                              @Field("email") String email, @Field("username") String username,
                                              @Field("password") String password, @Field("longitude") String longitude, @Field("latitude") String latitude);


        @Headers("Cache-Control: no-cache")
        @GET("/api/index.php/Pengajar/show")
        Call<APIGuruData> showguru();

        @FormUrlEncoded
        @POST("/api/index.php/Murid/order")
        Call<APIOrderData> order(@Field("id_pengajar") int idpengajar, @Field("id_murid") int idmurid);


    }
}
