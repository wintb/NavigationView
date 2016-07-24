package om.bluebirdaward.busticket.fragment;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import om.bluebirdaward.busticket.R;
import om.bluebirdaward.busticket.adapter.AdapterViewPager;

/**
 * Created by DinhTien on 15-05-2016.
 */
public class FragmentTabhostSoDoGhe extends Fragment{


    @Bind(R.id.txtSoDoGheChuyenDi)
    TextView txtChuyenDi;
    @Bind(R.id.txtSoDoGheGioDi)
    TextView txtGioDi;
    @Bind(R.id.txtSoDoGheNgayDi)
    TextView txtNgayDi;
    @Bind(R.id.tabhost_timchuyen_text_ghe_da_dat)
    TextView txtGheDaDat;
    @Bind(R.id.tabhost_timchuyen_text_ghe_dang_chon)
    TextView txtGheDangChon;
    @Bind(R.id.tabhost_timchuyen_text_ghe_trong)
    TextView txtGheTrong;
    @Bind(R.id.tabs)
    TabLayout tabLayout;
    @Bind(R.id.viewpager)
    ViewPager viewPager;

    private FragmentActivity myContext;
    private Toolbar toolbar;
    private List<String> data;

    private String code_trip;
    private String id_tripdate;
    private String code_driver;
    private String MaVe;
    private String SDTKhach;
    private String TenChuyen;
    private String GioDi;
    private String NgayDi;
    public static ArrayList<String> listGheDaChonTang1;


    public FragmentTabhostSoDoGhe(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_tabhost_so_do_ghe_1, container, false);
        ButterKnife.bind(this, rootView);
        setTypeFace();
        //get data from fragment previous
        listGheDaChonTang1 = new ArrayList<>();
        Bundle data = getArguments();
        txtChuyenDi.setText(data.getString("ChuyenDi"));
        txtGioDi.setText(data.getString("GioDi"));
        txtNgayDi.setText(data.getString("NgayDi"));
        code_trip = data.getString("code_trip");
        id_tripdate = data.getString("id_tripdate");
        code_driver = data.getString("code_driver");

        MaVe = data.getString("MaVe");
        SDTKhach = data.getString("SDTKhach");
        TenChuyen = txtChuyenDi.getText().toString();
        GioDi = txtGioDi.getText().toString();
        NgayDi = txtNgayDi.getText().toString();

        viewPager.setOffscreenPageLimit(1);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        return rootView;
    }

    private void setTypeFace(){
        Typeface face1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Regular.ttf");
        Typeface face2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Bold.ttf");
        txtGheDaDat.setTypeface(face1);
        txtGheDangChon.setTypeface(face1);
        txtGheTrong.setTypeface(face1);
        txtChuyenDi.setTypeface(face1);
        txtGioDi.setTypeface(face1);
        txtNgayDi.setTypeface(face1);
    }


    private void setupViewPager(ViewPager viewPager) {
        FragmentSoDoGheTang1 fragmentSoDoGheTang1 = new FragmentSoDoGheTang1();
        FragmentSoDoGheTang2 fragmentSoDoGheTang2 = new FragmentSoDoGheTang2();
        Bundle dataDoiGhe = new Bundle();
        dataDoiGhe.putString("code_trip",code_trip);
        dataDoiGhe.putString("id_tripdate", id_tripdate);
        dataDoiGhe.putString("code_driver", code_driver);
        dataDoiGhe.putString("MaVe",MaVe);
        dataDoiGhe.putString("SDTKhach", SDTKhach);
        dataDoiGhe.putString("TenChuyen",TenChuyen);
        dataDoiGhe.putString("GioDi", GioDi);
        dataDoiGhe.putString("NgayDi", NgayDi);

        fragmentSoDoGheTang1.setArguments(dataDoiGhe);
        fragmentSoDoGheTang2.setArguments(dataDoiGhe);
        AdapterViewPager adapter = new AdapterViewPager(getChildFragmentManager());
        adapter.addFragment(fragmentSoDoGheTang1, "TẦNG 1");
        adapter.addFragment(fragmentSoDoGheTang2,"TẦNG 2");
        viewPager.setAdapter(adapter);
    }


    @Override
    public void onAttach(Context context) {
        myContext=(FragmentActivity) context;
        super.onAttach(context);
    }

    public static FragmentTabhostSoDoGhe newInstance() {
        return new FragmentTabhostSoDoGhe();
    }

}