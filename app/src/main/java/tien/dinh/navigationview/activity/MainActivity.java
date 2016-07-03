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

import java.util.List;

import tien.dinh.navigationview.R;
import tien.dinh.navigationview.adapter.AdapterDanhSachChuyen;
import tien.dinh.navigationview.fragment.FragmentAbout;
import tien.dinh.navigationview.fragment.FragmentHuongDan;
import tien.dinh.navigationview.fragment.FragmentLienHe;
import tien.dinh.navigationview.fragment.FragmentSuaThongTinVe;
import tien.dinh.navigationview.fragment.FragmentThongTinVeVuaDat;
import tien.dinh.navigationview.fragment.FragmentTabhostSoDoGhe;
import tien.dinh.navigationview.fragment.FragmentSoDoGheTang1;
import tien.dinh.navigationview.fragment.FragmentTabhostDatVe;
import tien.dinh.navigationview.fragment.FragmentXemVe;
import tien.dinh.navigationview.fragment.FragmentNhapThongTinKhach;
import tien.dinh.navigationview.fragment.FragmentThongTinVeDaDat;
import tien.dinh.navigationview.fragment.FragmentDatVeMotChieu;
import tien.dinh.navigationview.fragment.FragmentDanhSachChuyen;

/**
 * Created by VuVanThang on 5/17/2016.
 */
public class MainActivity extends AppCompatActivity implements
        FragmentDatVeMotChieu.OnNameSetListener,
        AdapterDanhSachChuyen.SoDoGhe,
        FragmentXemVe.OnNameSetListener,
        FragmentSoDoGheTang1.ChonGhe,
        FragmentNhapThongTinKhach.DatVe,
        FragmentThongTinVeDaDat.DoiGhe,
        FragmentThongTinVeVuaDat.backDatve,
        FragmentThongTinVeDaDat.SuaVe{

    DrawerLayout drawerLayout;
    NavigationView navigation;
    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FragmentTabhostDatVe fragmentTabhostDatVe = new FragmentTabhostDatVe();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentholder, fragmentTabhostDatVe);
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
                        FragmentTabhostDatVe fragmentTabhostDatVe = new FragmentTabhostDatVe();
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragmentholder, fragmentTabhostDatVe).addToBackStack(null);
                        fragmentTransaction.commit();
                        navigation.setCheckedItem(id);
                        drawerLayout.closeDrawer(navigation);
                        break;
                    case R.id.navigation_item_2:
                        FragmentXemVe fragmentXemVe = new FragmentXemVe();
                        FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction2.replace(R.id.fragmentholder, fragmentXemVe);
                        fragmentTransaction2.commit();
                        navigation.setCheckedItem(id);
                        drawerLayout.closeDrawer(navigation);
                        break;
                    case R.id.navigation_item_3:
                        FragmentHuongDan fragmentHuongDan = new FragmentHuongDan();
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragmentholder, fragmentHuongDan)
                                .commit();
                        navigation.setCheckedItem(id);
                        drawerLayout.closeDrawer(navigation);
                        break;
                    case R.id.navigation_item_4:
                        FragmentLienHe fragmentLienHe = new FragmentLienHe();
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragmentholder, fragmentLienHe)
                                .commit();
                        navigation.setCheckedItem(id);
                        drawerLayout.closeDrawer(navigation);
                        break;
                    case R.id.navigation_item_5:
                        FragmentAbout fragmentAbout = new FragmentAbout(MainActivity.this);
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragmentholder, fragmentAbout)
                                .commit();
                        navigation.setCheckedItem(id);
                        drawerLayout.closeDrawer(navigation);
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
            FragmentDanhSachChuyen danhSachChuyen = new FragmentDanhSachChuyen();
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
        FragmentTabhostSoDoGhe datVe_fragment = new FragmentTabhostSoDoGhe();
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
        FragmentThongTinVeDaDat xemVe = new FragmentThongTinVeDaDat();
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
        FragmentNhapThongTinKhach nhap_thong_tin_fragment = new FragmentNhapThongTinKhach();
        nhap_thong_tin_fragment.setArguments(data);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentholder,nhap_thong_tin_fragment)
                .addToBackStack("sodoghe")
                .commit();
    }


    @Override
    public void clickDatVe(String MaTai, String MaChuyen, String HoTen, String CMND,
                           String SDT, String GhiChu, List<String> listSoGhe, int SoLuong, String MaVe) {

        Bundle data = new Bundle();
        data.putString("MaTai",MaTai);
        data.putString("MaChuyen",MaChuyen);
        data.putString("HoTen",HoTen);
        data.putString("CMND",CMND);
        data.putString("SDT",SDT);
        data.putString("GhiChu",GhiChu);
        data.putInt("SoLuong", SoLuong);
        data.putString("MaVe", MaVe);

        for (int i = 0; i < listSoGhe.size(); i++){

            data.putString("SoGhe" + (i+1),listSoGhe.get(i));
        }

        FragmentThongTinVeVuaDat thongTinVeVuaDatFragment = new FragmentThongTinVeVuaDat();
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
    public void setDoiGhe(String MaVe, String MaChuyen, String TenChuyen, String GioDi, String NgayDi, String SDTKhach) {
        Bundle data = new Bundle();
        data.putString("MaVe", MaVe);
        data.putString("MaChuyen",MaChuyen);
        data.putString("ChuyenDi",TenChuyen);
        data.putString("GioDi",GioDi);
        data.putString("NgayDi",NgayDi);
        data.putString("SDTKhach",SDTKhach);

        FragmentTabhostSoDoGhe datVe_fragment = new FragmentTabhostSoDoGhe();
        datVe_fragment.setArguments(data);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentholder,datVe_fragment)
                .addToBackStack("doighe")
                .commit();
    }

    /**
     * Sau khi đặt vé xong , báo thành công và trở về fragment Đặt vé (FragmentDatVeMotChieu)
     */
    @Override
    public void setBackDatVe() {
        FragmentTabhostDatVe fragmentTabhostDatVe = new FragmentTabhostDatVe();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentholder, fragmentTabhostDatVe)
                .commit();
    }

    @Override
    public void setSuaVe(String HoTen, String SDT, String CMND, String NoiXuong, String MaChuyen, String MaVe) {

        Bundle data = new Bundle();
        data.putString("HoTen",HoTen);
        data.putString("SDT",SDT);
        data.putString("CMND",CMND);
        data.putString("NoiXuong", NoiXuong);
        data.putString("MaChuyen", MaChuyen);
        data.putString("MaVe", MaVe);

        FragmentSuaThongTinVe fragmentSuaThongTinVe = new FragmentSuaThongTinVe();
        fragmentSuaThongTinVe.setArguments(data);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentholder,fragmentSuaThongTinVe)
                .addToBackStack("suave")
                .commit();

    }
}
