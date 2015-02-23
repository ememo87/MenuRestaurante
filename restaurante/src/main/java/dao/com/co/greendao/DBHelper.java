package dao.com.co.greendao;

import android.content.Context;

import com.co.menu.restaurante.app.Application;

import java.util.List;

import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by esandoval on 22/02/15.
 */
public class DBHelper {

    private static Context mContext;
    private static DBHelper instance;

    private static CategoryDao categoryDao;
    private static SubCategoryDao subCategoryDao;
    private static DetailsDao detailsDao;

    private static DaoSession daoSession;

    public DBHelper() {
    }

    public static DBHelper getInstance (Context context){
        instance = new DBHelper();
        if (mContext == null)
        {
            mContext = context;
        }
        DaoMaster daoMaster = Application.getDaoMaster(mContext);
        daoSession = daoMaster.newSession();
        daoSession.clear();
        instance.categoryDao = daoSession.getCategoryDao();
        instance.subCategoryDao = daoSession.getSubCategoryDao();
        instance.detailsDao = daoSession.getDetailsDao();

        QueryBuilder.LOG_SQL = true;
        QueryBuilder. LOG_VALUES=true;

        return instance;
    }

    public void addCategory(Category item)
    {
        instance.categoryDao.insertWithoutSettingPk(item);
    }

    public void deleteCategory()
    {
        instance.categoryDao.deleteAll();
    }

    public List<Category> getCategory()
    {
        Query<Category> qb = instance.categoryDao.queryBuilder().build();
        Query<Category> query = qb.forCurrentThread();
        return query.list();
    }

    public void addSubCategory(SubCategory item)
    {
        instance.subCategoryDao.insertWithoutSettingPk(item);
    }

    public void deleteSubCategory()
    {
        instance.subCategoryDao.deleteAll();
    }

    public List<SubCategory> getSubCategory()
    {
        Query<SubCategory> qb = instance.subCategoryDao.queryBuilder().build();
        Query<SubCategory> query = qb.forCurrentThread();
        return query.list();
    }

    public void addDetails(Details item)
    {
        instance.detailsDao.insertWithoutSettingPk(item);
    }

    public void deleteDetails()
    {
        instance.detailsDao.deleteAll();
    }

    public List<Details> getDetails()
    {
        Query<Details> qb = instance.detailsDao.queryBuilder().build();
        Query<Details> query = qb.forCurrentThread();
        return query.list();
    }
}
