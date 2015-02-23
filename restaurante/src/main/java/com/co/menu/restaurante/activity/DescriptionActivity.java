package com.co.menu.restaurante.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.co.menu.restaurante.R;
import com.co.menu.restaurante.adapter.AdapterDetails;
import com.co.menu.restaurante.app.Application;
import com.co.menu.restaurante.models.DetailsItem;
import com.co.menu.restaurante.tools.Constants;

import java.util.ArrayList;
import java.util.List;

import dao.com.co.greendao.DBHelper;
import dao.com.co.greendao.DaoMaster;
import dao.com.co.greendao.DaoSession;
import dao.com.co.greendao.Details;

public class DescriptionActivity extends ActionBarActivity {

    private ListView mListDescription;
    private String id_subCategory;
    private String name_subCategory;
    private List<Details> detailsList;
    private List<DetailsItem> detailsItemList;
    private DetailsItem detailsItem;
    private AdapterDetails adapterDetails;

    private ActionBar actionBar;

    Context context ;
    DBHelper dbHelper;
    DaoMaster daoMaster;
    DaoSession daoSession;
    DaoMaster.DevOpenHelper devOpenHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        context = getApplicationContext();

        actionBar = getSupportActionBar();


        id_subCategory = getIntent().getStringExtra("id_subCategory");
        name_subCategory = getIntent().getStringExtra("name_subCategory");

        actionBar.setTitle(name_subCategory);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setSubtitle("Descripción");
        actionBar.setDisplayHomeAsUpEnabled(true);

        initDB();
        detailsList = new ArrayList<Details>();
        detailsItemList = new ArrayList<DetailsItem>();


        mListDescription = (ListView) findViewById(R.id.listDescription);
        mListDescription.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialogAlertDelete( parent, position);
            }
        });

        loadDetails();
    }

    private void initDB(){

        dbHelper = new DBHelper();
        dbHelper.getInstance(context);
        devOpenHelper = new DaoMaster.DevOpenHelper(context,
                Constants.DATABASE_NAME, null);
        db = devOpenHelper.getWritableDatabase();
        daoMaster= Application.getDaoMaster(context);
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    private void loadDetails(){
        Boolean status = false;
        detailsList.clear();
        detailsList = dbHelper.getDetails();
        int sizeDetails = detailsList.size();
        for (int i=0; i<sizeDetails; i++){
            if(id_subCategory.equalsIgnoreCase(detailsList.get(i).getItem_subcategory_id())){
                detailsItem = new DetailsItem();
                detailsItem.setName_details(detailsList.get(i).getName_details());
                detailsItem.setDescription(detailsList.get(i).getDescription());
                detailsItem.setUrlImage_items(detailsList.get(i).getUrlImage_items());
                detailsItem.setUrlImage_left(detailsList.get(i).getUrlImage_left());
                detailsItem.setUrlImage_right(detailsList.get(i).getUrlImage_right());

                detailsItemList.add(detailsItem);
                status = true;
            }
        }
        if(status){
            adapterDetails = new AdapterDetails(context, detailsItemList);
            mListDescription.setAdapter(adapterDetails);
            adapterDetails.notifyDataSetChanged();
        }else{
            Toast.makeText(context,"De Momento no hay Detalles para este Plato",Toast.LENGTH_LONG).show();
            this.finish();
        }


    }

    private void dialogAlertDelete(AdapterView<?> parent, int position){
        DetailsItem detailsItem1 = (DetailsItem) parent.getItemAtPosition(position);

        AlertDialog alertDialog1 = new AlertDialog.Builder(this).create();
        alertDialog1.setCancelable(false);
        alertDialog1.setTitle(detailsItem1.getName_details());
        alertDialog1.setMessage(detailsItem1.getDescription());
        alertDialog1.setIcon(R.mipmap.ic_launcher);
        alertDialog1.setButton("Accept", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context,"Su Orden esta en Proceso",Toast.LENGTH_LONG).show();
                DescriptionActivity.this.finish();
            }
        });

        alertDialog1.setButton2("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog1.show();



//        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setTitle(detailsItem1.getName_details());
//        builder.setMessage(detailsItem1.getDescription());
//        builder.setCancelable(false);
//        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });
//        builder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(context,"Su Orden esta en Proceso",Toast.LENGTH_LONG).show();
//            }
//        });
//        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_description, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            this.finish();
        }
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                this.finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
