package tien.dinh.navigationview.fragment;

import android.content.Context;
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

import java.util.List;

import tien.dinh.navigationview.R;
import tien.dinh.navigationview.adapter.AdapterViewPager;

/**
 * Created by DinhTien on 15-05-2016.
 */
public class FragmentTabhostSoDoGhe extends Fragment{
    
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentActivity myContext;
    private Toolbar toolbar;
    private List<String> data;
    TextView txtChuyenDi;
    TextView txtGioDi;
    TextView txtNgayDi;

    private String MaChuyen;
    private String MaVe;
    private String SDTKhach;

    public FragmentTabhostSoDoGhe(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_tabhost_so_do_ghe_1, container, false);

        txtChuyenDi = (TextView) rootView.findViewById(R.id.txtSoDoGheChuyenDi);
        txtGioDi = (TextView) rootView.findViewById(R.id.txtSoDoGheGioDi);
        txtNgayDi = (TextView) rootView.findViewById(R.id.txtSoDoGheNgayDi);
        //get data from fragment previous
        Bundle data = getArguments();
        txtChuyenDi.setText(data.getString("ChuyenDi"));
        txtGioDi.setText(data.getString("GioDi"));
        txtNgayDi.setText(data.getString("NgayDi"));
        MaChuyen = data.getString("MaChuyen");
        MaVe = data.getString("MaVe");
        SDTKhach = data.getString("SDTKhach");

        viewPager = (ViewPager)rootView.findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(1);
        setupViewPager(viewPager);
        tabLayout = (TabLayout)rootView.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        return rootView;
    }


    private void setupViewPager(ViewPager viewPager) {
        FragmentSoDoGheTang1 fragmentSoDoGheTang1 = new FragmentSoDoGheTang1();
        FragmentSoDoGheTang2 fragmentSoDoGheTang2 = new FragmentSoDoGheTang2();
        Bundle dataDoiGhe = new Bundle();
        dataDoiGhe.putString("MaChuyen",MaChuyen);
        dataDoiGhe.putString("MaVe",MaVe);
        dataDoiGhe.putString("SDTKhach", SDTKhach);

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