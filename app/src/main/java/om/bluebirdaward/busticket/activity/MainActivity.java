package om.bluebirdaward.busticket.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.github.siyamed.shapeimageview.CircularImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

import om.bluebirdaward.busticket.R;
import om.bluebirdaward.busticket.adapter.AdapterDanhSachChuyen;
import om.bluebirdaward.busticket.fragment.FragmentAbout;
import om.bluebirdaward.busticket.fragment.FragmentDanhSachChuyen;
import om.bluebirdaward.busticket.fragment.FragmentDanhSachNhaXe;
import om.bluebirdaward.busticket.fragment.FragmentDatVeMotChieu;
import om.bluebirdaward.busticket.fragment.FragmentHuongDan;
import om.bluebirdaward.busticket.fragment.FragmentLienHe;
import om.bluebirdaward.busticket.fragment.FragmentMaXacNhan;
import om.bluebirdaward.busticket.fragment.FragmentNhapThongTinKhach;
import om.bluebirdaward.busticket.fragment.FragmentSoDoGheTang1;
import om.bluebirdaward.busticket.fragment.FragmentSuaThongTinVe;
import om.bluebirdaward.busticket.fragment.FragmentTabhostSoDoGhe;
import om.bluebirdaward.busticket.fragment.FragmentThongTinVeDaDat;
import om.bluebirdaward.busticket.fragment.FragmentThongTinVeVuaDat;
import om.bluebirdaward.busticket.fragment.FragmentXemVe;
import om.bluebirdaward.busticket.utils.BitmapLoader;

/**
 * Created by VuVanThang on 5/17/2016.
 */
public class MainActivity extends AppCompatActivity implements
        FragmentDatVeMotChieu.OnNameSetListener,
        AdapterDanhSachChuyen.SoDoGhe,
        FragmentSoDoGheTang1.ChonGhe,
        FragmentNhapThongTinKhach.DatVe,
        FragmentThongTinVeDaDat.DoiGhe,
        FragmentThongTinVeVuaDat.backDatve
{

    DrawerLayout drawerLayout;
    NavigationView navigation;
    ActionBarDrawerToggle drawerToggle;
    TextView txtName;
    TextView txtMail;
    CircularImageView imgAvatar;
    Button btnLogin_Logout;
    Button btnShare;
    boolean doubleBackToExitPressedOnce = false;

    //--------------------------------Facebook----------------------
    private CallbackManager callbackManager;
    private ShareDialog shareDialog;  // intialize facebook shareDialog.
    private final Handler handler = new Handler();
    private String profileUser;
    private String userId;
    private String email;

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_main);
        navigation = (NavigationView) findViewById(R.id.navigation_view);
        shareDialog = new ShareDialog(this);

        View headerLayout = navigation.getHeaderView(0);
        btnLogin_Logout = (Button)headerLayout.findViewById(R.id.nav_header_Login_Logout);
        btnShare = (Button) headerLayout.findViewById(R.id.nav_header_Share);
        txtName = (TextView) headerLayout.findViewById(R.id.nav_header_name);
        txtMail = (TextView) headerLayout.findViewById(R.id.nav_header_mail);
        imgAvatar = (CircularImageView) headerLayout.findViewById(R.id.nav_header_image_avatar);



        FragmentDanhSachNhaXe fragmentDanhSachNhaXe = new FragmentDanhSachNhaXe(MainActivity.this);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentholder,fragmentDanhSachNhaXe)
                .commit();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.opendrawer, R.string.closedrawer);
        drawerLayout.setDrawerListener(drawerToggle);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initInstances();

    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    private void initInstances() {

        if (btnLogin_Logout.getText().equals("Login Facebook")){
            btnShare.setVisibility(View.GONE);
        }else if (btnLogin_Logout.getText().equals("Logout Facebook")){
            btnShare.setVisibility(View.VISIBLE);
        }

        btnLogin_Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnLogin_Logout.getText().equals("Login Facebook")) {
                    LoginFacebook();

                } else if (btnLogin_Logout.getText().equals("Logout Facebook")) {
                    LogoutFacebook();
                }

            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareFacebook();
            }
        });

        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.navigation_item_0:
                        FragmentDanhSachNhaXe fragmentDanhSachNhaXe = new FragmentDanhSachNhaXe(MainActivity.this);
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragmentholder, fragmentDanhSachNhaXe)
                                .commit();
                        navigation.setCheckedItem(id);
                        drawerLayout.closeDrawer(navigation);
                        break;
                    case R.id.navigation_item_1:
                        FragmentDatVeMotChieu fragmentTabhostDatVe = new FragmentDatVeMotChieu();
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragmentholder, fragmentTabhostDatVe)
                                .commit();
                        navigation.setCheckedItem(id);
                        drawerLayout.closeDrawer(navigation);
                        break;
                    case R.id.navigation_item_2:
                        FragmentXemVe fragmentXemVe = new FragmentXemVe();
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragmentholder, fragmentXemVe)
                                .commit();
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
                        FragmentAbout fragmentAbout = new FragmentAbout();
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragmentholder, fragmentAbout)
                                .commit();
                        navigation.setCheckedItem(id);
                        drawerLayout.closeDrawer(navigation);
                        break;
                    case R.id.navigation_item_6:
                        FragmentMaXacNhan fragmentMaXacNhan = new FragmentMaXacNhan();
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragmentholder, fragmentMaXacNhan)
                                .commit();
                        navigation.setCheckedItem(id);
                        drawerLayout.closeDrawer(navigation);
                        break;
                    default:
                        break;

                }

                return false;
            }
        });

    }

    //--------------------------------------API Facebook--------------------------------------------

    private void ShareFacebook(){

        if (ShareDialog.canShow(ShareLinkContent.class)) {
            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentTitle("How to integrate Linkedin from your app")
                    .setImageUrl(Uri.parse("https://www.numetriclabz.com/wp-content/uploads/2015/11/114.png"))
                    .setContentDescription(
                            "simple LinkedIn integration")
                    .setContentUrl(Uri.parse("https://www.numetriclabz.com/android-linkedin-integration-login-tutorial/"))
                    .build();

            shareDialog.show(linkContent);  // Show facebook ShareDialog
        }
    }

    private void LoginFacebook(){
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile","email"));
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(final LoginResult loginResult) {

                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(final JSONObject object, final GraphResponse response) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Log.d("MainActivityGraph", response.toString());
                                    Log.d("MainActivity", object.toString());
                                    profileUser = object.getString("name");
                                    userId = object.getString("id");
                                    email = object.getString("email");
                                    txtMail.setText(email);
                                    txtName.setText(profileUser);

                                    Glide.with(getApplicationContext())
                                            .load("https://graph.facebook.com/" + userId + "/picture??width=100&&height=100")
                                            .asBitmap()
                                            .into(imgAvatar);
                                    btnLogin_Logout.setText("Logout Facebook");
                                    btnShare.setVisibility(View.VISIBLE);

                                    /*Glide.with(getApplicationContext())
                                            .load(urlPic)
                                            .asBitmap()
                                            .override(500, Target.SIZE_ORIGINAL)
                                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                                            .skipMemoryCache(true)
                                            .into(imgAvatar);*/

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                });

                Bundle data = new Bundle();
                data.putString("fields", "id,name,picture,email");
                request.setParameters(data);
                request.executeAsync();

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    private void LogoutFacebook(){
        LoginManager.getInstance().logOut();
        txtName.setText("");
        txtMail.setText("");
        BitmapLoader.LoadImageNotScale(this,imgAvatar,R.drawable.icon_avatar);
        btnLogin_Logout.setText("Login Facebook");
        btnShare.setVisibility(View.GONE);
    }


    //----------------------------------------------------------------------------------------------


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
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
    public void setChuyenDi_NgayDi(String idHangXe, String ChuyenDi, String NgayDi) {
        FragmentDanhSachChuyen danhSachChuyen = new FragmentDanhSachChuyen();
        Bundle data = new Bundle();
        data.putString("ChuyenDi", ChuyenDi);
        data.putString("NgayDi", NgayDi);
        data.putString("idHangXe", idHangXe);
        danhSachChuyen.setArguments(data);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentholder, danhSachChuyen);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.commit();
    }

    private String code_trip_temp;
    private String code_driver_temp;
    private String id_tripdate_temp;

    @Override
    public void setSoDoGhe(String TenChuyen, String GioDi, String NgayDi,String code_trip, String id_tripdate, String code_driver) {
        FragmentTabhostSoDoGhe datVe_fragment = new FragmentTabhostSoDoGhe();
        Bundle data = new Bundle();
        data.putString("ChuyenDi", TenChuyen);
        data.putString("GioDi", GioDi);
        data.putString("NgayDi", NgayDi);
        data.putString("code_trip", code_trip);
        data.putString("id_tripdate", id_tripdate);
        data.putString("code_driver", code_driver);
        code_trip_temp = code_trip;
        code_driver_temp = code_driver;
        id_tripdate_temp  = id_tripdate;
        datVe_fragment.setArguments(data);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentholder, datVe_fragment);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.commit();

    }

    @Override
    public void clickChonGhe(String TenChuyen, String GioDi, String NgayDi) {
        Bundle data = new Bundle();
        data.putString("id_tripdate",id_tripdate_temp);
        data.putString("code_driver", code_driver_temp);
        data.putString("code_trip", code_trip_temp);
        data.putString("TenChuyen", TenChuyen);
        data.putString("GioDi", GioDi);
        data.putString("NgayDi", NgayDi);

        FragmentNhapThongTinKhach nhap_thong_tin_fragment = new FragmentNhapThongTinKhach();
        nhap_thong_tin_fragment.setArguments(data);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentholder,nhap_thong_tin_fragment)
                .addToBackStack("")
                .commit();
    }


    @Override
    public void clickDatVe(String TenChuyen, String GioDi, String NgayDi, String MaTai, String MaChuyen, String code_trip, String HoTen, String CMND,
                           String SDT, String GhiChu, List<String> listSoGhe, int SoLuong, String MaVe) {

        Bundle data = new Bundle();
        data.putString("TenChuyen", TenChuyen);
        data.putString("GioDi", GioDi);
        data.putString("NgayDi", NgayDi);
        data.putString("MaTai",MaTai);
        data.putString("MaChuyen",MaChuyen);
        data.putString("code_trip", code_trip);
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
                .addToBackStack("")
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
                .commit();
    }

    /**
     * Sau khi đặt vé xong , báo thành công và trở về fragment Đặt vé (FragmentDatVeMotChieu)
     */
    @Override
    public void setBackDatVe() {
        //FragmentTabhostDatVe fragmentTabhostDatVe = new FragmentTabhostDatVe();
        FragmentDatVeMotChieu fragmentTabhostDatVe = new FragmentDatVeMotChieu();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentholder, fragmentTabhostDatVe)
                .commit();
    }

}
