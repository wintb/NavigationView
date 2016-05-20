package tien.dinh.navigationview.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import tien.dinh.navigationview.R;
import tien.dinh.navigationview.adapter.CustomAdapterOneTrip;
import tien.dinh.navigationview.fragment.DatVe_Fragment;
import tien.dinh.navigationview.fragment.MyFragment1;
import tien.dinh.navigationview.fragment.MyFragment2;
import tien.dinh.navigationview.tabhost.oneway.OnWay_ListTrip;
import tien.dinh.navigationview.tabhost.oneway.OneWay;

/**
 * Created by VuVanThang on 5/17/2016.
 */
public class MainActivity extends AppCompatActivity implements OneWay.OnNameSetListener,CustomAdapterOneTrip.SoDoGhe{

    DrawerLayout drawerLayout;
    NavigationView navigation;
    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyFragment1 myFragment1 = new MyFragment1();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentholder, myFragment1);
        fragmentTransaction.commit();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.opendrawer, R.string.closedrawer);
        drawerLayout.setDrawerListener(drawerToggle);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initInstances();
    }


    private void initInstances() {

        navigation = (NavigationView) findViewById(R.id.navigation_view);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.navigation_item_1:
                        //Do some thing here
                        // add navigation drawer item onclick method here
                        MyFragment1 myFragment1 = new MyFragment1();
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragmentholder, myFragment1).addToBackStack(null);
                        fragmentTransaction.commit();
                        navigation.setCheckedItem(id);
                        drawerLayout.closeDrawer(navigation);
                        break;
                    case R.id.navigation_item_2:
                        MyFragment2 myFragment2 = new MyFragment2();
                        FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction2.replace(R.id.fragmentholder, myFragment2);
                        fragmentTransaction2.commit();
                        navigation.setCheckedItem(id);
                        drawerLayout.closeDrawer(navigation);
                        break;
                    case R.id.navigation_item_3:
                        //Do some thing here
                        // add navigation drawer item onclick method here
                        break;
                    case R.id.navigation_item_4:
                        //Do some thing here
                        // add navigation drawer item onclick method here
                        break;
                    case R.id.navigation_item_5:
                        //Do some thing here
                        // add navigation drawer item onclick method here
                        break;
                }

                return false;
            }
        });

    }


    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    //----------------------------------SEND DATA FROM FRAGMENT TO FRAGMENT TO SHOW LIST TRIP---------------------------------

    @Override
    public void setChuyenDi_NgayDi(String ChuyenDi, String NgayDi, String json) {
        if (json == "[]"){
            Toast.makeText(getApplication(), "No Trip on day", Toast.LENGTH_LONG).show();
        }else {
            OnWay_ListTrip danhSachChuyen = new OnWay_ListTrip();
            Bundle data = new Bundle();
            data.putString("ChuyenDi", ChuyenDi);
            data.putString("NgayDi", NgayDi);
            data.putString("JsonChuyen", json);
            danhSachChuyen.setArguments(data);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmentholder, danhSachChuyen);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void setSoDoGhe(String TenChuyen, String GioDi, String NgayDi) {
        DatVe_Fragment datVe_fragment = new DatVe_Fragment();
        Bundle data = new Bundle();
        data.putString("ChuyenDi", TenChuyen);
        data.putString("GioDi", GioDi);
        data.putString("NgayDi", NgayDi);
        datVe_fragment.setArguments(data);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentholder,datVe_fragment);
        fragmentTransaction.addToBackStack("SoDoGhe");
        fragmentTransaction.commit();
    }
}
