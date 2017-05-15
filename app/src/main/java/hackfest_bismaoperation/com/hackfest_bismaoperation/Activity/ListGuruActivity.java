package hackfest_bismaoperation.com.hackfest_bismaoperation.Activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import hackfest_bismaoperation.com.hackfest_bismaoperation.Model.APIGuruData;
import hackfest_bismaoperation.com.hackfest_bismaoperation.Model.Guru;
import hackfest_bismaoperation.com.hackfest_bismaoperation.R;
import hackfest_bismaoperation.com.hackfest_bismaoperation.REST.RestClient;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class ListGuruActivity extends AppCompatActivity {

    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private Call<APIGuruData> callGuru;
    private RestClient.GitApiInterface service;

    private ArrayList<Guru> GuruItems = new ArrayList<Guru>();
    private int id;
    private static int idmurid1;

    public static int getIdmurid1() {
        return idmurid1;
    }

    public static void setIdmurid1(int idmurid1) {
        ListGuruActivity.idmurid1 = idmurid1;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listguru);
        rvView = (RecyclerView) findViewById(R.id.rv_main);
        rvView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);
        rvView.setLayoutManager(layoutManager);
        adapter = new RecycleViewAdapter(ListGuruActivity.this, GuruItems);
        rvView.setAdapter(adapter);
        fetchData();
       // Bundle b=getIntent().getExtras();
//        id=b.getInt("idmurid");
//        setIdmurid1(b.getInt("idmurid"));
    }

    public void fetchData()
    {
        final ProgressDialog progressDialog = new ProgressDialog(ListGuruActivity.this,
                R.style.ProgressDialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Mengambil Data Guru...");
        progressDialog.show();



        service = RestClient.getClient();
        callGuru = service.showguru();
        callGuru.enqueue(new Callback<APIGuruData>() {
            @Override
            public void onResponse(Response<APIGuruData> response) {
                Log.d("ListGuruFetching", "Status Code = " + response.code());
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    APIGuruData result = response.body();
                    Log.d("ListGuruFetching", "response = " + new Gson().toJson(result));
                    if (result != null) {

                        GuruItems.clear();

                        List<Guru> ResponseItems = result.getGuru();

                        if(ResponseItems!=null)
                        {
                            for (Guru Responitem : ResponseItems) {
                                GuruItems.add(Responitem);
                                adapter.notifyDataSetChanged();
                            }
                        }
                        progressDialog.dismiss();
                    }

                } else {
                    // response received but request not successful (like 400,401,403 etc)
                    //Handle errors
                    Toast.makeText(getApplicationContext(), "Gagal Ambil Data", Toast.LENGTH_SHORT).show();

                    progressDialog.dismiss();
                }
            }
            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), "Koneksi Ke Internet Gagal", Toast.LENGTH_SHORT).show();
                Log.d("ListGuruFetching", t.getMessage()+t.toString());
                progressDialog.dismiss();

            }
        });

    }
}


