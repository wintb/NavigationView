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

import java.util.List;

import tien.dinh.navigationview.R;
import tien.dinh.navigationview.adapter.ViewPagerAdapter;

/**
 * Created by DinhTien on 15-05-2016.
 */
public class DatVe_Fragment extends Fragment{
    
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentActivity myContext;
    private Toolbar toolbar;
    private List<String> data;

    public DatVe_Fragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.activity_dat_ve,container,false);

        viewPager = (ViewPager)rootView.findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(1);
        setupViewPager(viewPager);

        tabLayout = (TabLayout)rootView.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        return rootView;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(myContext.getSupportFragmentManager());
        adapter.addFragment(new Datve_First_Floor_Fragment(), "TẦNG 1");
        adapter.addFragment(new Datve_Second_Floor_Fragment(),"TẦNG 2");

        viewPager.setAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        myContext=(FragmentActivity) context;
        super.onAttach(context);
    }


}