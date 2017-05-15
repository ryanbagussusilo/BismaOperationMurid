package hackfest_bismaoperation.com.hackfest_bismaoperation.Activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import hackfest_bismaoperation.com.hackfest_bismaoperation.Model.APIBaseResponse;
import hackfest_bismaoperation.com.hackfest_bismaoperation.R;
import hackfest_bismaoperation.com.hackfest_bismaoperation.REST.RestClient;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText namadepan,namabelakang,nomortlp,alamatmurid;
    private EditText tempatLahir,emailmurid,usernamereg,passwordreg,tgllahir;
    private RadioButton jeniskelamin;

    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;
    private RadioButton rbP;
    private RadioButton rbL;
    private int mYear, mMonth, mDay;
    private Button btnSave;
    private Call<APIBaseResponse> callSignup;
    private hackfest_bismaoperation.com.hackfest_bismaoperation.REST.RestClient.GitApiInterface service;
    private static String birthDateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        namadepan=(EditText)findViewById(R.id.input_namadepan);
        namabelakang=(EditText)findViewById(R.id.input_namabelakang);
        nomortlp=(EditText)findViewById(R.id.input_nomortelp);
        alamatmurid=(EditText)findViewById(R.id.input_alamatmurid);
        tempatLahir=(EditText)findViewById(R.id.input_tempatlahir);
        emailmurid=(EditText)findViewById(R.id.input_emailmurid);
        usernamereg=(EditText)findViewById(R.id.input_usernameReg);
        passwordreg=(EditText)findViewById(R.id.input_passwordReg);
        radioSexGroup=(RadioGroup)findViewById(R.id.radioGroup);
        int selectedId=radioSexGroup.getCheckedRadioButtonId();
        radioSexButton=(RadioButton)findViewById(selectedId);
        rbP=(RadioButton)findViewById(R.id.rbPerempuan);
        rbL=(RadioButton)findViewById(R.id.rbLakilaki);
        tgllahir=(EditText)findViewById(R.id.input_tgllahir);
        btnSave=(Button)findViewById(R.id.buttonSave);

        tgllahir.setOnClickListener(this);
        btnSave.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        if (v == tgllahir) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            tgllahir.setText( dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }

        if (v == btnSave) {
            if (!validate()) {

                return;
            }
            final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this, R.style.ProgressDialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Membuat Akun...");
            progressDialog.show();

            // TODO: Implement your own signup logic here.
            String namadepanS=namadepan.getText().toString();
            String namabelakangS=namabelakang.getText().toString();
            String nomortelpS=nomortlp.getText().toString();
            String alamatmuridS=alamatmurid.getText().toString();
            String tempatlahirS=tempatLahir.getText().toString();
            String emailmuridS=emailmurid.getText().toString();
            String usernameS=usernamereg.getText().toString();
            String passwordS=passwordreg.getText().toString();

            String jeniskelaminS="";
            if(rbL.isChecked()) {
                jeniskelaminS= "Pria";
            }else if(rbP.isChecked()) {
                jeniskelaminS= "Wanita";
            }
            birthDateFormat= tgllahir.getText().toString();



            service = RestClient.getClient();


            callSignup = service.signUp(namadepanS,namabelakangS,alamatmuridS,tempatlahirS,nomortelpS,jeniskelaminS,birthDateFormat,emailmuridS,usernameS,passwordS,"","");
            callSignup.enqueue(new Callback<APIBaseResponse>() {
                @Override
                public void onResponse(Response<APIBaseResponse> response) {
                    Log.d("Register2", "Status Code = " + response.code());
                    if (response.isSuccess()) {
                        // request successful (status code 200, 201)
                        APIBaseResponse result = response.body();
                        Log.d("Register2", "response = " + new Gson().toJson(result));
                        if (result != null) {
                            Toast.makeText(getBaseContext(), "Berhasil Mendaftar", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                            finishAffinity();
                            Intent intent = new Intent(RegisterActivity.this ,ListGuruActivity.class);
                            startActivity(intent);
                        }

                    } else {
                        // response received but request not successful (like 400,401,403 etc)
                        //Handle errors
                        Toast.makeText(getBaseContext(), "Gagal Mendaftar", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    Toast.makeText(getBaseContext(), "Gagal Mendaftar", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }
            });
        }



    }


    public boolean validate() {
        boolean valid = true;

        String email = emailmurid.getText().toString();
       // String username=txtinputUsername.getText().toString();
        String password=passwordreg.getText().toString();

        if (password.isEmpty()) {
            passwordreg.setError("Password tidak boleh kosong");
            valid = false;
        } else {
            passwordreg.setError(null);
        }

        if (usernamereg.getText().toString().isEmpty()) {
            usernamereg.setError("Username tidak boleh kosong");
            valid = false;
        } else {
            usernamereg.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailmurid.setError("Email tidak sesuai dengan format");
            valid = false;
        } else {
            emailmurid.setError(null);
        }




        if (namadepan.getText().toString().isEmpty()) {
            namadepan.setError("Nama Depan tidak boleh kosong");
            valid = false;
        } else {
            namadepan.setError(null);
        }

        if (namabelakang.getText().toString().isEmpty()) {
            namabelakang.setError("Nama Belakang tidak boleh kosong");
            valid = false;
        } else {
            namabelakang.setError(null);
        }

        if (nomortlp.getText().toString().isEmpty() ) {
            nomortlp.setError("Nomor Telepon tidak boleh kosong");
            valid = false;
        } else {
            nomortlp.setError(null);
        }
        if (!rbL.isChecked()&&!rbP.isChecked()) {
            rbL.setError("Jenis kelamin tidak boleh kosong");
            rbP.setError("Jenis kelamin tidak boleh kosong");
            valid = false;
        } else {
            rbL.setError(null);
            rbP.setError(null);
        }
        if (tgllahir.getText().toString().isEmpty()) {
            tgllahir.hasFocusable();
            tgllahir.setError("Tanggal lahir tidak boleh kosong");
            valid = false;
        } else {
            tgllahir.setError(null);
        }



        return valid;
    }


}
