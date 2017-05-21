package hackfest_bismaoperation.com.hackfest_bismaoperation.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import hackfest_bismaoperation.com.hackfest_bismaoperation.Model.APIGuruData;
import hackfest_bismaoperation.com.hackfest_bismaoperation.R;

/**
 * Created by Ryan Bagus Susilo on 5/21/2017.
 */
public class RecycleViewOrderAdapter extends RecyclerView.Adapter<GuruHolder>{

    List<APIGuruData.ResponBean.DataBean> listGuru;
    private Context context;
    private String notlp1,email,tgllahir,tempatlahir,jeniskelamin,namabelakang;

    public RecycleViewOrderAdapter(Context context, List<APIGuruData.ResponBean.DataBean> listGuru) {
        this.context = context;
        this.listGuru = listGuru;

    }

    @Override
    public int getItemCount() {
        return (null != listGuru ? listGuru.size() : 0);

    }

    @Override
    public void onBindViewHolder(GuruHolder holder, int position) {
        final APIGuruData.ResponBean.DataBean guru = listGuru.get(position);
        final GuruHolder mainHolder = holder;

        mainHolder.tvid.setText(String.valueOf(guru.getId()));
        mainHolder.tvtit.setText(guru.getNama_depan());
        mainHolder.tvsub.setText(guru.getAlamat());
        mainHolder.tvStatus.setText(guru.getMata_pelajaran());
        mainHolder.tv_telpon.setText(guru.getTelepon());
        mainHolder.tv_email.setText(guru.getEmail());
        mainHolder.tv_tanggallahir.setText(guru.getTanggal_lahir());
        mainHolder.tv_tempatlahir.setText(guru.getTempat_lahir());
        mainHolder.tv_jeniskelamin.setText(guru.getKelamin());
        mainHolder.tv_namabelakang.setText(guru.getNama_belakang());
        mainHolder.tv_harga.setText(guru.getHarga());
        namabelakang=guru.getNama_belakang();

        mainHolder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle extras = new Bundle();
                extras.putInt("id",Integer.parseInt(mainHolder.tvid.getText().toString()));
                extras.putString("nama",mainHolder.tvtit.getText().toString());
                extras.putString("alamat", mainHolder.tvsub.getText().toString());
                extras.putString("status", mainHolder.tvStatus.getText().toString());
                extras.putString("nomortlp", mainHolder.tv_telpon.getText().toString());
                extras.putString("harga", mainHolder.tv_harga.getText().toString());
                // extras.putString("nomortlp",notlp1);
                extras.putString("email",mainHolder.tv_email.getText().toString());
                extras.putString("tanggallahir",mainHolder.tv_tanggallahir.getText().toString());
                extras.putString("tempatlahir",mainHolder.tv_tempatlahir.getText().toString());
                extras.putString("jeniskelamin",mainHolder.tv_jeniskelamin.getText().toString());
                extras.putString("namabelakang",mainHolder.tv_namabelakang.getText().toString());
                Intent intent = new Intent(context, DetilGuruActivity.class);
                intent.putExtras(extras);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public GuruHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // This method will inflate the custom layout and return as viewholder
        LayoutInflater mInflater = LayoutInflater.from(viewGroup.getContext());

        ViewGroup mainGroup = (ViewGroup) mInflater.inflate(
                R.layout.view_rv_item, viewGroup, false);
        GuruHolder listHolder = new GuruHolder(mainGroup);
        return listHolder;
    }

}
