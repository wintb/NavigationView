package tien.dinh.navigationview.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import tien.dinh.navigationview.R;

/**
 * Created by DinhTien on 15-05-2016.
 */
public class Datve_Second_Floor_Fragment extends Fragment {

    ImageView A1T;
    ImageView A2T;
    ImageView A3T;
    ImageView A4T;
    ImageView A5T;

    ImageView B1T;
    ImageView B2T;
    ImageView B3T;
    ImageView B4T;
    ImageView B5T;

    ImageView C1T;
    ImageView C2T;
    ImageView C3T;
    ImageView C4T;
    ImageView C5T;

    ImageView D1T;
    ImageView D2T;
    ImageView D3T;
    ImageView D4T;
    ImageView D5T;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.datve_second_floor,container,false);

        A1T = (ImageView) rootView.findViewById(R.id.A1T);
        A2T = (ImageView) rootView.findViewById(R.id.A2T);
        A3T = (ImageView) rootView.findViewById(R.id.A3T);
        A4T = (ImageView) rootView.findViewById(R.id.A4T);
        A5T = (ImageView) rootView.findViewById(R.id.A5T);
        B1T = (ImageView) rootView.findViewById(R.id.B1T);
        B2T = (ImageView) rootView.findViewById(R.id.B2T);
        B3T = (ImageView) rootView.findViewById(R.id.B3T);
        B4T = (ImageView) rootView.findViewById(R.id.B4T);
        B5T = (ImageView) rootView.findViewById(R.id.B5T);
        C1T = (ImageView) rootView.findViewById(R.id.C1T);
        C2T = (ImageView) rootView.findViewById(R.id.C2T);
        C3T = (ImageView) rootView.findViewById(R.id.C3T);
        C4T = (ImageView) rootView.findViewById(R.id.C4T);
        C5T = (ImageView) rootView.findViewById(R.id.C5T);
        D1T = (ImageView) rootView.findViewById(R.id.A1T);
        D2T = (ImageView) rootView.findViewById(R.id.A2T);
        D3T = (ImageView) rootView.findViewById(R.id.A3T);
        D4T = (ImageView) rootView.findViewById(R.id.A4T);
        D5T = (ImageView) rootView.findViewById(R.id.A5T);

        return rootView;
    }
}
