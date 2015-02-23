package com.co.menu.restaurante.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.co.menu.restaurante.R;
import com.co.menu.restaurante.models.CategoryItem;
import com.co.menu.restaurante.models.Item;
import com.co.menu.restaurante.models.SubCategories;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by esandoval on 21/02/15.
 */
public class AdapterCategories extends ArrayAdapter<Item>{

    private Context context;
    private ArrayList<Item> items;
    private LayoutInflater vi;

    public AdapterCategories(Context context, ArrayList<Item> items) {
        super(context,0, items);
        this.context = context;
        this.items = items;
        vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Item getItem(int position) {
        return items.get(position);
    }

    public List<Item> getItemData() {
        return items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        final Item i = items.get(position);
        if (i != null) {
            if(i.isSection()){
                CategoryItem si = (CategoryItem)i;
                v = vi.inflate(R.layout.row_category_view, null);

                v.setOnClickListener(null);
                v.setOnLongClickListener(null);
                v.setLongClickable(false);

                final TextView TextCategory = (TextView) v.findViewById(R.id.text_category);
                //final ImageView imgCategory = (ImageView) v.findViewById(R.id.img_category);

                if (si.getUrlImage() != null) {
                    ImageView img_contact = (ImageView) v.findViewById(R.id.img_category);
                    UrlImageViewHelper.setUrlDrawable(img_contact, si.getUrlImage());
                    si.setImage(img_contact.getDrawingCache());
                }
                else{
                    ImageView img_contact = (ImageView) v.findViewById(R.id.img_category);
                    img_contact.setImageResource(R.drawable.ic_drawer);
                }


                TextCategory.setText(si.getNameCategory());
            }else{
                SubCategories subCategories = (SubCategories)i;
                v = vi.inflate(R.layout.row_subcategory, null);

                final TextView mTextNamesubcategory = (TextView)v.findViewById(R.id.text_subcategory);
                final TextView mId_subcategory = (TextView) v.findViewById(R.id.text_idSubcategory);

                mTextNamesubcategory.setText(subCategories.getNameSubCategory());
                mId_subcategory.setText(subCategories.getIdSubCategory());

            }
        }
        return v;
    }
}
