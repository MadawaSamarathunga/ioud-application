package com.nextapp.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nextapp.R;
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

    private class ViewHolder{
        TextView lblUser, lblDate, lblStatus, lblPestName;
        ImageView imgPest;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout,null);

            holder.lblUser = (TextView) row.findViewById(R.id.lblUser);
            holder.lblDate = (TextView) row.findViewById(R.id.lblDate);
            holder.lblStatus = (TextView) row.findViewById(R.id.lblStatus);
            holder.lblPestName = (TextView) row.findViewById(R.id.lblPestName);
            holder.imgPest = (ImageView) row.findViewById(R.id.imgPest);
            row.setTag(holder);

        }else {
            holder = (ViewHolder) row.getTag();
        }

        AuthCallResponse pest = complainList.get(position);

        holder.lblUser.setText(pest.getRequestedUser());
        holder.lblDate.setText(pest.getRequestedDate());
        holder.lblStatus.setText(pest.getStatus());
        holder.lblPestName.setText(pest.getDetectedPest());
        Picasso.get().load(pest.getPestImage()).into(holder.imgPest);
        return row;
    }

}
