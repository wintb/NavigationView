package tien.dinh.navigationview.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tien.dinh.navigationview.R;

/**
 * Created by DinhTien on 18-05-2016.
 */
public class MyFragment2 extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_xemve,container,false);
        return view;
    }
}
