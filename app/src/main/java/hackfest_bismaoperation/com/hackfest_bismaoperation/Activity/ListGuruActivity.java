package hackfest_bismaoperation.com.hackfest_bismaoperation.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yalantis.phoenix.PullToRefreshView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import hackfest_bismaoperation.com.hackfest_bismaoperation.Model.APIGuruData;

import hackfest_bismaoperation.com.hackfest_bismaoperation.Preferences.SessionManager;
import hackfest_bismaoperation.com.hackfest_bismaoperation.R;
import hackfest_bismaoperation.com.hackfest_bismaoperation.REST.RestClient;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class ListGuruActivity extends AppCompatActivity {

    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private PullToRefreshView mPullToRefreshView;
    private Call<APIGuruData> callGuru;
    private RestClient.GitApiInterface service;

    private ArrayList<APIGuruData.ResponBean.DataBean> GuruItems = new ArrayList<APIGuruData.ResponBean.DataBean>();
    private  Intent intent;
    Bundle extras;
    SessionManager sessions;
    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listguru);
        rvView = (RecyclerView) findViewById(R.id.rv_main);
        rvView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);
        adapter = new RecycleViewAdapter(ListGuruActivity.this, GuruItems);
        rvView.setAdapter(adapter);




        fetchData();
        sessions = new SessionManager(this);
        Log.d("LoginActivity", "Status Code = " + sessions.getUserDetails().get(SessionManager.KEY_EMAIL));

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_favorites:
                            break;

                            case R.id.action_schedules:
                                intent = new Intent(ListGuruActivity.this, ListOrderActivity.class);

                                startActivity(intent);
                                break;

                            case R.id.action_music:
                                extras = new Bundle();
                                intent = new Intent(ListGuruActivity.this, ProfilActivity.class);
                                intent.putExtras(extras);
                                startActivity(intent);
                                break;

                        }
                        return true;
                    }
                });


        // Lookup the swipe container view
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                fetchData();
                swipeContainer.setRefreshing(false);
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    }




    @Override
    public void onBackPressed()
    {
        this.finishAffinity();
        super.onBackPressed();
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

                        List<APIGuruData.ResponBean.DataBean> ResponseItems = response.body().getRespon().getData();

                        if(ResponseItems!=null)
                        {
                            for (APIGuruData.ResponBean.DataBean Responitem : ResponseItems) {
                                GuruItems.add(Responitem);
                                adapter.notifyDataSetChanged();
                                swipeContainer.setRefreshing(false);

                            }
                        }
                        progressDialog.dismiss();
                        swipeContainer.setRefreshing(false);
                    }

                } else {
                    // response received but request not successful (like 400,401,403 etc)
                    //Handle errors
                    Toast.makeText(getApplicationContext(), "Gagal Ambil Data", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    swipeContainer.setRefreshing(false);
                }
                swipeContainer.setRefreshing(false);
            }
            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), "Koneksi Ke Internet Gagal", Toast.LENGTH_SHORT).show();
                Log.d("ListGuruFetching", t.getMessage()+t.toString());
                progressDialog.dismiss();
                swipeContainer.setRefreshing(false);

            }
        });

    }
}


