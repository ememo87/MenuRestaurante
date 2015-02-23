package com.co.menu.restaurante.bl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.co.menu.restaurante.app.Application;
import com.co.menu.restaurante.entities.DetailsSpResponse;
import com.co.menu.restaurante.entities.DetailsSpResponseItems;
import com.co.menu.restaurante.entities.DetailsSpResponseSubcategory;
import com.co.menu.restaurante.tools.Constants;
import com.co.menu.restaurante.webservice.DetailsService;

import java.util.ArrayList;
import java.util.List;

import dao.com.co.greendao.DBHelper;
import dao.com.co.greendao.DaoMaster;
import dao.com.co.greendao.DaoSession;
import dao.com.co.greendao.Details;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by esandoval on 22/02/15.
 */
public class DetailsBL {

    Context context ;
    DBHelper dbHelper;
    DaoMaster daoMaster;
    DaoSession daoSession;
    DaoMaster.DevOpenHelper devOpenHelper;
    SQLiteDatabase db;

    DetailsSpResponse detailsSpResponse;
    DetailsSpResponseSubcategory detailsSpSubcategory;
    DetailsSpResponseItems detailsSpItems;

    List<DetailsSpResponse> detailsSpResponseList = new ArrayList<DetailsSpResponse>();
    List<DetailsSpResponseSubcategory> detailsSpResponseSubcategoryList;
    List<DetailsSpResponseItems> detailsSpResponseItemsList;

    private DetailsListener listener;


    public void requestDetails(Context context){
        this.context = context;
        initDB();
        DetailsService service = Application.getRestAdapterCategoryDetails().create(DetailsService.class);
        service.details(new Callback<DetailsSpResponse>() {
            @Override
            public void success(DetailsSpResponse detailsSpResponses, Response response) {

                detailsSpResponseSubcategoryList = new ArrayList<DetailsSpResponseSubcategory>();

                detailsSpResponse = new DetailsSpResponse();
                detailsSpResponse.setId(detailsSpResponses.getId());
                detailsSpResponse.setName(detailsSpResponses.getName());
                detailsSpResponse.setImg_path(detailsSpResponses.getImg_path());
                detailsSpResponse.setType_id(detailsSpResponses.getType_id());
                detailsSpResponse.setEnabled(detailsSpResponses.getEnabled());
                detailsSpResponse.setCreated_at(detailsSpResponses.getCreated_at());
                detailsSpResponse.setUpdated_at(detailsSpResponses.getUpdated_at());

                int sizeSubC = detailsSpResponses.getSubcategory().size();
                for (int i=0;i<sizeSubC;i++){
                    detailsSpResponseItemsList = new ArrayList<DetailsSpResponseItems>();
                    detailsSpSubcategory = new DetailsSpResponseSubcategory();
                    detailsSpSubcategory.setId(detailsSpResponses.getSubcategory().get(i).getId());
                    detailsSpSubcategory.setName(detailsSpResponses.getSubcategory().get(i).getName());
                    detailsSpSubcategory.setCategory_id(detailsSpResponses.getSubcategory().get(i).getCategory_id());
                    detailsSpSubcategory.setAddition_enable(detailsSpResponses.getSubcategory().get(i).getAddition_enable());
                    detailsSpSubcategory.setEnabled(detailsSpResponses.getSubcategory().get(i).getEnabled());
                    detailsSpSubcategory.setCreated_at(detailsSpResponses.getSubcategory().get(i).getCreated_at());
                    detailsSpSubcategory.setUpdated_at(detailsSpResponses.getSubcategory().get(i).getUpdated_at());

                    int sizeItem = detailsSpResponses.getSubcategory().get(i).getItems().size();
                    for (int j=0;j<sizeItem;j++){
                        detailsSpItems = new DetailsSpResponseItems();
                        detailsSpItems.setId(detailsSpResponses.getSubcategory().get(i).getItems().get(j).getId());
                        detailsSpItems.setName(detailsSpResponses.getSubcategory().get(i).getItems().get(j).getName());
                        detailsSpItems.setDescription(detailsSpResponses.getSubcategory().get(i).getItems().get(j).getDescription());
                        detailsSpItems.setImg_path(detailsSpResponses.getSubcategory().get(i).getItems().get(j).getImg_path());
                        detailsSpItems.setLeft_img_path(detailsSpResponses.getSubcategory().get(i).getItems().get(j).getLeft_img_path());
                        detailsSpItems.setRight_img_path(detailsSpResponses.getSubcategory().get(i).getItems().get(j).getRight_img_path());
                        detailsSpItems.setSubcategory_id(detailsSpResponses.getSubcategory().get(i).getItems().get(j).getSubcategory_id());
                        detailsSpItems.setEnabled(detailsSpResponses.getSubcategory().get(i).getItems().get(j).getEnabled());
                        detailsSpItems.setCreated_at(detailsSpResponses.getSubcategory().get(i).getItems().get(j).getCreated_at());
                        detailsSpItems.setUpdated_at(detailsSpResponses.getSubcategory().get(i).getItems().get(j).getUpdated_at());
                        detailsSpResponseItemsList.add(detailsSpItems);
                    }
                    detailsSpSubcategory.setItems(detailsSpResponseItemsList);
                    detailsSpResponseSubcategoryList.add(detailsSpSubcategory);
                }
                detailsSpResponse.setSubcategory(detailsSpResponseSubcategoryList);
                detailsSpResponseList.add(detailsSpResponse);

                successResponse(detailsSpResponse);

            }

            @Override
            public void failure(RetrofitError retrofitError) {

                listener.errorDetails("error "+retrofitError.getUrl().toString());
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
    }

    private void successResponse(DetailsSpResponse responses){
        Details details = null;
        List<Details> detailsList = new ArrayList<Details>();

        int sizeubcategory = responses.getSubcategory().size();
        for (int i=0; i<sizeubcategory;i++){

            int sizeItem = responses.getSubcategory().get(i).getItems().size();
            for (int j=0; j<sizeItem; j++){
                details = new Details();
                details.setId_details(responses.getSubcategory().get(i).getItems().get(j).getId());
                details.setName_details(responses.getSubcategory().get(i).getItems().get(j).getName());
                details.setDescription(responses.getSubcategory().get(i).getItems().get(j).getDescription());
                details.setUrlImage_items(Constants.URL_CATEGORY + responses.getSubcategory().get(i).getItems().get(j).getImg_path());
                details.setUrlImage_left(Constants.URL_CATEGORY + responses.getSubcategory().get(i).getItems().get(j).getLeft_img_path());
                details.setUrlImage_right(Constants.URL_CATEGORY + responses.getSubcategory().get(i).getItems().get(j).getRight_img_path());
                details.setItem_subcategory_id(responses.getSubcategory().get(i).getItems().get(j).getSubcategory_id());
                detailsList.add(details);
                dbHelper.addDetails(details);
            }
        }
        listener.successDetails("OK");
    }

    public interface DetailsListener{
        void successDetails(String response);
        void errorDetails(String error);
    }

    public void setListener(DetailsListener listener) {
        this.listener = listener;
    }
}
