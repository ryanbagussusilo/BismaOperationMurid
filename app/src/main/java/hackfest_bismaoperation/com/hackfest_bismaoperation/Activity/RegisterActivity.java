package hackfest_bismaoperation.com.hackfest_bismaoperation.Activity;

import android.accounts.AccountManager;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.File;

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
    public static final int MEDIA_TYPE_IMAGE = 1;
    private static final String IMAGE_DIRECTORY_NAME = "Bisma_CapturedPhoto";
    private RadioButton rbL;
    private int mYear, mMonth, mDay;
    private Button btnSave;
    private Call<APIBaseResponse> callSignup;
    private hackfest_bismaoperation.com.hackfest_bismaoperation.REST.RestClient.GitApiInterface service;
    private static String birthDateFormat;
    private ImageView AttachImage;
    private Uri fileUri;
    public static int COUNT_CAMERA = 100;
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
        AttachImage = (ImageView) findViewById(R.id.imgAttach);
        rbL=(RadioButton)findViewById(R.id.rbLakilaki);
        tgllahir=(EditText)findViewById(R.id.input_tgllahir);
        btnSave=(Button)findViewById(R.id.buttonSave);

        tgllahir.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        AttachImage.setOnClickListener(this);

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
            if (AttachImage.getDrawable() == null) {
                Toast.makeText(getApplicationContext(), "Silahkan tambahan foto terlebih dahulu", Toast.LENGTH_SHORT).show();
            } else {


              //  String fDate = new SimpleDateFormat("yyyy-MM-dd").format(tgllahir);

                String photoName;


                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ((BitmapDrawable) AttachImage.getDrawable()).getBitmap().compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
                String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
                photoName= emailmurid.getText().toString()+"_Profil.jpg";

                final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this, R.style.ProgressDialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Membuat Akun...");
                progressDialog.show();


                Toast.makeText(getBaseContext(), String.valueOf(encodedImage.length()), Toast.LENGTH_SHORT).show();
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


                callSignup = service.signUp(namadepanS,namabelakangS,alamatmuridS,tempatlahirS,nomortelpS,jeniskelaminS,birthDateFormat,emailmuridS,usernameS,passwordS,"","",encodedImage);
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
                                Intent intent = new Intent(RegisterActivity.this ,LoginActivity.class);
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
        else if(v==AttachImage)
        {
            selectImage();
        }


    }
    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == COUNT_CAMERA) {

                Bitmap bitmap;
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(fileUri.getPath(), options);
                final int REQUIRED_SIZE = 450;
                int scale = 1;
                while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                        && options.outHeight / scale / 2 >= REQUIRED_SIZE)
                    scale *= 2;
                options.inSampleSize = scale;
                options.inJustDecodeBounds = false;
                bitmap =  BitmapFactory.decodeFile(fileUri.getPath(), options);

                AttachImage.setImageBitmap(bitmap);
                AttachImage.setScaleType(ImageView.ScaleType.FIT_XY);

            } else if (requestCode == 2) {
                Uri selectedImageUri = data.getData();
                String[] projection = {MediaStore.MediaColumns.DATA};
                CursorLoader cursorLoader = new CursorLoader(this, selectedImageUri, projection, null, null,
                        null);
                Cursor cursor = cursorLoader.loadInBackground();
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                cursor.moveToFirst();

                String selectedImagePath = cursor.getString(column_index);

                Bitmap bm;
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(selectedImagePath, options);
                final int REQUIRED_SIZE = 450;
                int scale = 1;
                while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                        && options.outHeight / scale / 2 >= REQUIRED_SIZE)
                    scale *= 2;
                options.inSampleSize = scale;
                options.inJustDecodeBounds = false;
                bm = BitmapFactory.decodeFile(selectedImagePath, options);

                Display display = getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                int width = size.x;

                if(bm.getWidth()<width){
                    int heighttoAdd = bm.getHeight()+(width-bm.getWidth());
                    Bitmap newResizedBitmap = getResizedBitmap(bm,width,heighttoAdd);
                    AttachImage.setImageBitmap(newResizedBitmap);
                }else{
                    AttachImage.setImageBitmap(bm);
                    AttachImage.setScaleType(ImageView.ScaleType.FIT_START);
                }
            }
        }
    }


    private void selectImage() {
        final CharSequence[] items = { "Take Photo", "Choose from Library", "Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                    startActivityForResult(intent, COUNT_CAMERA);
                } else if (items[item].equals("Choose from Library")) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent, "Select File"),
                            2);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }
    private File getOutputMediaFile(int type) {
        // External sdcard location
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }

        // Create a media file name
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "FotoProfil.jpg");
        } else {
            return null;
        }
        MediaScannerConnection.scanFile(this, new String[]{Environment.getExternalStorageDirectory().toString()},
                null, new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, Uri uri) {
                        Log.i("ExternalStorage", "Scanned " + path + ":");
                        Log.i("ExternalStorage", "-> uri=" + uri);
                    }
                });
        return mediaFile;
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
