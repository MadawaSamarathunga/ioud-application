package com.nextapp.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nextapp.R;
import com.nextapp.activity_view_customer_complains;
import com.nextapp.dto.AuthCallResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ComplainListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<AuthCallResponse> complainList;

    public ComplainListAdapter(Context context, int layout, List<AuthCallResponse> complainList) {
        this.context = context;
        this.layout = layout;
        this.complainList = complainList;
    }

    @Override
    public int getCount() {
        return complainList.size();
    }

    @Override
    public Object getItem(int i) {
        return complainList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder {
        TextView lblUser, lblDate, lblStatus, lblPestName, lblContactNo, lblTemp;
        Button btnMarkCompleted;
        ImageView imgPest;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View row = view;
        ViewHolder holder = new ViewHolder();

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.lblUser = (TextView) row.findViewById(R.id.lblUser);
            holder.lblDate = (TextView) row.findViewById(R.id.lblDate);
            holder.lblStatus = (TextView) row.findViewById(R.id.lblStatus);
            holder.lblPestName = (TextView) row.findViewById(R.id.lblPestName);
            holder.lblContactNo = (TextView) row.findViewById(R.id.lblContactNo);
            holder.lblTemp = (TextView) row.findViewById(R.id.lblTemp);
            holder.imgPest = (ImageView) row.findViewById(R.id.imgPest);
            holder.btnMarkCompleted = (Button) row.findViewById(R.id.btnMarkCompleted);
            row.setTag(holder);

        } else {
            holder = (ViewHolder) row.getTag();
        }

        AuthCallResponse pest = complainList.get(position);

        holder.lblUser.setText(pest.getRequestBy());
        holder.lblDate.setText(pest.getRequestDate().substring(0, 10));
        holder.lblStatus.setText(pest.getStatus());
        holder.lblPestName.setText(pest.getDetectedPest());
        holder.lblContactNo.setText(pest.getRequestUserNumber());
        holder.lblTemp.setText(pest.getTemperature());
        holder.btnMarkCompleted.setOnClickListener(pest.getBtnClickListener());
        holder.btnMarkCompleted.setEnabled((pest.getStatus().equalsIgnoreCase("Completed")));
        holder.btnMarkCompleted.setAlpha((pest.getStatus().equalsIgnoreCase("Completed")) ? 0.5f : 1);


        Picasso.get().load("http://192.168.1.4:8000/" + pest.getPestImage()).into(holder.imgPest);
        return row;
    }

}
