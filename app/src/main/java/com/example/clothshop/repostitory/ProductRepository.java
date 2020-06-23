package com.example.clothshop.repostitory;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.clothshop.database.Database;
import com.example.clothshop.database.ProductDao;
import com.example.clothshop.model.Product;

import java.util.List;

public class ProductRepository {


    private ProductDao productDao;
    private LiveData<List<Product>> products;


    public ProductRepository(Context context) {
        Database db = Database.getInstance(context);
        productDao = db.productDao();

        this.products = productDao.getAllProducts();
    }

    public void insert(Product product) {
        new InsertAsyncTask(productDao).execute(product);
    }

    public void update(Product product) {
        new UpdateAsyncTask(productDao).execute(product);

    }

    public void delete(Product product) {
        new DeleteAsyncTask(productDao).execute(product);

    }

    public LiveData<List<Product>> getAllProducts() {
        return products;
    }


    public static class InsertAsyncTask extends AsyncTask<Product, Void, Void> {

        private ProductDao productDao;

        public InsertAsyncTask(ProductDao productDao) {
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(Product... products) {

            productDao.insert(products[0]);
            return null;
        }
    }

    public static class UpdateAsyncTask extends AsyncTask<Product, Void, Void> {

        private ProductDao productDao;

        public UpdateAsyncTask(ProductDao productDao) {
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(Product... products) {

            productDao.update(products[0]);
            return null;
        }
    }

    public static class DeleteAsyncTask extends AsyncTask<Product, Void, Void> {

        private ProductDao productDao;

        public DeleteAsyncTask(ProductDao productDao) {
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(Product... products) {

            productDao.delete(products[0]);
            return null;
        }
    }


}
