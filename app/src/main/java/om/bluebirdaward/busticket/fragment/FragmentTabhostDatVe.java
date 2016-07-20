package om.bluebirdaward.busticket.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;

import om.bluebirdaward.busticket.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTabhostDatVe extends Fragment {


    private TextView tv;
    private TabHost host;
    private FragmentTabHost tabHost;

    public FragmentTabhostDatVe() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_tabhost_dat_ve,container,false);
        tabHost = (FragmentTabHost) rootView.findViewById(android.R.id.tabhost);
        tabHost.setup(getActivity(), getChildFragmentManager(), android.R.id.tabcontent);
        tabHost.addTab(tabHost.newTabSpec("Một chiều").setIndicator("Một chiều"), FragmentDatVeMotChieu.class, null);
        tabHost.addTab(tabHost.newTabSpec("Khứ hồi").setIndicator("Khứ hồi"), FragmentDatVeKhuHoi.class, null);
        setForTabHost();
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        tabHost = null;
    }

    //--------------------------------SET EVENT FOR TABHOST ------------------------------------------------------------------

    @SuppressWarnings("deprecation")
    public void setForTabHost(){
        tv = (TextView) tabHost.getCurrentTabView().findViewById(android.R.id.title); //for Selected Tab
        tv.setTextColor(getResources().getColor(R.color.colormain));
        tv.setTextSize(12);
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
                    //Set text color for tabhost
                    tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title); //Unselected Tabs
                    tv.setTextColor(Color.parseColor("#2c3e50"));
                    tv.setTextSize(12);
                }
                /*host.getTabWidget().getChildAt(host.getCurrentTab()).setBackgroundColor(Color.parseColor("#0000FF")); // selected*/
                //Set text color for tabhost
                tv = (TextView) tabHost.getCurrentTabView().findViewById(android.R.id.title); //for Selected Tab
                tv.setTextColor(getResources().getColor(R.color.colormain));
                tv.setTextSize(12);

            }
        });
    }


}
