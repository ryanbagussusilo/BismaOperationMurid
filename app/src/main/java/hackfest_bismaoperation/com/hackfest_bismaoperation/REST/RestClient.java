package hackfest_bismaoperation.com.hackfest_bismaoperation.REST;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;

import java.io.IOException;

import hackfest_bismaoperation.com.hackfest_bismaoperation.Helper.ToStringConverter;
import hackfest_bismaoperation.com.hackfest_bismaoperation.Model.APIBaseResponse;
import hackfest_bismaoperation.com.hackfest_bismaoperation.Model.APIBayar;
import hackfest_bismaoperation.com.hackfest_bismaoperation.Model.APIDealOrder;
import hackfest_bismaoperation.com.hackfest_bismaoperation.Model.APIGuruData;
import hackfest_bismaoperation.com.hackfest_bismaoperation.Model.APIMuridData;
import hackfest_bismaoperation.com.hackfest_bismaoperation.Model.APIOrderListPengajar;
import hackfest_bismaoperation.com.hackfest_bismaoperation.Model.APITambahOrder;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;


/**
 * Created by Ryan Bagus Susilo on 5/12/2017.
 */
public class RestClient {
    private static GitApiInterface gitApiInterface;
//    private static String baseUrl = "http://bisma.bluecrawler.com" ;
    private static String baseUrl = "http://api.bismaoperation.id" ;
    private static final String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vd3d3LmFwaS5iaXNtYW9wZXJhdGlvbi5pZC9wdWJsaWMvYXV0aC9sb2dpbiIsImlhdCI6MTQ5NTc0MzQ5MiwiZXhwIjoxNTI2ODQ3NDkyLCJuYmYiOjE0OTU3NDM0OTIsImp0aSI6InZBSWVCdHFGNnA4YlNZRlciLCJzdWIiOjE0fQ.A3IbdRAoJy7lsYzTWhn4jKjvQ0-g1T-B5ij7qivGL9Q";


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
        @Headers("Authorization: "+token)
        @POST("/public/Murid/LoginMurid")
        Call<APIMuridData> login(@Field("username") String username, @Field("password") String password);

        @FormUrlEncoded
        @Headers("Authorization: "+token)
        @POST("/public/Murid/RegisterMurid")
        Call<APIBaseResponse> signUp(@Field("nama_depan") String nama_depan, @Field("nama_belakang") String nama_belakang, @Field("alamat") String alamat, @Field("tempat_lahir") String tempat_lahir ,
                                              @Field("telepon")String nomor_telepon, @Field("kelamin") String jenis_kelamin, @Field("tanggal_lahir") String birthDate,
                                              @Field("email") String email, @Field("username") String username,
                                              @Field("password") String password, @Field("longitude") String longitude, @Field("latitude") String latitude);


        @Headers("Authorization: "+token)
        @GET("public/Pengajar/ListPengajar")
        Call<APIGuruData> showguru();

        @FormUrlEncoded
        @Headers("Authorization: "+token)
        @POST("/public/Order/TambahOrder")
        Call<APITambahOrder> order(@Field("id_pengajar") String idpengajar, @Field("id_murid") String idmurid);

//        @Headers("Authorization: "+token)
//        @GET("/public/Order/ListPengajar")
//        Call<APIOrderData> orderdetil(@Query("id_murid")int idmurid);

        @Headers("Authorization: "+token)
        @GET("/public/Order/ListPengajar")
        Call<APIOrderListPengajar> orderdetil(@Query("id_murid") int idmurid);

        @FormUrlEncoded
        @Headers("Authorization: "+token)
        @POST("public/Order/Pembayaran")
        Call<APIBayar> bayar(@Field("id_order") String idorder);

        @FormUrlEncoded
        @Headers("Authorization: "+token)
        @POST("public/Order/Deal")
        Call<APIDealOrder> dealorder(@Field("id_order") String idorder);

    }
}
