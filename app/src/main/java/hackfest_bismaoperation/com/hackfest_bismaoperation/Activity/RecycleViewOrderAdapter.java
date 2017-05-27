package hackfest_bismaoperation.com.hackfest_bismaoperation.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import hackfest_bismaoperation.com.hackfest_bismaoperation.Model.APIGuruData;
import hackfest_bismaoperation.com.hackfest_bismaoperation.Model.APIOrderListPengajar;
import hackfest_bismaoperation.com.hackfest_bismaoperation.R;

/**
 * Created by Ryan Bagus Susilo on 5/21/2017.
 */
public class RecycleViewOrderAdapter extends RecyclerView.Adapter<OrderHolder>{

    List<APIOrderListPengajar.ResponBean> listGuru;
    private Context context;
    private String notlp1,email,tgllahir,tempatlahir,jeniskelamin,namabelakang;

    public RecycleViewOrderAdapter(Context context, List<APIOrderListPengajar.ResponBean> listGuru) {
        this.context = context;
        this.listGuru = listGuru;

    }

    @Override
    public int getItemCount() {
        return (null != listGuru ? listGuru.size() : 0);

    }



    @Override
    public OrderHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // This method will inflate the custom layout and return as viewholder
        LayoutInflater mInflater = LayoutInflater.from(viewGroup.getContext());

        ViewGroup mainGroup = (ViewGroup) mInflater.inflate(
                R.layout.view_rv_order , viewGroup, false);
        OrderHolder listHolder = new OrderHolder(mainGroup);
        return listHolder;
    }

    @Override
    public void onBindViewHolder(OrderHolder holder, int position) {
        final APIOrderListPengajar.ResponBean guru = listGuru.get(position);
        final OrderHolder mainHolder = holder;

        //mainHolder.tvid.setText(String.valueOf(guru.getId()));

        mainHolder.tvtit.setText(guru.getId_pengajar().get(0).getNama_depan());
        mainHolder.tvsub.setText(guru.getId_pengajar().get(0).getAlamat());
        mainHolder.tv_status.setText(guru.getId_pengajar().get(0).getStatus());
        mainHolder.tv_telpon.setText(guru.getId_pengajar().get(0).getTelepon());
        mainHolder.tv_email.setText(guru.getId_pengajar().get(0).getEmail());
        mainHolder.tv_tanggallahir.setText(guru.getId_pengajar().get(0).getTanggal_lahir());
        mainHolder.tv_tempatlahir.setText(guru.getId_pengajar().get(0).getTempat_lahir());
        mainHolder.tv_jeniskelamin.setText(guru.getId_pengajar().get(0).getKelamin());
        mainHolder.tv_namabelakang.setText(guru.getId_pengajar().get(0).getNama_belakang());
        mainHolder.tv_harga.setText(guru.getId_pengajar().get(0).getHarga());
        mainHolder.foto2.setText(guru.getId_pengajar().get(0).getProfil());
        mainHolder.matapelajaran.setText(guru.getId_pengajar().get(0).getMata_pelajaran());
        Picasso.with(context).load(guru.getId_pengajar().get(0).getProfil()).into(mainHolder.foto);
        mainHolder.tv_title.setText(guru.getId_pengajar().get(0).getNama_depan());
        mainHolder.id_order.setText(String.valueOf(guru.getId()));
        mainHolder.tv_totalharga.setText(guru.getTotal_biaya());
        mainHolder.tv_jambelajar.setText(String.valueOf(guru.getJam()));
        mainHolder.tv_tanggalOrder.setText(guru.getTanggal());
        mainHolder.tv_statusOrder.setText(guru.getStatus());

        namabelakang=guru.getId_pengajar().get(0).getNama_belakang();

        mainHolder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle extras = new Bundle();
                extras.putInt("id",Integer.parseInt(mainHolder.id_order.getText().toString()));
                extras.putString("nama",mainHolder.tvtit.getText().toString());
                extras.putString("alamat", mainHolder.tvsub.getText().toString());
                extras.putString("status", mainHolder.tv_status.getText().toString());
                extras.putString("nomortlp", mainHolder.tv_telpon.getText().toString());
                extras.putString("harga", mainHolder.tv_harga.getText().toString());
                // extras.putString("nomortlp",notlp1);
                extras.putString("email",mainHolder.tv_email.getText().toString());
                extras.putString("tanggallahir",mainHolder.tv_tanggallahir.getText().toString());
                extras.putString("tempatlahir",mainHolder.tv_tempatlahir.getText().toString());
                extras.putString("jeniskelamin",mainHolder.tv_jeniskelamin.getText().toString());
                extras.putString("namabelakang",mainHolder.tv_namabelakang.getText().toString());
                extras.putString("matapelajaran",mainHolder.matapelajaran.getText().toString());
                extras.putString("profil",mainHolder.foto2.getText().toString());
                extras.putString("totalharga",mainHolder.tv_totalharga.getText().toString());
                extras.putString("jambelajar",mainHolder.tv_jambelajar.getText().toString());
                extras.putString("tanggalorder",mainHolder.tv_tanggalOrder.getText().toString());
                extras.putString("statusorder",mainHolder.tv_statusOrder.getText().toString());
//                extras.putString("profil",mainHolder.foto.toString());

                Log.d("LoginActivity", "response >>>= " + mainHolder.foto2.toString());
                Intent intent = new Intent(context, DetilGuruOrderWithPriceActivity.class);
                intent.putExtras(extras);
                context.startActivity(intent);
            }
        });

    }


}
