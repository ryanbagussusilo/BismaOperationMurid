package hackfest_bismaoperation.com.hackfest_bismaoperation.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import hackfest_bismaoperation.com.hackfest_bismaoperation.Model.APIBayar;
import hackfest_bismaoperation.com.hackfest_bismaoperation.Model.APITambahOrder;
import hackfest_bismaoperation.com.hackfest_bismaoperation.Preferences.SessionManager;
import hackfest_bismaoperation.com.hackfest_bismaoperation.R;
import hackfest_bismaoperation.com.hackfest_bismaoperation.REST.RestClient;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class DetilGuruOrderWithPriceActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnbayar,btnTelp;
    boolean flag;
    private hackfest_bismaoperation.com.hackfest_bismaoperation.REST.RestClient.GitApiInterface service;
    private String namaDepan, harga, jk, namaBelakang, email, tempatlahir, tanggallahir, alamat, status, matapelajaran;
    private String nomorTlp, foto;
    private Integer idorder;

    private Call<APIBayar> callOrder;
    private String idmurid;
    private int idguru;
    LinearLayout view1;
    ListGuruActivity lgactivity;
    //ImageView img;
    private ImageView gbr;

    Bitmap bi = null;

    private int SIZE = 256;
    // Red, Green, Blue
    private int NUMBER_OF_COLOURS = 3;

    public final int RED = 0;
    public final int GREEN = 1;
    public final int BLUE = 2;

    private int[][] colourBins;
    private volatile boolean loaded = false;
    private int maxY;
    ImageView profil;
    SessionManager sessions;
    EditText txtnama, txtharga, txttlp, txttgllahir, txtstatus, txtjk, txtnamaDepan, txtEmail, tempatLahir, txtAlamat, txtId, txtMatapelajaran;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detil_guru_order_with_price);

        btnTelp=(Button) findViewById(R.id.btnTlp);
        btnbayar = (Button) findViewById(R.id.btnPay);
        txtnama = (EditText) findViewById(R.id.tv_name);
        txttlp = (EditText) findViewById(R.id.tv_phone);
        txtjk = (EditText) findViewById(R.id.tv_gender);

        txtEmail = (EditText) findViewById(R.id.tv_mail);
        txttgllahir = (EditText) findViewById(R.id.tv_dateborn);
        tempatLahir = (EditText) findViewById(R.id.tv_placeborn);
        txtAlamat = (EditText) findViewById(R.id.tv_address);
        txtMatapelajaran = (EditText) findViewById(R.id.tv_mapel);
        txtharga = (EditText) findViewById(R.id.tv_price);
        profil = (ImageView) findViewById(R.id.tv_photo);
        sessions = new SessionManager(this);
        // img=(ImageView)findViewById(R.id.img1);

//        gbr = (ImageView) findViewById(R.id.insgbr);
//        gbr.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent it = new Intent(Intent.ACTION_PICK,
//                        android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
//                startActivityForResult(it, 101);
//            }
//        });
//
//        colourBins = new int[NUMBER_OF_COLOURS][];
//        for (int i = 0; i < NUMBER_OF_COLOURS; i++) {
//            colourBins[i] = new int[SIZE];
//        }
//
//        Button upload = (Button) findViewById(R.id.btnSelect);
//        upload.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                if (flag) {
//                    view1.removeAllViews();
//                }
//                Intent it = new Intent(Intent.ACTION_PICK,
//                        android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
//                startActivityForResult(it, 101);
//                flag = true;
//
//            }
//        });
//

        btnbayar.setOnClickListener(this);
        btnTelp.setOnClickListener(this);
        Bundle b = getIntent().getExtras();
        idmurid = sessions.getUserDetails().get(SessionManager.KEY_USERID);
        idorder = b.getInt("id");
        namaDepan = b.getString("nama");
        namaBelakang = b.getString("namabelakang");
        nomorTlp = b.getString("nomortlp");
        jk = b.getString("jeniskelamin");
        alamat = b.getString("alamat");
        tanggallahir = b.getString("tanggallahir");
        tempatlahir = b.getString("tempatlahir");
        email = b.getString("email");
        status = b.getString("status");
        harga = b.getString("harga");
        foto = b.getString("profil");
        matapelajaran = b.getString("matapelajaran");

//        Toast.makeText(getBaseContext(), idguru + " Login " + Integer.parseInt(idmurid), Toast.LENGTH_LONG).show();


        txtnama.setText(namaDepan + " " + namaBelakang);
        txttlp.setText(nomorTlp);
        txtjk.setText(jk);
        txttgllahir.setText(tanggallahir);
        txtEmail.setText(email);
        txtAlamat.setText(alamat);
        tempatLahir.setText(tempatlahir);
        txtMatapelajaran.setText(matapelajaran);
        txtharga.setText(harga);
        Picasso.with(this).load(foto).into(profil);
    }

    public void onClick(View v) {
        if (v == btnbayar) {


            final ProgressDialog progressDialog = new ProgressDialog(DetilGuruOrderWithPriceActivity.this, R.style.ProgressDialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Order Guru..");
            progressDialog.show();

            // TODO: Implement your own signup logic here.


            service = RestClient.getClient();

            callOrder = service.bayar(String.valueOf(idorder));

            callOrder.enqueue(new Callback<APIBayar>() {
                @Override
                public void onResponse(Response<APIBayar> response) {
                    Log.d("Register2", "Status Code = " + response.code());
                    if (response.isSuccess()) {
                        // request successful (status code 200, 201)
                        APIBayar result = response.body();
                        Log.d("Register2", "response = " + new Gson().toJson(result));
                        if (result != null) {
                            Toast.makeText(getBaseContext(), "Berhasil Membayar Guru", Toast.LENGTH_LONG).show();
//                            progressDialog.dismiss();
                            finishAffinity();
                            Intent intent = new Intent(DetilGuruOrderWithPriceActivity.this, ListGuruActivity.class);
                            startActivity(intent);
                        }

                    } else {
                        // response received but request not successful (like 400,401,403 etc)
                        //Handle errors
                        Toast.makeText(getBaseContext(), "Gagal Memesan Guru", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    Toast.makeText(getBaseContext(), "Gagal Memesan Guru1", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }
            });


        }
        else if(v==btnTelp) {
            Intent intent = new Intent(this, PopUpOrderActivity.class);
            Bundle extras = new Bundle();
            extras.putString("nomortlp", nomorTlp);
            extras.putString("idmurid", idmurid);
            extras.putInt("id", idguru);
            intent.putExtras(extras);
            startActivity(intent);
        }


    }
}