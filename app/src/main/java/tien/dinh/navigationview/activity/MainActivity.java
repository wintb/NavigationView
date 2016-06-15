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
import tien.dinh.navigationview.adapter.Thong_Tin_Ve_Vua_Dat_Fragment;
import tien.dinh.navigationview.fragment.DatVe_Fragment;
import tien.dinh.navigationview.fragment.Datve_First_Floor_Fragment;
import tien.dinh.navigationview.fragment.MyFragment1;
import tien.dinh.navigationview.fragment.MyFragment2;
import tien.dinh.navigationview.fragment.Nhap_Thong_Tin_Fragment;
import tien.dinh.navigationview.fragment.XemVe;
import tien.dinh.navigationview.tabhost.oneway.OnWay_ListTrip;
import tien.dinh.navigationview.tabhost.oneway.OneWay;

/**
 * Created by VuVanThang on 5/17/2016.
 */
public class MainActivity extends AppCompatActivity implements
        OneWay.OnNameSetListener,
        CustomAdapterOneTrip.SoDoGhe,
        MyFragment2.OnNameSetListener,
        Datve_First_Floor_Fragment.ChonGhe,
        Nhap_Thong_Tin_Fragment.DatVe,
        XemVe.DoiGhe,
        Thong_Tin_Ve_Vua_Dat_Fragment.backDatve{

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
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    private String MaChuyen_temp;
    private String MaTai_temp;

    @Override
    public void setSoDoGhe(String TenChuyen, String GioDi, String NgayDi,String MaChuyen, String MaTai) {
        DatVe_Fragment datVe_fragment = new DatVe_Fragment();
        Bundle data = new Bundle();
        data.putString("ChuyenDi", TenChuyen);
        data.putString("GioDi", GioDi);
        data.putString("NgayDi", NgayDi);
        data.putString("MaChuyen", MaChuyen);
        MaChuyen_temp = MaChuyen;
        MaTai_temp = MaTai;
        datVe_fragment.setArguments(data);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentholder, datVe_fragment);
        fragmentTransaction.addToBackStack("SoDoGhe");
        fragmentTransaction.commit();

    }

    @Override
    public void setThongTinVe(String json) {
        XemVe xemVe = new XemVe();
        Bundle data = new Bundle();
        data.putString("JsonThongTinVe",json);
        xemVe.setArguments(data);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentholder, xemVe);
        fragmentTransaction.addToBackStack("xemve");
        fragmentTransaction.commit();
    }

    @Override
    public void clickChonGhe() {
        Bundle data = new Bundle();
        data.putString("MaChuyen",MaChuyen_temp);
        data.putString("MaTai", MaTai_temp);
        Nhap_Thong_Tin_Fragment nhap_thong_tin_fragment = new Nhap_Thong_Tin_Fragment();
        nhap_thong_tin_fragment.setArguments(data);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentholder,nhap_thong_tin_fragment)
                .addToBackStack("sodoghe")
                .commit();
    }

    @Override
    public void clickDatVe(String MaTai, String MaChuyen, String HoTen, String CMND,
                           String SDT, String GhiChu, String SoGhe, int SoLuong, String MaVe) {

        Bundle data = new Bundle();
        data.putString("MaTai",MaTai);
        data.putString("MaChuyen",MaChuyen);
        data.putString("HoTen",HoTen);
        data.putString("CMND",CMND);
        data.putString("SDT",SDT);
        data.putString("GhiChu",GhiChu);
        data.putString("SoGhe", SoGhe);
        data.putInt("SoLuong", SoLuong);
        data.putString("MaVe", MaVe);

        Thong_Tin_Ve_Vua_Dat_Fragment thongTinVeVuaDatFragment = new Thong_Tin_Ve_Vua_Dat_Fragment();
        thongTinVeVuaDatFragment.setArguments(data);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentholder,thongTinVeVuaDatFragment)
                .addToBackStack("nhapthongtin")
                .commit();
    }

    /**
     * Đổi ghế trong chuyến xe đã đặt sau khi điền thông tin và xem vé
     * @param MaVe
     * @param MaChuyen
     */
    @Override
    public void setDoiGhe(String MaVe, String MaChuyen, String TenChuyen, String GioDi, String NgayDi) {
        Bundle data = new Bundle();
        data.putString("MaVe", MaVe);
        data.putString("MaChuyen",MaChuyen);
        data.putString("ChuyenDi",TenChuyen);
        data.putString("GioDi",GioDi);
        data.putString("NgayDi",NgayDi);

        DatVe_Fragment datVe_fragment = new DatVe_Fragment();
        datVe_fragment.setArguments(data);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentholder,datVe_fragment)
                .addToBackStack("doighe")
                .commit();
    }

    /**
     * Sau khi đặt vé xong , báo thành công và trở về fragment Đặt vé (OneWay)
     */
    @Override
    public void setBackDatVe() {
        MyFragment1 myFragment1 = new MyFragment1();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentholder,myFragment1)
                .commit();
    }
}
