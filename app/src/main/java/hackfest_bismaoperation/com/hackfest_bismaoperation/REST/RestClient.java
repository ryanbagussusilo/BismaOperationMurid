package hackfest_bismaoperation.com.hackfest_bismaoperation.REST;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;

import java.io.IOException;

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
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Query;


/**
 * Created by Ryan Bagus Susilo on 5/12/2017.
 */
public class RestClient {
    private static GitApiInterface gitApiInterface;
    private static String baseUrl = "http://bisma.bluecrawler.com" ;


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
        @Headers("Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vd3d3LmJpc21hLmJsdWVjcmF3bGVyLmNvbS9wdWJsaWMvYXV0aC9sb2dpbiIsImlhdCI6MTQ5NTMzMzgzNSwiZXhwIjoxNDk3OTI1ODM1LCJuYmYiOjE0OTUzMzM4MzUsImp0aSI6IlVDZlNhUW9qdm5YdEVmSEoiLCJzdWIiOjF9.1ekzdWOPoHA4GQGXqRaL9CopRUT77ZLtz2sCJcZA9Z4")
        @POST("/public/Murid/LoginMurid")
        Call<APIMuridData> login(@Field("username") String username, @Field("password") String password);

        @FormUrlEncoded
        @POST("/public/Murid/RegisterMurid")
        Call<APIBaseResponse> signUp(@Field("nama_depan") String nama_depan, @Field("nama_belakang") String nama_belakang, @Field("alamat") String alamat, @Field("tempat_lahir") String tempat_lahir ,
                                              @Field("telepon")String nomor_telepon, @Field("kelamin") String jenis_kelamin, @Field(" tanggal_lahir") String birthDate,
                                              @Field("email") String email, @Field("username") String username,
                                              @Field("password") String password, @Field("longitude") String longitude, @Field("latitude") String latitude,@Header("Authorization") String Authorization);


        @Headers("Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vd3d3LmJpc21hLmJsdWVjcmF3bGVyLmNvbS9wdWJsaWMvYXV0aC9sb2dpbiIsImlhdCI6MTQ5NTMzMzgzNSwiZXhwIjoxNDk3OTI1ODM1LCJuYmYiOjE0OTUzMzM4MzUsImp0aSI6IlVDZlNhUW9qdm5YdEVmSEoiLCJzdWIiOjF9.1ekzdWOPoHA4GQGXqRaL9CopRUT77ZLtz2sCJcZA9Z4")
        @GET("public/Pengajar/ListPengajar")
        Call<APIGuruData> showguru();

        @FormUrlEncoded
        @POST("/api/index.php/Murid/order")
        Call<APIOrderData> order(@Field("id_pengajar") int idpengajar, @Field("id_murid") int idmurid);

        @Headers("Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vd3d3LmJpc21hLmJsdWVjcmF3bGVyLmNvbS9wdWJsaWMvYXV0aC9sb2dpbiIsImlhdCI6MTQ5NTMzMzgzNSwiZXhwIjoxNDk3OTI1ODM1LCJuYmYiOjE0OTUzMzM4MzUsImp0aSI6IlVDZlNhUW9qdm5YdEVmSEoiLCJzdWIiOjF9.1ekzdWOPoHA4GQGXqRaL9CopRUT77ZLtz2sCJcZA9Z4")
        @GET("/public/Pengajar/ListPengajar")
        Call<APIGuruData> orderdetil(@Query("id_murid")int idmurid);



    }
}
