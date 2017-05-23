package hackfest_bismaoperation.com.hackfest_bismaoperation.Activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import hackfest_bismaoperation.com.hackfest_bismaoperation.R;


/**
 * Created by Ryan Bagus Susilo on 5/21/2017.
 */
public class OrderHolder extends RecyclerView.ViewHolder {

    TextView tvid, tvtit, tvsub, tv_telpon, tv_email, tv_tanggallahir, tv_tempatlahir, tv_jeniskelamin,
            tv_namabelakang, tv_harga, tv_alamat, foto2, tv_status, tv_title, matapelajaran, id_order;


    ImageView foto;
//    TextView tv_status;
//    TextView tv_riwayatpendidikan;
//    TextView mata_pelajaran;
    RelativeLayout item;

    public OrderHolder(View view){
        super(view);

        this.tvtit = (TextView) view.findViewById(R.id.tv_namabelakango);
        this.tvid=(TextView)view.findViewById(R.id.tv_ido);
        tv_namabelakang=(TextView)view.findViewById(R.id.tv_namabelakango);
        tv_telpon=(TextView)view.findViewById(R.id.tv_tlpo);
        tv_email=(TextView)view.findViewById(R.id.tv_emailo);
        tv_tanggallahir=(TextView)view.findViewById(R.id.tv_tanggallahiro);
        tv_tempatlahir=(TextView)view.findViewById(R.id.tv_tempatlahiro);
        tv_jeniskelamin=(TextView)view.findViewById(R.id.tv_jeniskelamino);
        tv_harga=(TextView)view.findViewById(R.id.tv_hargao);
        tvsub=(TextView)view.findViewById(R.id.tv_subtitleo);
        item = (RelativeLayout)view.findViewById(R.id.itemOrderGuru);
        tv_status = (TextView)view.findViewById(R.id.tv_statuso);
        foto2=(TextView)view.findViewById(R.id.tv_tempo);
        foto=(ImageView)view.findViewById(R.id.icono);
        tv_title=(TextView)view.findViewById(R.id.tv_titleo);
        tv_alamat=(TextView)view.findViewById(R.id.tv_alamato);
        matapelajaran=(TextView)view.findViewById(R.id.tv_matapelajarano);
        id_order=(TextView)view.findViewById(R.id.tv_id_ordero);
      //  tv_alamat=(TextView)view.findViewById(R.id.ala);

    }

}
