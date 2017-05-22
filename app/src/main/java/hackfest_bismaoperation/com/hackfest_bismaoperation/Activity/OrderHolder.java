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

    TextView tv_idpengajarO;
    TextView tv_namadepanO;
    TextView tv_namabelakangO;
    TextView tv_telponO;
    TextView tv_emailO;
    TextView tv_tanggallahirO;
    TextView tv_tempatlahirO;
    TextView tv_jeniskelaminO;
    TextView tv_hargaO;
    TextView tv_alamat;
    TextView tv_email;
    ImageView tv_profil;
    TextView tv_status;
    TextView tv_riwayatpendidikan;
    TextView mata_pelajaran;
    RelativeLayout item;

    public OrderHolder(View view){
        super(view);
        tv_idpengajarO=(TextView) view.findViewById(R.id.tv_ido);
        this.tv_namadepanO = (TextView) view.findViewById(R.id.tv_namabelakango);
        this.tv_idpengajarO=(TextView)view.findViewById(R.id.tv_id);
    //    tv_namabelakangO=(TextView)view.findViewById(R.id.input_namabelakang);
        tv_telponO=(TextView)view.findViewById(R.id.tv_tlp);
        tv_emailO=(TextView)view.findViewById(R.id.tv_email);
        tv_tanggallahirO=(TextView)view.findViewById(R.id.txtTanggalLahir);
        tv_tempatlahirO=(TextView)view.findViewById(R.id.tv_tempatlahir);
        tv_jeniskelaminO=(TextView)view.findViewById(R.id.tv_jeniskelamin);
        tv_hargaO=(TextView)view.findViewById(R.id.tv_harga);
        tv_alamat=(TextView)view.findViewById(R.id.txtAlamat);

    }

}
