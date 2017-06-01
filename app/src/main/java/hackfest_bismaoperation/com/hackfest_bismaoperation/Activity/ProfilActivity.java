package hackfest_bismaoperation.com.hackfest_bismaoperation.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


import hackfest_bismaoperation.com.hackfest_bismaoperation.Preferences.SessionManager;
import hackfest_bismaoperation.com.hackfest_bismaoperation.R;

public class ProfilActivity extends ActionBarActivity implements AbsListView.OnScrollListener {
    private ListView listView;
    private Toolbar toolbar;
    private TextView floatTitle;
    private ImageView headerBg;
    //测量值
    private float headerHeight;//顶部高度
    private float minHeaderHeight;//顶部最低高度，即Bar的高度
    private float floatTitleLeftMargin;//header标题文字左偏移量
    private float floatTitleSize;//header标题文字大小
    private float floatTitleSizeLarge;//header标题文字大小（大号）

    private String id;
    private String namadepan;
    private String namabelakang;
    private String tempatlahir;
    private String tanggallahir;
    private String jeniskelamin;
    private String nomortlp;
    private String emailmurid;
    private String alamatmurid;
    private  Intent intent;
    private TextView txtnamadepan,txtnamabelakang,txttempatlahir,txttanggallahir,txtjeniskelamin,txtnomortlp,txtemailmurid,txtalamatmurid;
    SessionManager sessions;
    private Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profils);

        sessions = new SessionManager(this);

//        Bundle b=getIntent().getExtras();
//        id=b.getString("idmurid");
//        namadepan=b.getString("namadepan");
//        namabelakang=b.getString("namabelakang");
//        tempatlahir=b.getString("tempatlahir");
//        tanggallahir=b.getString("tanggallahir");
//        jeniskelamin=b.getString("jeniskelamin");
//        nomortlp=b.getString("nomortlp");
//        emailmurid=b.getString("emailmurid");
//        alamatmurid=b.getString("alamatmurid");





        initMeasure();
        initView();
        initListViewHeader();
        initListView();
        initEvent();

//        txtnamadepan=(TextView) findViewById(R.id.txtNamaDepanPr);
//        txtnamabelakang=(TextView) findViewById(R.id.txtNamaBelakangPr);
//        txtalamatmurid=(TextView) findViewById(R.id.txtAlamatPr);
//        txtnomortlp=(TextView) findViewById(R.id.txtNomortlpPr);
//        txtemailmurid=(TextView) findViewById(R.id.txtEmailPr);
//        txtjeniskelamin=(TextView) findViewById(R.id.txtJenisKelaminPr);


//        txtnamadepan.setText(namadepan);
//        txtnamabelakang.setText(namabelakang);
//        txttempatlahir.setText(tempatlahir);
//        txtalamatmurid.setText(alamatmurid);
//        txtnomortlp.setText(nomortlp);
//        txtemailmurid.setText(emailmurid);
//        txtjeniskelamin.setText(jeniskelamin);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.action_favorites:

//                                Bundle extras = new Bundle();
//                                extras.putString("idmurid",id);
//                                extras.putString("namadepan",namadepan);
//                                extras.putString("namabelakang",namabelakang);
//                                extras.putString("tempatlahir",tempatlahir);
//                                extras.putString("tanggallahir",tanggallahir);
//                                extras.putString("jeniskelamin",jeniskelamin);
//                                extras.putString("nomorlp",nomortlp);
//                                extras.putString("emailmurid",emailmurid);
//                                extras.putString("alamatmurid",alamatmurid);
//                                intent.putExtras(extras);
                                intent = new Intent(ProfilActivity.this,ListGuruActivity.class);
                                startActivity(intent);

                                break;


                            case R.id.action_schedules:
                                intent = new Intent(ProfilActivity.this, ListOrderActivity.class);
                                // intent.putExtras(extras);
                                startActivity(intent);

                            break;
                            case R.id.action_music:
                                intent = new Intent(ProfilActivity.this, ProfilActivity.class);
                                startActivity(intent);
                                break;

                        }
                        return true;
                    }
                });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_settings:
                // Code you want run when activity is clicked
               sessions.logoutUser();
                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onBackPressed()
    {
        this.finishAffinity();
        super.onBackPressed();
    }
    private void initMeasure() {
        headerHeight = getResources().getDimension(R.dimen.header_height);
        minHeaderHeight = getResources().getDimension(R.dimen.abc_action_bar_default_height_material);
        floatTitleLeftMargin = getResources().getDimension(R.dimen.float_title_left_margin);
        floatTitleSize = getResources().getDimension(R.dimen.float_title_size);
        floatTitleSizeLarge = getResources().getDimension(R.dimen.float_title_size_large);
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.lv_main);
        floatTitle = (TextView) findViewById(R.id.tv_main_title);
        toolbar = (Toolbar) findViewById(R.id.tb_main);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initListView() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            data.add("Nama Depan              :" + sessions.getUserDetails().get(SessionManager.KEY_NAMA_DEPAN));
            data.add("Nama Belakang         :"+ sessions.getUserDetails().get(SessionManager.KEY_NAMA_BELAKANG));
            data.add("Tempatlahir                :"+ sessions.getUserDetails().get(SessionManager.KEY_TEMPAT_LAHIR));
            data.add("Tanggal Lahir             :"+ sessions.getUserDetails().get(SessionManager.KEY_TANGGAL_LAHIR));
            data.add("Jenis Kelamin            :"+ sessions.getUserDetails().get(SessionManager.KEY_KELAMIN));
            data.add("Nomor Telepon          :"+ sessions.getUserDetails().get(SessionManager.KEY_TELEPON));
            data.add("Email                           :"+ sessions.getUserDetails().get(SessionManager.KEY_EMAIL));
            data.add("Alamat                        :"+ sessions.getUserDetails().get(SessionManager.KEY_ALAMAT));
            data.add("Profil                        :"+ sessions.getUserDetails().get(SessionManager.KEY_PROFIL));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.activity_list_item, android.R.id.text1, data);
        listView.setAdapter(adapter);
    }

    private void initListViewHeader() {
        View headerContainer = LayoutInflater.from(this).inflate(R.layout.header, listView, false);
        headerBg = (ImageView) headerContainer.findViewById(R.id.img_header_bg);
        Picasso.with(context).load(sessions.getUserDetails().get(SessionManager.KEY_PROFIL)).into(headerBg);
      //  Log.d("Cek Profil", sessions.getUserDetails().get(SessionManager.KEY_PROFIL));
        Log.d("LoginActivity", "Status Code = " + sessions.getUserDetails().get(SessionManager.KEY_NAMA_DEPAN));

        listView.addHeaderView(headerContainer);
    }

    private void initEvent() {
        listView.setOnScrollListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        //Y轴偏移量
        float scrollY = getScrollY(view);

        //变化率
        float headerBarOffsetY = headerHeight - minHeaderHeight;//Toolbar与header高度的差值
        float offset = 1 - Math.max((headerBarOffsetY - scrollY) / headerBarOffsetY, 0f);

        //Toolbar背景色透明度
        toolbar.setBackgroundColor(Color.argb((int) (offset * 255), 0, 0, 0));
        //header背景图Y轴偏移
        headerBg.setTranslationY(scrollY / 2);

        /*** 标题文字处理 ***/
        //标题文字缩放圆心（X轴）
        floatTitle.setPivotX(floatTitle.getLeft() + floatTitle.getPaddingLeft());
        //标题文字缩放比例
        float titleScale = floatTitleSize / floatTitleSizeLarge;
        //标题文字X轴偏移
        floatTitle.setTranslationX(floatTitleLeftMargin * offset);
        //标题文字Y轴偏移：（-缩放高度差 + 大文字与小文字高度差）/ 2 * 变化率 + Y轴滑动偏移
        floatTitle.setTranslationY(
                (-(floatTitle.getHeight() - minHeaderHeight) +//-缩放高度差
                        floatTitle.getHeight() * (1 - titleScale))//大文字与小文字高度差
                        / 2 * offset +
                        (headerHeight - floatTitle.getHeight()) * (1 - offset));//Y轴滑动偏移
        //标题文字X轴缩放
        floatTitle.setScaleX(1 - offset * (1 - titleScale));
        //标题文字Y轴缩放
        floatTitle.setScaleY(1 - offset * (1 - titleScale));

        //判断标题文字的显示
        if (scrollY > headerBarOffsetY) {
            toolbar.setTitle(getResources().getString(R.string.toolbar_title));
            floatTitle.setVisibility(View.GONE);
        } else {
            toolbar.setTitle("");
            floatTitle.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 得到ListView在Y轴上的偏移
     */
    public float getScrollY(AbsListView view) {
        View c = view.getChildAt(0);

        if (c == null)
            return 0;

        int firstVisiblePosition = view.getFirstVisiblePosition();
        int top = c.getTop();

        float headerHeight = 0;
        if (firstVisiblePosition >= 1)
            headerHeight = this.headerHeight;

        return -top + firstVisiblePosition * c.getHeight() + headerHeight;
    }

}
