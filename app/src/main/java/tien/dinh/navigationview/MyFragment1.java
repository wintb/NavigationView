package tien.dinh.navigationview;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;

import tien.dinh.navigationview.EventForTabHost.Fragment_OneWay.OneWay;
import tien.dinh.navigationview.EventForTabHost.Fragment_RoundTrip.RoundTrip;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment1 extends Fragment {


    private TextView tv;
    private TabHost host;
    private FragmentTabHost tabHost;


    public MyFragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_my_fragment2,container,false);

        /*host = (TabHost) rootView.findViewById(R.id.tabHost);
        host.setup();

        //Một chiều
        TabHost.TabSpec spec = host.newTabSpec("MỘT CHIỀU");
        spec.setContent(R.id.tab1);
        spec.setIndicator("MỘT CHIỀU");
        host.addTab(spec);



        //Khứ hồi
        spec = host.newTabSpec("KHỨ HỒI");
        spec.setContent(R.id.tab2);

        spec.setIndicator("KHỨ HỒI");
        host.addTab(spec);

        //Set text color for tabhost
        setForTabHost();*/

        //tabHost = new FragmentTabHost(getActivity());
        tabHost = (FragmentTabHost) rootView.findViewById(android.R.id.tabhost);
        tabHost.setup(getActivity(), getChildFragmentManager(), android.R.id.tabcontent);

        tabHost.addTab(tabHost.newTabSpec("MOT CHIEU").setIndicator("MOT CHIEU"), OneWay.class, null);
        tabHost.addTab(tabHost.newTabSpec("HAI CHIEU").setIndicator("HAI CHIEU"), RoundTrip.class, null);
        //tabHost.setCurrentTab(0);

        return rootView;


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        tabHost = null;
    }

    //--------------------------------SET EVENT FOR TABHOST ------------------------------------------------------------------

    public void setForTabHost(){
        tv = (TextView) host.getCurrentTabView().findViewById(android.R.id.title); //for Selected Tab
        tv.setTextColor(Color.parseColor("#2980b9"));
        tv.setTextSize(15);
        host.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                for (int i = 0; i < host.getTabWidget().getChildCount(); i++) {

                    //Set text color for tabhost
                    tv = (TextView) host.getTabWidget().getChildAt(i).findViewById(android.R.id.title); //Unselected Tabs
                    tv.setTextColor(Color.parseColor("#2c3e50"));
                    tv.setTextSize(15);

                }

                /*host.getTabWidget().getChildAt(host.getCurrentTab()).setBackgroundColor(Color.parseColor("#0000FF")); // selected*/
                //Set text color for tabhost
                tv = (TextView) host.getCurrentTabView().findViewById(android.R.id.title); //for Selected Tab
                tv.setTextColor(Color.parseColor("#2980b9"));
                tv.setTextSize(15);

            }
        });
    }


}
