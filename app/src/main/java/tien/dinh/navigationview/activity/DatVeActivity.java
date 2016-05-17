package tien.dinh.navigationview.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import tien.dinh.navigationview.R;
import tien.dinh.navigationview.adapter.ViewPagerAdapter;
import tien.dinh.navigationview.fragment.Datve_First_Floor_Fragment;
import tien.dinh.navigationview.fragment.Datve_Second_Floor_Fragment;

public class DatVeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_ve);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Datve_First_Floor_Fragment(), "TẦNG 1");
        adapter.addFragment(new Datve_Second_Floor_Fragment(),"TẦNG 2");
        viewPager.setAdapter(adapter);
    }
}
