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
                R.layout.view_rv_item, viewGroup, false);
        OrderHolder listHolder = new OrderHolder(mainGroup);
        return listHolder;
    }

    @Override
    public void onBindViewHolder(OrderHolder holder, int position) {

    }


}
