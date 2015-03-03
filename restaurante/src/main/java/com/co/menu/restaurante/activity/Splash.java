package com.co.menu.restaurante.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.co.menu.restaurante.R;
import com.co.menu.restaurante.app.Application;
import com.co.menu.restaurante.bl.MenuBL;
import com.co.menu.restaurante.models.MenuData;
import com.co.menu.restaurante.models.MenuItems;
import com.co.menu.restaurante.models.MenuRequests;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class Splash extends Activity implements MenuBL.menuBlListener{

    private MenuBL menuBL;

    private boolean isTablet=false;
    Application globalVariableTablet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        globalVariableTablet = (Application) getApplicationContext();
        isTablet = isTabletDevice(getApplicationContext());
        globalVariableTablet.setIsTablet(isTablet);

        if(isTablet==false){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        }else{
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        }

        menuBL = new MenuBL();
        menuBL.setListener(Splash.this);
        menuBL.RequestMenu();

        Handler sp = new Handler();
        sp.postDelayed(new SplashHandler(), 2000);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void errorMenu(String error) {
        System.out.println("error: "+error.toString());
    }

    @Override
    public void successMenu(MenuItems items) {
        System.out.println("informacion del ws full");
    }

    class SplashHandler implements Runnable {
        public void run() {
            LauncherActivity(ListCategoriesActivity.class);
        }
    }

    @SuppressWarnings("rawtypes")
    private void LauncherActivity(Class activity) {
        Intent intent = new Intent(getApplication(), activity);
        startActivity(intent);
        finish();
//        overridePendingTransition(
//                co.com.widetech.mobile.reporter.R.anim.fadein,
//                co.com.widetech.mobile.reporter.R.anim.fadeout);
    }

    private void printJson(){
        Gson gson = new Gson();
        List<MenuRequests> requestsList = new ArrayList<MenuRequests>();

        MenuRequests requests = null;

        MenuItems items = new MenuItems();
        items.setId("");
        items.setName("");
        items.setTimestamp((long)1111111);

        int sizeRequests = 2;
        for (int i = 0; i < sizeRequests; i++) {
            List<MenuData> dataList = new ArrayList<MenuData>();
            requests = new MenuRequests();
            requests.setCollectionId("");
            requests.setId("");
            requests.setName("");
            requests.setDescription("");
            requests.setUrl("");
            requests.setMethod("");
            requests.setHeaders("");

            int sizeData = 2;
            for (int j=0; j < sizeData; j++){
                MenuData data = new MenuData();
                data.setKey("");
                data.setValue("");
                data.setType("");
                dataList.add(data);
                requests.setData(dataList);
            }
            requests.setDataMode("");
            requests.setTimestamp((long)23456789);
            requests.setVersion((long)22222);
            requestsList.add(requests);
            items.setRequests(requestsList);
        }

        Log.d("Objeto formato Json", gson.toJson(
                new MenuItems(
                        "prueba",
                        "prueba",
                        ((long) 1223),
                        requestsList
                )));
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
