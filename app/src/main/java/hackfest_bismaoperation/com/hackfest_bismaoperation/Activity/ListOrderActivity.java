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
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


import hackfest_bismaoperation.com.hackfest_bismaoperation.Model.APIOrderListPengajar;
import hackfest_bismaoperation.com.hackfest_bismaoperation.Preferences.SessionManager;
import hackfest_bismaoperation.com.hackfest_bismaoperation.R;
import hackfest_bismaoperation.com.hackfest_bismaoperation.REST.RestClient;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class ListOrderActivity extends AppCompatActivity {

    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Toolbar toolbar;
    private Call<APIOrderListPengajar> callGuru;
    private RestClient.GitApiInterface service;

    private ArrayList<APIOrderListPengajar.ResponBean> GuruItems = new ArrayList<APIOrderListPengajar.ResponBean>();
    private Intent intent;
    SessionManager sessions;
    private SwipeRefreshLayout swipeContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_order);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);
        rvView = (RecyclerView) findViewById(R.id.rv_mainorder);
        rvView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);
        adapter = new RecycleViewOrderAdapter(ListOrderActivity.this, GuruItems);
        rvView.setAdapter(adapter);
        sessions = new SessionManager(this);
        fetchData();

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_favorites:
//                                intent = new Intent(ListOrderActivity.this, Testing.class);
                                intent = new Intent(ListOrderActivity.this,ListGuruActivity.class);
                                startActivity(intent);
                                break;

                            case R.id.action_schedules:

                                break;
                            case R.id.action_music:

                                intent = new Intent(ListOrderActivity.this, ProfilActivity.class);
                                startActivity(intent);
                                break;

                        }
                        return true;
                    }
                });


        // Lookup the swipe container view
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContaineror);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                fetchData();
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
        final ProgressDialog progressDialog = new ProgressDialog(ListOrderActivity.this,
                R.style.ProgressDialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Mengambil Data Guru...");
        progressDialog.show();


 //       String ID=sessions.getUserDetails().get(SessionManager.KEY_USERID);
        service = RestClient.getClient();
        callGuru = service.orderdetil(Integer.parseInt(sessions.getUserDetails().get(SessionManager.KEY_USERID)));
        callGuru.enqueue(new Callback<APIOrderListPengajar>() {
            @Override
            public void onResponse(Response<APIOrderListPengajar> response) {
                Log.d("ListGuruFetching", "Status Code = " + response.code());
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    APIOrderListPengajar result = response.body();
                    Log.d("ListGuruFetching", "response = " + new Gson().toJson(result));
                    if (result != null) {

                        GuruItems.clear();

                        List<APIOrderListPengajar.ResponBean> ResponseItems = result.getRespon();

                        if(ResponseItems!=null)
                        {
                            for (APIOrderListPengajar.ResponBean Responitem : ResponseItems) {
                                GuruItems.add(Responitem);
                                adapter.notifyDataSetChanged();
                                swipeContainer.setRefreshing(false);
                            }
                        }
                        progressDialog.dismiss();
                    }

                } else {
                    // response received but request not successful (like 400,401,403 etc)
                    //Handle errors
                    Toast.makeText(getApplicationContext(), "Gagal Ambil Data", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    swipeContainer.setRefreshing(false);

                }
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
