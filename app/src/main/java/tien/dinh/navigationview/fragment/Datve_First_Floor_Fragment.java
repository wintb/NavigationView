package tien.dinh.navigationview.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import tien.dinh.navigationview.R;

/**
 * Created by DinhTien on 15-05-2016.
 */
public class Datve_First_Floor_Fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.datve_first_floor,container,false);
        Button button = (Button) rootView.findViewById(R.id.btnback);
        ImageView imageView;

        return rootView;
    }


}
