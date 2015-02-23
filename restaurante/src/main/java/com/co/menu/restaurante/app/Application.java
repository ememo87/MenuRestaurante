package com.co.menu.restaurante.app;

import android.content.Context;

import com.co.menu.restaurante.tools.Constants;

import dao.com.co.greendao.DaoMaster;
import dao.com.co.greendao.DaoSession;
import retrofit.RestAdapter;

/**
 * Created by esandoval on 21/02/15.
 */
public class Application extends android.app.Application {

    private static Context context;
    private static RestAdapter restAdapterMenu;
    private static RestAdapter restAdapterCategoryDetails;
    private static RestAdapter restAdapterCategories;

    private static DaoMaster daoMaster=null;
    private static DaoSession daoSession=null;

    public Application() {
        super();
    }

    @Override
    public void onCreate() {
        context = getApplicationContext();
        restAdapterMenu = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setServer(Constants.URL_GETPOSTMAN)
                .build();

        restAdapterCategories = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setServer(Constants.URL_CATEGORY)
                .build();

        restAdapterCategoryDetails = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setServer(Constants.URL_CATEGORY)
                .build();
    }

    public static RestAdapter getRestAdapterrestAdapterMenu() {
        return restAdapterMenu;
    }

    public static RestAdapter getRestAdapterCategoryDetails() {
        return restAdapterCategoryDetails;
    }

    public static RestAdapter getRestAdapterCategories() {
        return restAdapterCategories;
    }

    public static Context getContext() {
        // TODO Auto-generated method stub
        return context;
    }

    @Override
    public Context getApplicationContext() {
        // TODO Auto-generated method stub
        return super.getApplicationContext();
    }

    public void onTerminate() {
        super.onTerminate();

    }

    public static DaoMaster getDaoMaster(Context context)
    {
        if (daoMaster == null)
        {
            DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context, Constants.DATABASE_NAME, null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());

        }
        return daoMaster;
    }

    public static DaoSession getDaoSession(Context context)
    {
        if (daoSession == null)
        {
            if (daoMaster == null)
            {
                daoMaster = getDaoMaster(context);
            }
            daoSession = daoMaster.newSession();

        }
        return daoSession;
    }

}
