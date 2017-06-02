package hackfest_bismaoperation.com.hackfest_bismaoperation.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.gson.Gson;
import hackfest_bismaoperation.com.hackfest_bismaoperation.Model.APIMuridData;
import hackfest_bismaoperation.com.hackfest_bismaoperation.Preferences.SessionManager;
import hackfest_bismaoperation.com.hackfest_bismaoperation.R;
import hackfest_bismaoperation.com.hackfest_bismaoperation.REST.RestClient;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;


public class LoginActivity extends AppCompatActivity {

    private RestClient.GitApiInterface service;
    private Call<APIMuridData> callLogin;
    private String username;
    private String password;
    EditText txtusername;
    EditText txtpassword;
    SessionManager sessions;
    Intent intent;
    private static final String TAG = "LoginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btnRegister = (Button) findViewById(R.id.btn_Register);
        Button btnLogin = (Button) findViewById(R.id.btn_login);
        txtusername=(EditText) findViewById(R.id.input_username);
        txtpassword=(EditText) findViewById(R.id.input_password);

        sessions = new SessionManager(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);

            }

        });

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                doLogin();

            }

        });
    }
    @Override
    public void onBackPressed()
    {
        this.finishAffinity();
        super.onBackPressed();
    }
    public void doLogin() {
        Log.d(TAG, "LoginActivity");
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.ProgressDialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Mencoba Login...");
        progressDialog.show();

        username= txtusername.getText().toString();
        password = txtpassword.getText().toString();

        // TODO: Implement your own authentication logic here.

        service = RestClient.getClient();
        callLogin = service.login(username, password);

        callLogin.enqueue(new Callback<APIMuridData>() {
            @Override
            public void onResponse(Response<APIMuridData> response) {

                Log.d("LoginActivity", "Status Code = " + response.code());
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    APIMuridData result = response.body();
                    String returnresponse=result.getMessage();

                    //Format Date Database  YYYY-MM-DD
                    Log.d("LoginActivity", "response = " + new Gson().toJson(result));

                    if (returnresponse.equalsIgnoreCase("Login Berhasil")) {
                        Integer idMurid = result.getRespon().getId();
                        Log.d("LoginActivity", "response = " + new Gson().toJson(idMurid));


                        Toast.makeText(getBaseContext(),idMurid+" Login Berhasil sebagai "+username+" Role : Murid", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), ListGuruActivity.class);
                        startActivity(intent);
                        txtusername.setText("");
                        txtpassword.setText("");
                        txtpassword.clearFocus();
                        txtusername.clearFocus();
                        progressDialog.dismiss();


                        Log.d("LoginActivity", "response = " + new Gson().toJson(result));
                        sessions.createLoginSession(result);

                        Toast.makeText(getBaseContext(),result.getRespon().getId()+" Login Berhasil sebagai "+username+" Role : Murid", Toast.LENGTH_LONG).show();
                       intent = new Intent(getApplicationContext(), ListGuruActivity.class);
                        startActivity(intent);
                        progressDialog.dismiss();
                    }
                    else {
                        // response received but request not successful (like 400,401,403 etc)
                        //Handle errors
                        finish();
                        intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        Toast.makeText(getBaseContext(), "Login Gagal", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }

                } else {
                    // response received but request not successful (like 400,401,403 etc)
                    //Handle errors
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    Toast.makeText(getBaseContext(), "Login Gagal", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getBaseContext(), "Login Gagal", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });
    }

}
