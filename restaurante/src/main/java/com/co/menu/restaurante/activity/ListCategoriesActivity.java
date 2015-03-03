package com.co.menu.restaurante.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.co.menu.restaurante.R;
import com.co.menu.restaurante.adapter.AdapterCategories;
import com.co.menu.restaurante.app.Application;
import com.co.menu.restaurante.bl.CategoriesBL;
import com.co.menu.restaurante.bl.DetailsBL;
import com.co.menu.restaurante.bl.MenuBL;
import com.co.menu.restaurante.entities.CategoriesSp;
import com.co.menu.restaurante.entities.CategoriesSpResponse;
import com.co.menu.restaurante.entities.CategoriesSpResponseSubcategory;
import com.co.menu.restaurante.entities.MenuSpResponse;
import com.co.menu.restaurante.entities.MenuSpResponseData;
import com.co.menu.restaurante.entities.MenuSpResponseRequests;
import com.co.menu.restaurante.models.CategoryItem;
import com.co.menu.restaurante.models.Item;
import com.co.menu.restaurante.models.MenuData;
import com.co.menu.restaurante.models.MenuItems;
import com.co.menu.restaurante.models.MenuRequests;
import com.co.menu.restaurante.models.SubCategories;
import com.co.menu.restaurante.net.MakeHttpRequest;
import com.co.menu.restaurante.tools.Constants;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dao.com.co.greendao.Category;
import dao.com.co.greendao.DBHelper;
import dao.com.co.greendao.DaoMaster;
import dao.com.co.greendao.DaoSession;
import dao.com.co.greendao.Details;
import dao.com.co.greendao.SubCategory;

public class ListCategoriesActivity extends ActionBarActivity implements CategoriesBL.CategoriesBlListener,
        DetailsBL.DetailsListener{

    Context context;
    CategoriesBL categoriesBL;
    DetailsBL detailsBL;
    private DBHelper dbHelper;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private DaoMaster.DevOpenHelper devOpenHelper;
    private static SQLiteDatabase db;

    private boolean isTablet=false;
    Application globalVariableTablet;

    private ActionBar actionBar;

    private List<Category> categoryList;
    private List<SubCategory> subCategoryList;

    private ListView listCategory;
    private AdapterCategories adapterCategories;

    private ArrayList<Item> item_List = new ArrayList<Item>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getApplicationContext();

        globalVariableTablet = (Application) getApplicationContext();
        isTablet = isTabletDevice(getApplicationContext());
        globalVariableTablet.setIsTablet(isTablet);

        if(isTablet==false){
            //sensorLandscape
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
            setContentView(R.layout.activity_list_menu);
        }else{
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
            setContentView(R.layout.activity_list_menu_tablet);
        }

        listCategory = (ListView) findViewById(R.id.list_Category);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Menu Principal");
        actionBar.setHomeButtonEnabled(true);
        actionBar.setSubtitle("y Submenu");
        actionBar.setDisplayHomeAsUpEnabled(true);

        initDB();

        listCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SubCategories subCategory = (SubCategories) parent.getItemAtPosition(position);
                String id_sub = subCategory.getIdSubCategory();
                String name_Sub = subCategory.getNameSubCategory();
                Intent intent = new Intent();
                intent.putExtra("id_subCategory",id_sub);
                intent.putExtra("name_subCategory",name_Sub);
                intent.setClass(context,DescriptionActivity.class);
                startActivity(intent);

            }
        });
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
        categoryList = new ArrayList<Category>();
        subCategoryList = new ArrayList<SubCategory>();

        categoryList = dbHelper.getCategory();

        if(categoryList.size() >= 1){
            loadItemCategory();
        }else{
            launcherCategoriesBL();
        }

    }

    private void launcherCategoriesBL(){
        dbHelper.deleteCategory();
        dbHelper.deleteDetails();
        dbHelper.deleteSubCategory();
        categoriesBL = new CategoriesBL();
        categoriesBL.setListener(this);
        categoriesBL.requestCategories(context);
    }

    @Override
    public void errorCategory(String error) {

    }

    @Override
    public void successCategory(String response) {
        if(response.equalsIgnoreCase("OK")) {
            loadItemCategory();
            loadDetails();
        }
    }

    private void loadItemCategory(){
        categoryList.clear();
        subCategoryList.clear();
        item_List.clear();
        adapterCategories = new AdapterCategories(ListCategoriesActivity.this, item_List);
        adapterCategories.clear();

        categoryList = dbHelper.getCategory();
        subCategoryList = dbHelper.getSubCategory();
        int cant_category = categoryList.size();
        int cant_subcategory = subCategoryList.size();

        if (cant_category >= 1) {
            for (int i = 0; i < cant_category; i++) {

                String id_category = categoryList.get(i).getCategory_id();
                String name = categoryList.get(i).getNameCategory();
                String urlImage = categoryList.get(i).getUrlImage();
                item_List.add(new CategoryItem(name,urlImage));

                for (int j=0;j<cant_subcategory;j++){

                    String id_sub = subCategoryList.get(j).getSubCategory_id();

                    if(id_sub.equalsIgnoreCase(id_category)) {

                        String subCategory = subCategoryList.get(j).getSubCategory();
                        item_List.add(new SubCategories(subCategory, id_sub));
                        adapterCategories = new AdapterCategories(ListCategoriesActivity.this, item_List);
                    }
                }
                adapterCategories = new AdapterCategories(ListCategoriesActivity.this, item_List);
            }

            listCategory.setAdapter(adapterCategories);
            adapterCategories.notifyDataSetChanged();
        }

    }

    private void loadDetails(){
        detailsBL = new DetailsBL();
        detailsBL.setListener(this);
        detailsBL.requestDetails(context);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_menu, menu);
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
            launcherCategoriesBL();
        }

        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                this.finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void printJson(){
        Gson gson = new Gson();
        CategoriesSpResponse cResponse;
        CategoriesSpResponseSubcategory cSubcategory;
        List<CategoriesSpResponseSubcategory> subcategories;

        int sizeCategory = 2;
        for (int j=0; j<sizeCategory;j++){
            subcategories = new ArrayList<CategoriesSpResponseSubcategory>();
            cResponse = new CategoriesSpResponse();
            cResponse.setId("1");
            cResponse.setName("ANTIPASTI");
            cResponse.setImg_path("img/categories/1.jpg");
            cResponse.setType_id("1");
            cResponse.setEnabled("1");
            cResponse.setCreated_at("0000-00-00 00:00:00");
            cResponse.setUpdated_at("0000-00-00 00:00:00");

            int sizeSubcategory = 2;
            for (int i=0; i<sizeSubcategory;i++){
                cSubcategory = new CategoriesSpResponseSubcategory();
                cSubcategory.setId("2");
                cSubcategory.setName("2");
                cSubcategory.setCategory_id("2");
                cSubcategory.setAddition_enable("2");
                cSubcategory.setEnabled("2");
                cSubcategory.setCreated_at("2");
                cSubcategory.setUpdated_at("2");
                subcategories.add(cSubcategory);
                //cResponse.setSubcategory(subcategories);
            }
            //cListResponse.add(cResponse);

            Log.d("PRUEBA JSON", gson.toJson(
                    new CategoriesSpResponse(
                            cResponse.getId(),
                            cResponse.getName(),
                            cResponse.getImg_path(),
                            cResponse.getType_id(),
                            cResponse.getEnabled(),
                            cResponse.getCreated_at(),
                            cResponse.getUpdated_at(),
                            subcategories)));
        }


    }

    @Override
    public void successDetails(String response) {
        Toast.makeText(context,response,Toast.LENGTH_LONG).show();
    }

    @Override
    public void errorDetails(String error) {
        Toast.makeText(context,error,Toast.LENGTH_LONG).show();
    }

    public boolean isTabletDevice(Context activityContext) {
        boolean device_large = ((activityContext.getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) ==
                Configuration.SCREENLAYOUT_SIZE_LARGE);

        if (device_large) {
            DisplayMetrics metrics = new DisplayMetrics();
            //         Activity activity = (Activity) context;
            this.getWindowManager().getDefaultDisplay().getMetrics(metrics);

            if (metrics.densityDpi == DisplayMetrics.DENSITY_DEFAULT
                    || metrics.densityDpi == DisplayMetrics.DENSITY_HIGH
                    || metrics.densityDpi == DisplayMetrics.DENSITY_MEDIUM
                    || metrics.densityDpi == DisplayMetrics.DENSITY_TV
                    || metrics.densityDpi == DisplayMetrics.DENSITY_XHIGH) {
                //    AppInstance.getLogger().logD("DeviceHelper","IsTabletDevice-True");
                return true;
            }
        }
        //  AppInstance.getLogger().logD("DeviceHelper","IsTabletDevice-False");
        return false;
    }
}
