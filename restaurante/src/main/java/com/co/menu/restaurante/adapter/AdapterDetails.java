package com.co.menu.restaurante.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.co.menu.restaurante.R;
import com.co.menu.restaurante.models.DetailsItem;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import java.util.List;
/**
 * Created by esandoval on 22/02/15.
 */
public class AdapterDetails extends ArrayAdapter{
    private final Context context;
    private List<DetailsItem> objDetails;

    public AdapterDetails(Context context, List<DetailsItem> objects) {
        super(context, R.layout.row_details, objects);
        this.context = context;
        this.objDetails = objects;
    }

    @Override
    public int getCount() {
        return objDetails.size();
    }

    @Override
    public Object getItem(int position) {
        return objDetails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return objDetails.get(position).hashCode();
    }

    static class ViewHolder{
        private TextView mTextTittle;
        private TextView mTextDescription;
        private ImageView mImgItems;
        private ImageView mImgLeft;
        private ImageView mImgRight;

        public ViewHolder(View root) {
            mTextTittle = (TextView) root.findViewById(R.id.text_tittle_category);
            mTextDescription = (TextView) root.findViewById(R.id.text_description);
            mImgItems = (ImageView) root.findViewById(R.id.img_items);
            mImgLeft = (ImageView) root.findViewById(R.id.img_left);
            mImgRight = (ImageView) root.findViewById(R.id.img_right);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view= convertView;
        ViewHolder holder;
        if(view == null){
            LayoutInflater inflater= LayoutInflater.from(context);
            view = inflater.inflate(R.layout.row_details, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        holder = (ViewHolder) view.getTag();
        DetailsItem details = objDetails.get(position);
        holder.mTextTittle.setText(details.getName_details());
        holder.mTextDescription.setText(details.getDescription());

        if (details.getUrlImage_items() != null) {
            UrlImageViewHelper.setUrlDrawable(holder.mImgItems, details.getUrlImage_items());
            details.setBitmapItems(holder.mImgItems.getDrawingCache());
        }
        else{
            holder.mImgItems.setImageResource(R.drawable.imaginamos);
        }

        if (details.getUrlImage_left() != null) {
            UrlImageViewHelper.setUrlDrawable(holder.mImgLeft, details.getUrlImage_left());
            details.setBitmapLeft(holder.mImgLeft.getDrawingCache());
        }
        else{
            holder.mImgLeft.setImageResource(R.drawable.imaginamos);
        }

        if (details.getUrlImage_right() != null) {
            UrlImageViewHelper.setUrlDrawable(holder.mImgRight, details.getUrlImage_right());
            details.setBitmapRight(holder.mImgRight.getDrawingCache());
        }
        else{
            holder.mImgRight.setImageResource(R.drawable.imaginamos);
        }

        return view;
    }
}
