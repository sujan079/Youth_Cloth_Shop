package com.example.clothshop;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.clothshop.databinding.ActivityMainBinding;
import com.example.clothshop.fragment.CartFragment;
import com.example.clothshop.fragment.HomeFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    private HomeFragment homeFragment;
    private CartFragment cartFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        init();
        initDrawer();


    }

    private void init() {
        homeFragment = new HomeFragment();
        cartFragment = new CartFragment();
        getSupportFragmentManager().beginTransaction().replace(activityMainBinding.fragmentHome.getId(), homeFragment).commit();
        getSupportFragmentManager().beginTransaction().replace(activityMainBinding.fragmentCart.getId(), cartFragment).commit();

    }

    private void initDrawer() {
        drawerLayout = activityMainBinding.drawerLayout;

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close);
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        drawerToggle.syncState();

    }


}