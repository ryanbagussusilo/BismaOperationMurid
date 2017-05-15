package hackfest_bismaoperation.com.hackfest_bismaoperation.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.io.IOException;

import hackfest_bismaoperation.com.hackfest_bismaoperation.Model.APIBaseResponse;
import hackfest_bismaoperation.com.hackfest_bismaoperation.Model.APIMuridData;
import hackfest_bismaoperation.com.hackfest_bismaoperation.Model.APIOrderData;
import hackfest_bismaoperation.com.hackfest_bismaoperation.R;
import hackfest_bismaoperation.com.hackfest_bismaoperation.Activity.ListGuruActivity;
import hackfest_bismaoperation.com.hackfest_bismaoperation.REST.RestClient;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;


public class DetilGuruActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnorder;
    boolean flag;
    private hackfest_bismaoperation.com.hackfest_bismaoperation.REST.RestClient.GitApiInterface service;
    private String namaDepan,harga, jk, namaBelakang, email, tempatlahir, tanggallahir, alamat,status;
    private String nomorTlp;

    private Call<APIOrderData> callOrder;
    private int idmurid, idguru;
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

    TextView txtnama,txtharga, txttlp, txttgllahir, txtstatus, txtjk, txtnamaDepan, txtEmail, tempatLahir, txtAlamat, txtId,txtMatapelajaran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detil_guru);
        btnorder = (Button) findViewById(R.id.btnOrder);
        txtnama = (TextView) findViewById(R.id.txtnama);
        txttlp = (TextView) findViewById(R.id.txtTelefon);
        txtjk = (TextView) findViewById(R.id.txtJenisKelamin);
        //txtnamaDepan=(TextView)findViewById(R.id.txtnam)
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txttgllahir = (TextView) findViewById(R.id.txtTanggalLahir);
        tempatLahir = (TextView) findViewById(R.id.txtTempatLahir);
        txtAlamat = (TextView) findViewById(R.id.txtAlamat);
        txtMatapelajaran=(TextView) findViewById(R.id.txtMataPelajaran);
        txtharga=(TextView) findViewById(R.id.txtHarga);


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

        btnorder.setOnClickListener(this);
        Bundle b = getIntent().getExtras();
        idguru = b.getInt("id");
        namaDepan = b.getString("nama");
        namaBelakang = b.getString("namabelakang");
        nomorTlp = b.getString("nomortlp");
        jk = b.getString("jeniskelamin");
        alamat = b.getString("alamat");
        tanggallahir = b.getString("tanggallahir");
        tempatlahir = b.getString("tempatlahir");
        email = b.getString("email");
        status=b.getString("status");
        harga=b.getString("harga");


        txtnama.setText(namaDepan + " " + namaBelakang);
        txttlp.setText(nomorTlp);
        txtjk.setText(jk);
        txttgllahir.setText(tanggallahir);
        txtEmail.setText(email);
        txtAlamat.setText(alamat);
        tempatLahir.setText(tempatlahir);
        txtMatapelajaran.setText(status);
        txtharga.setText(harga);

    }

    public void onClick(View v) {
        if (v == btnorder) {


//            final ProgressDialog progressDialog = new ProgressDialog(DetilGuruActivity.this, R.style.ProgressDialog);
//            progressDialog.setIndeterminate(true);
//            progressDialog.setMessage("Order Guru..");
//            progressDialog.show();
//
//            // TODO: Implement your own signup logic here.
//            int idPengajarS=idguru;
//            int idMuridS=lgactivity.getIdmurid1();
//
//
//            service = RestClient.getClient();
//
//            callOrder = service.order(idPengajarS,idMuridS);
//            callOrder.enqueue(new Callback<APIOrderData>() {
//                @Override
//                public void onResponse(Response<APIOrderData> response) {
//                    Log.d("Register2", "Status Code = " + response.code());
//                    if (response.isSuccess()) {
//                        // request successful (status code 200, 201)
//                        APIOrderData result = response.body();
//                        Log.d("Register2", "response = " + new Gson().toJson(result));
//                        if (result != null) {
//                            Toast.makeText(getBaseContext(), "Berhasil Memesan Guru", Toast.LENGTH_LONG).show();
//                            progressDialog.dismiss();
//                            finishAffinity();
//                            Intent intent = new Intent(DetilGuruActivity.this ,ListGuruActivity.class);
//                            startActivity(intent);
//                        }
//
//                    } else {
//                        // response received but request not successful (like 400,401,403 etc)
//                        //Handle errors
//                        Toast.makeText(getBaseContext(), "Gagal Memesan Guru", Toast.LENGTH_LONG).show();
//                        progressDialog.dismiss();
//                    }
//                }
//
//                @Override
//                public void onFailure(Throwable t) {
//                    Toast.makeText(getBaseContext(), "Gagal Memesan Guru", Toast.LENGTH_LONG).show();
//                    progressDialog.dismiss();
//                }
//            });
//        }


            Intent intent = new Intent(this, PopUpOrderActivity.class);
            Bundle extras = new Bundle();
            extras.putString("nomortlp", nomorTlp);
            extras.putInt("id", idguru);
            intent.putExtras(extras);
            startActivity(intent);


            //id guru dapet


        }

    }
}
    ///////////////////////////AMBIL GAMBAR//////////////////////////////////////////////////////
//    protected void onActivityResult(int requestCode, int resultCode,Intent imageReturnedIntent) {
//        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
//        switch (requestCode) {
//
//            case 101:
//                if (resultCode == RESULT_OK) {
//                    Uri selectedImage = imageReturnedIntent.getData();
//                    String filename = getRealPathFromURI(selectedImage);
//                    bi = BitmapFactory.decodeFile(filename);
//                /*
//                ByteArrayOutputStream out = new ByteArrayOutputStream();
//                bi.compress(Bitmap.CompressFormat.JPEG,10,out);
//                bi = BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));*/
//
//                    if(bi!=null)
//                    {
//                        try {
//                            new MyAsync().execute();
//                        } catch (Exception e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        }
//                    }
//                }
//        }
//    }
//    class MyAsync extends AsyncTask {
//        @Override
//        protected void onPreExecute() {
//            // TODO Auto-generated method stub
//            super.onPreExecute();
//            showDialog(0);
//        }
//
//        @Override
//        protected Object doInBackground(Object... params) {
//            // TODO Auto-generated method stub
//
//            try {
//                load(bi);
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Object result) {
//            // TODO Auto-generated method stub
//            super.onPostExecute(result);
//
//            ImageView img = (ImageView) findViewById(R.id.insgbr);
//            img.setImageBitmap(bi);
//        }
//    }
//
//    public void load(Bitmap bi) throws IOException {
//
//        if (bi != null) {
//            // Reset all the bins
//            for (int i = 0; i < NUMBER_OF_COLOURS; i++) {
//                for (int j = 0; j < SIZE; j++) {
//                    colourBins[i][j] = 0;
//                }
//            }
//
//            for (int x = 0; x < bi.getWidth(); x++) {
//                for (int y = 0; y < bi.getHeight(); y++) {
//
//                    int pixel = bi.getPixel(x, y);
//
//                    colourBins[RED][Color.red(pixel)]++;
//                    colourBins[GREEN][Color.green(pixel)]++;
//                    colourBins[BLUE][Color.blue(pixel)]++;
//                }
//            }
//
//            maxY = 0;
//
//            for (int i = 0; i < NUMBER_OF_COLOURS; i++) {
//                for (int j = 0; j < SIZE; j++) {
//                    if (maxY < colourBins[i][j]) {
//                        maxY = colourBins[i][j];
//                    }
//                }
//            }
//            loaded = true;
//        } else {
//            loaded = false;
//        }
//    }
//
//    public String getRealPathFromURI(Uri contentUri) {
//        Log.e("TEST", "GetRealPath : " + contentUri);
//
//        try {
//            if (contentUri.toString().contains("video")) {
//                String[] proj = { MediaStore.Video.Media.DATA };
//                Cursor cursor = managedQuery(contentUri, proj, null, null, null);
//                int column_index = cursor
//                        .getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
//                cursor.moveToFirst();
//                return cursor.getString(column_index);
//            } else {
//                String[] proj = { MediaStore.Images.Media.DATA };
//                Cursor cursor = managedQuery(contentUri, proj, null, null, null);
//                int column_index = cursor
//                        .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//                cursor.moveToFirst();
//                return cursor.getString(column_index);
//            }
//        } catch (IllegalArgumentException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return null;
//    }



