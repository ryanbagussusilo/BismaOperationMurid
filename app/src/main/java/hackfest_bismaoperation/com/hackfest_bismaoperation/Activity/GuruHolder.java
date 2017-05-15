package hackfest_bismaoperation.com.hackfest_bismaoperation.Activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import hackfest_bismaoperation.com.hackfest_bismaoperation.R;

/**
 * Created by Ryan Bagus Susilo on 5/12/2017.
 */
public class GuruHolder extends RecyclerView.ViewHolder{

     TextView tvsub,tvid;
     TextView tvtit;
     TextView tvStatus;
     TextView tv_telpon;
     TextView tv_email;
     TextView tv_tanggallahir;
     TextView tv_tempatlahir;
     TextView tv_jeniskelamin;
     TextView tv_namabelakang;
     TextView tv_harga;
    RelativeLayout item;

    public GuruHolder(View view)
    {
        super(view);
        this.tvid = (TextView) view.findViewById(R.id.tv_id);
        this.tvsub = (TextView) view.findViewById(R.id.tv_subtitle);
        this.tvtit = (TextView) view.findViewById(R.id.tv_title);
        this.tvStatus=(TextView) view.findViewById(R.id.tv_status);
        this.tv_telpon=(TextView)view.findViewById(R.id.tv_tlp);
        this.tv_email=(TextView) view.findViewById(R.id.tv_email);
        this.tv_tanggallahir=(TextView) view.findViewById(R.id.tv_tanggallahir);
        this.tv_tempatlahir=(TextView) view.findViewById(R.id.tv_tempatlahir);
        this.tv_jeniskelamin=(TextView)view.findViewById(R.id.tv_jeniskelamin);
        this.tv_namabelakang=(TextView) view.findViewById(R.id.tv_namabelakang);
        this.tv_harga=(TextView) view.findViewById(R.id.tv_harga);
        this.item = (RelativeLayout) view.findViewById(R.id.itemGuru);

    }
}
