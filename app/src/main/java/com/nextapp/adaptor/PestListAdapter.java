package com.nextapp.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nextapp.R;
import com.nextapp.dto.Pest;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PestListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Pest> pestList;

    public PestListAdapter(Context context, int layout, List<Pest> pestList) {
        this.context = context;
        this.layout = layout;
        this.pestList = pestList;
    }

    @Override
    public int getCount() {
        return pestList.size();
    }

    @Override
    public Object getItem(int i) {
        return pestList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder{
        TextView pestName, pestScientificName, pestClassification, pestDescription;
        ImageView pestImage;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout,null);

            holder.pestName = (TextView) row.findViewById(R.id.itemName);
            holder.pestScientificName = (TextView) row.findViewById(R.id.itemSubtitle);
            holder.pestClassification = (TextView) row.findViewById(R.id.itemSubtitle2);
            holder.pestDescription = (TextView) row.findViewById(R.id.itemDescription);
            holder.pestImage = (ImageView) row.findViewById(R.id.itemImage);
            row.setTag(holder);

        }else {
            holder = (ViewHolder) row.getTag();
        }

        Pest pest = pestList.get(position);

        holder.pestName.setText(pest.getPestName());
        holder.pestScientificName.setText(pest.getPestScientificName());
        holder.pestClassification.setText(pest.getPestClassification());
        holder.pestDescription.setText(pest.getPestDescription());
//        holder.pestImage.setImageBitmap(getBitmapFromURL(pest.getPestImage()));
        Picasso.get().load(pest.getPestImage()).into(holder.pestImage);
        return row;
    }

}
