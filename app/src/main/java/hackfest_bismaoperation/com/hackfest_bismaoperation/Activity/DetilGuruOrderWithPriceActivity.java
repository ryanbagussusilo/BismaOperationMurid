package hackfest_bismaoperation.com.hackfest_bismaoperation.Activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
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
import hackfest_bismaoperation.com.hackfest_bismaoperation.Model.APICancelOrder;
import hackfest_bismaoperation.com.hackfest_bismaoperation.Model.APIDealOrder;
import hackfest_bismaoperation.com.hackfest_bismaoperation.Model.APITambahOrder;
import hackfest_bismaoperation.com.hackfest_bismaoperation.Preferences.SessionManager;
import hackfest_bismaoperation.com.hackfest_bismaoperation.R;
import hackfest_bismaoperation.com.hackfest_bismaoperation.REST.RestClient;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class DetilGuruOrderWithPriceActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnbayar,btnTelp,btnBtl;
    boolean flag;
    private hackfest_bismaoperation.com.hackfest_bismaoperation.REST.RestClient.GitApiInterface service;
    private String namaDepan, harga, jk, namaBelakang, email, tempatlahir, tanggallahir, alamat, status, matapelajaran;
    private String nomorTlp, foto,totalharga,jambelajar,tanggalorder,statusorder;
    private Integer idorder;

    private Call<APIBayar> callOrder;
    private Call<APIDealOrder> callOrderDeal;
    private Call<APICancelOrder> callCancel;
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
    EditText txtTanggalOrder,txtTotalHarga,txttotaljam,txtnama, txtharga, txttlp, txttgllahir, txtstatus, txtjk, txtnamaDepan, txtEmail, tempatLahir, txtAlamat, txtId, txtMatapelajaran;

    Handler handler = new Handler();
    Runnable refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detil_guru_order_with_price);

        btnTelp=(Button) findViewById(R.id.btnTlp);
        btnBtl=(Button) findViewById(R.id.btnBatal);
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
        txtTotalHarga=(EditText) findViewById(R.id.tv_totalor);
        txttotaljam=(EditText) findViewById(R.id.tv_jamor);
        txtTanggalOrder=(EditText) findViewById(R.id.tv_tanggalorderor);

        profil = (ImageView) findViewById(R.id.tv_photo);
        sessions = new SessionManager(this);


        btnbayar.setOnClickListener(this);
        btnBtl.setOnClickListener(this);
        btnTelp.setOnClickListener(this);
        Bundle b = getIntent().getExtras();
        idmurid = sessions.getUserDetails().get(SessionManager.KEY_USERID);
        idorder = b.getInt("id");
        namaDepan = b.getString("nama");
        namaBelakang = b.getString("namabelakang");
        nomorTlp = b.getString("nomortlp");
        jk = b.getString("jeniskelamin");
        alamat = b.getString("alamat");
        tanggalorder=b.getString("tanggalorder");
        tanggallahir = b.getString("tanggallahir");
        tempatlahir = b.getString("tempatlahir");
        email = b.getString("email");
        status = b.getString("status");
        harga = b.getString("harga");
        foto = b.getString("profil");
        matapelajaran = b.getString("matapelajaran");
        jambelajar=b.getString("jambelajar");
        totalharga=b.getString("totalharga");
        statusorder=b.getString("statusorder");


        if(statusorder.equalsIgnoreCase("Order")){
            btnbayar.setText("Deal");
            btnbayar.setBackground(this.getResources().getDrawable(R.drawable.deal2));
        }else if(statusorder.equalsIgnoreCase("Verifikasi")){
            btnbayar.setEnabled(true);
            btnbayar.setBackground(this.getResources().getDrawable(R.drawable.deal2));
            btnbayar.setText("Deal");

            String tittle="Pesan Baru";
            String subject="Bisma Operation";
            String body="Order telah di Verifikasi";

            NotificationManager notif=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            Notification notify=new Notification.Builder
                    (getApplicationContext()).setContentTitle(tittle).setContentText(body).
                    setContentTitle(subject).setSmallIcon(R.mipmap.bisma1).build();

            notify.flags |= Notification.FLAG_AUTO_CANCEL;
            notif.notify(0, notify);

        }else if(statusorder.equalsIgnoreCase("Deal")){
            btnbayar.setText("Bayar");
            btnbayar.setBackground(this.getResources().getDrawable(R.drawable.bayar));
            btnBtl.setEnabled(false);
        }



        txtnama.setText(namaDepan + " " + namaBelakang);
        txttlp.setText(nomorTlp);
        txtjk.setText(jk);
        txttgllahir.setText(tanggallahir);
        txtEmail.setText(email);
        txtAlamat.setText(alamat);
        tempatLahir.setText(tempatlahir);
        txtMatapelajaran.setText(matapelajaran);
        txtharga.setText(harga);
        txtTotalHarga.setText(totalharga);
        txttotaljam.setText(jambelajar);
        txtTanggalOrder.setText(tanggalorder);
        Picasso.with(this).load(foto).into(profil);


    }

    @Override

    public void onClick(View v) {
        if (v == btnbayar) {
            if(txttotaljam.getText().toString().equalsIgnoreCase("null"))
            {
                Toast.makeText(getBaseContext(), "Tunggu Guru Memverifikasi jumlah jam", Toast.LENGTH_LONG).show();

            }
            if(btnbayar.getText().toString().equalsIgnoreCase("Bayar")) {
                btnBtl.setEnabled(false);
                final ProgressDialog progressDialog = new ProgressDialog(DetilGuruOrderWithPriceActivity.this, R.style.ProgressDialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Membayar Guru..");
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
                                progressDialog.dismiss();
                                Intent intent = new Intent(DetilGuruOrderWithPriceActivity.this, ListOrderActivity.class);
                                startActivity(intent);
                            }

                        } else {
                            // response received but request not successful (like 400,401,403 etc)
                            //Handle errors
                            Toast.makeText(getBaseContext(), "Gagal Membayar Guru", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                            Intent intent = new Intent(DetilGuruOrderWithPriceActivity.this, ListOrderActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Toast.makeText(getBaseContext(), "Berhasil Membayar Guru", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                });

            }
            else if(btnbayar.getText().toString().equalsIgnoreCase("Deal")&&!txttotaljam.getText().toString().equalsIgnoreCase("null")){

                final ProgressDialog progressDialog = new ProgressDialog(DetilGuruOrderWithPriceActivity.this, R.style.ProgressDialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Deal Guru..");
                progressDialog.show();

                // TODO: Implement your own signup logic here.

                service = RestClient.getClient();

                callOrderDeal = service.dealorder(String.valueOf(idorder));
                callOrderDeal.enqueue(new Callback<APIDealOrder>() {
                    @Override
                    public void onResponse(Response<APIDealOrder> response) {
                        Log.d("Register2", "Status Code = " + response.code());
                        if (response.isSuccess()) {
                            // request successful (status code 200, 201)
                            APIDealOrder result = response.body();
                            Log.d("Register2", "response = " + new Gson().toJson(result));
                            if (result != null) {
                                Toast.makeText(getBaseContext(), "Berhasil Deal untuk Memesan Guru Silahkan tekan bayar untuk membayar Guru", Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();
                             //   finishAffinity();
                               // Intent intent = new Intent(DetilGuruOrderWithPriceActivity.this ,ListGuruActivity.class);
                                btnbayar.setText("Bayar");
                              //  startActivity(intent);
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
                        Toast.makeText(getBaseContext(), "Gagal Memesan Guru", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                });
            }
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
        else if(v==btnBtl){

            final ProgressDialog progressDialog = new ProgressDialog(DetilGuruOrderWithPriceActivity.this, R.style.ProgressDialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Membatalkan Pesanan Guru..");
            progressDialog.show();

            // TODO: Implement your own signup logic here.

            service = RestClient.getClient();

            callCancel = service.batal(String.valueOf(idorder));

            callCancel.enqueue(new Callback<APICancelOrder>() {
                @Override
                public void onResponse(Response<APICancelOrder> response) {
                    Log.d("Register2", "Status Code = " + response.code());
                    if (response.isSuccess()) {
                        // request successful (status code 200, 201)
                        APICancelOrder result = response.body();
                        Log.d("Register2", "response = " + new Gson().toJson(result));
                        if (result != null) {
                            Toast.makeText(getBaseContext(), "Berhasil Membatalkan Order", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                            finish();
                            startActivity(getIntent());
                            Intent intent = new Intent(DetilGuruOrderWithPriceActivity.this, ListOrderActivity.class);
                            startActivity(intent);
                        }

                    } else {
                        // response received but request not successful (like 400,401,403 etc)
                        //Handle errors
                        Toast.makeText(getBaseContext(), "Gagal Membatalkan Order", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    Toast.makeText(getBaseContext(), "Berhasil Membatalkan Order", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }
            });

        }


    }
}