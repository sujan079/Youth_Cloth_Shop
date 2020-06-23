package com.example.clothshop.repostitory;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.clothshop.database.CartDao;
import com.example.clothshop.database.Database;
import com.example.clothshop.model.CartItem;

import java.util.List;

public class CartItemRepository {

    private CartDao cartDao;
    private LiveData<List<CartItem>> cartItems;


    public CartItemRepository(Context context) {
        Database database = Database.getInstance(context);
        cartDao = database.cartDao();

        cartItems = cartDao.getAllCartItems();
    }

    public void insert(CartItem cartItem) {
        new InsertAsyncTask(cartDao).execute(cartItem);
    }

    public void update(CartItem cartItem) {
        new UpdateAsyncTask(cartDao).execute(cartItem);

    }

    public void delete(CartItem cartItem) {
        new DeleteAsyncTask(cartDao).execute(cartItem);
    }

    public LiveData<List<CartItem>> getCartItems() {
        return cartItems;
    }


    public static class InsertAsyncTask extends AsyncTask<CartItem, Void, Void> {

        private CartDao cartDao;

        public InsertAsyncTask(CartDao cartDao) {
            this.cartDao = cartDao;
        }

        @Override
        protected Void doInBackground(CartItem... cartItems) {

            cartDao.insert(cartItems[0]);
            return null;
        }
    }

    public static class UpdateAsyncTask extends AsyncTask<CartItem, Void, Void> {

        private CartDao cartDao;

        public UpdateAsyncTask(CartDao cartDao) {
            this.cartDao = cartDao;
        }

        @Override
        protected Void doInBackground(CartItem... cartItems) {

            cartDao.update(cartItems[0]);
            return null;
        }
    }

    public static class DeleteAsyncTask extends AsyncTask<CartItem, Void, Void> {

        private CartDao cartDao;

        public DeleteAsyncTask(CartDao cartDao) {
            this.cartDao = cartDao;
        }

        @Override
        protected Void doInBackground(CartItem... cartItems) {

            cartDao.delete(cartItems[0]);
            return null;
        }
    }

}
