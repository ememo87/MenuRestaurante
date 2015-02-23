package com.co.menu.restaurante.bl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.co.menu.restaurante.app.Application;
import com.co.menu.restaurante.entities.CategoriesSpResponse;
import com.co.menu.restaurante.entities.CategoriesSpResponseSubcategory;
import com.co.menu.restaurante.tools.Constants;
import com.co.menu.restaurante.webservice.CategoriesService;

import java.util.ArrayList;
import java.util.List;

import dao.com.co.greendao.Category;
import dao.com.co.greendao.DBHelper;
import dao.com.co.greendao.DaoMaster;
import dao.com.co.greendao.DaoSession;
import dao.com.co.greendao.SubCategory;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by esandoval on 21/02/15.
 */
public class CategoriesBL {

    Context context ;
    DBHelper dbHelper;
    DaoMaster daoMaster;
    DaoSession daoSession;
    DaoMaster.DevOpenHelper devOpenHelper;
    SQLiteDatabase db;

    private CategoriesBlListener listener;

    CategoriesSpResponse cResponse;
    CategoriesSpResponseSubcategory cSubcategory;
    List<CategoriesSpResponseSubcategory> subcategories;
    List<CategoriesSpResponse> cListResponse = new ArrayList<CategoriesSpResponse>();

    public void requestCategories(Context context){
        this.context= context;
        initDB();
        CategoriesService service = Application.getRestAdapterCategories().create(CategoriesService.class);
        service.category(new Callback<List<CategoriesSpResponse>>() {
            @Override
            public void success(List<CategoriesSpResponse> categoriesSpResponse, Response response) {

                int sizeCategory = categoriesSpResponse.size();
                for (int j=0; j<sizeCategory;j++){
                    subcategories = new ArrayList<CategoriesSpResponseSubcategory>();
                    cResponse = new CategoriesSpResponse();
                    cResponse.setId(categoriesSpResponse.get(j).getId());
                    cResponse.setName(categoriesSpResponse.get(j).getName());
                    cResponse.setImg_path(categoriesSpResponse.get(j).getImg_path());
                    cResponse.setType_id(categoriesSpResponse.get(j).getType_id());
                    cResponse.setEnabled(categoriesSpResponse.get(j).getEnabled());
                    cResponse.setCreated_at(categoriesSpResponse.get(j).getCreated_at());
                    cResponse.setUpdated_at(categoriesSpResponse.get(j).getUpdated_at());

                    int sizeSubcategory = categoriesSpResponse.get(j).getSubcategory().size();
                    for (int i=0; i<sizeSubcategory;i++){
                        cSubcategory = new CategoriesSpResponseSubcategory();
                        cSubcategory.setId(categoriesSpResponse.get(j).getSubcategory().get(i).getId());
                        cSubcategory.setName(categoriesSpResponse.get(j).getSubcategory().get(i).getName());
                        cSubcategory.setCategory_id(categoriesSpResponse.get(j).getSubcategory().get(i).getCategory_id());
                        cSubcategory.setAddition_enable(categoriesSpResponse.get(j).getSubcategory().get(i).getAddition_enable());
                        cSubcategory.setEnabled(categoriesSpResponse.get(j).getSubcategory().get(i).getEnabled());
                        cSubcategory.setCreated_at(categoriesSpResponse.get(j).getSubcategory().get(i).getCreated_at());
                        cSubcategory.setUpdated_at(categoriesSpResponse.get(j).getSubcategory().get(i).getUpdated_at());
                        subcategories.add(cSubcategory);
                        cResponse.setSubcategory(subcategories);
                    }
                    cListResponse.add(cResponse);
                }
                successResponse(cListResponse);

            }

            @Override
            public void failure(RetrofitError retrofitError) {
                listener.errorCategory(retrofitError.getUrl().toString());
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

    private void successResponse(List<CategoriesSpResponse> responses){
        Category category = null;
        SubCategory subCategory = null;
        List<Category> categoryList = new ArrayList<Category>();
        List<SubCategory> subCategoryList = new ArrayList<SubCategory>();

        int sizeResponse = responses.size();
        for (int i=0;i<sizeResponse;i++) {
            category = new Category();
            category.setCategory_id(responses.get(i).getId().toString().trim());
            category.setNameCategory(responses.get(i).getName().toString().trim());
            String url_image = responses.get(i).getImg_path();
            if((url_image == null)|| url_image.equalsIgnoreCase("")){
                category.setUrlImage("http://192.237.180.31/archies/public/img/categories/1.jpg");
            }else{
                category.setUrlImage(Constants.URL_CATEGORY + url_image);
            }

            int sizeSubCategory = responses.get(i).getSubcategory().size();
            for (int j=0; j<sizeSubCategory; j++) {
                subCategory = new SubCategory();
                subCategory.setSubCategory_id(responses.get(i).getSubcategory().get(j).getCategory_id().toString().trim());
                subCategory.setSubCategory(responses.get(i).getSubcategory().get(j).getName().toString().trim());
                subCategoryList.add(subCategory);
                dbHelper.addSubCategory(subCategory);
            }
            categoryList.add(category);
            dbHelper.addCategory(category);
        }

        listener.successCategory("OK");
    }

    public interface CategoriesBlListener{
        void errorCategory(String error);
        void successCategory(String response);
    }

    public void setListener(CategoriesBlListener listener) {
        this.listener = listener;
    }
}
