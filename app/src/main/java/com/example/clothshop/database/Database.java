package com.example.clothshop.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.clothshop.model.CartItem;
import com.example.clothshop.model.Product;

@androidx.room.Database(entities = {Product.class, CartItem.class}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {


    private static Database INSTANCE = null;
    private static String DATABASE_NAME = "database";

    public static Database getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.
                    databaseBuilder(context, Database.class, DATABASE_NAME)
                    .addCallback(roomCallback)
                    .build();
        }
        return INSTANCE;
    }

    public abstract CartDao cartDao();

    public abstract ProductDao productDao();

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            ProductDao productDao = INSTANCE.productDao();
            new InsertInBackground(productDao).execute();
        }
    };

    private static class InsertInBackground extends AsyncTask<Void, Void, Void> {

        private ProductDao productDao;

        public InsertInBackground(ProductDao productDao) {
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            productDao.insert(new Product("Jordan", 650.0, "https://www.pngkit.com/png/full/834-8343448_jordan-shoes-png-air-jordan.png", "38FR", "Jordan Shoes, Air Jordan"));
            productDao.insert(new Product("Nike Air", 120.0, "https://lh3.googleusercontent.com/proxy/e-NmYqinRLf_BymAlH5gwQVIrzRMgGUusrYagy1FHICiCIjgGOg-uHWsV7yUgDh6lQ1yebduJlcrD3uTOOXaDJeqyYYN1pBbYcmLx_QBU8iF8FMGI9nFiCNrv8a1fni5bw", "38FR", "Jordan Shoes, Air Jordan"));
            productDao.insert(new Product("Adidas Samba ", 550.0, "https://www.freepngimg.com/thumb/adidas/58285-samba-superstar-originals-adidas-sneakers-shoe.png", "48FR", "Adidas Samba Originals Sneakers Shoe"));
            productDao.insert(new Product("SPLY 350", 700.0, "https://i.dlpng.com/static/png/6474710_preview.png", "48FR", "SPLY 350 Sneakers Shoe"));
            productDao.insert(new Product("Adidas Deerupt", 650.0, "https://i.dlpng.com/static/png/1219672-adidas-deerupt-runner-shoes-adidas-shoes-png-640_386_preview.png", "48FR", "Adidas Deerupt Runner Shoes"));
            return null;
        }
    }


}
