package tien.dinh.navigationview.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tien.dinh.navigationview.R;

/**
 * Created by VuVanThang on 5/17/2016.
 */
public class MyFragment2 extends android.support.v4.app.Fragment {

    public MyFragment2(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_xemve,container,false);
        return rootView;

    }
}
