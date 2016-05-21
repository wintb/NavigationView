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
public class Datve_First_Floor_Fragment extends Fragment {

    ImageView A1D;
    ImageView A2D;
    ImageView A3D;
    ImageView A4D;
    ImageView A5D;

    ImageView B1D;
    ImageView B2D;
    ImageView B3D;
    ImageView B4D;
    ImageView B5D;

    ImageView C1D;
    ImageView C2D;
    ImageView C3D;
    ImageView C4D;
    ImageView C5D;

    ImageView D1D;
    ImageView D2D;
    ImageView D3D;
    ImageView D4D;
    ImageView D5D;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.datve_first_floor,container,false);

        A1D = (ImageView) rootView.findViewById(R.id.A1D);
        A2D = (ImageView) rootView.findViewById(R.id.A2D);
        A3D = (ImageView) rootView.findViewById(R.id.A3D);
        A4D = (ImageView) rootView.findViewById(R.id.A4D);
        A5D = (ImageView) rootView.findViewById(R.id.A5D);
        B1D = (ImageView) rootView.findViewById(R.id.B1D);
        B2D = (ImageView) rootView.findViewById(R.id.B2D);
        B3D = (ImageView) rootView.findViewById(R.id.B3D);
        B4D = (ImageView) rootView.findViewById(R.id.B4D);
        B5D = (ImageView) rootView.findViewById(R.id.B5D);
        C1D = (ImageView) rootView.findViewById(R.id.C1D);
        C2D = (ImageView) rootView.findViewById(R.id.C2D);
        C3D = (ImageView) rootView.findViewById(R.id.C3D);
        C4D = (ImageView) rootView.findViewById(R.id.C4D);
        C5D = (ImageView) rootView.findViewById(R.id.C5D);
        D1D = (ImageView) rootView.findViewById(R.id.A1D);
        D2D = (ImageView) rootView.findViewById(R.id.A2D);
        D3D = (ImageView) rootView.findViewById(R.id.A3D);
        D4D = (ImageView) rootView.findViewById(R.id.A4D);
        D5D = (ImageView) rootView.findViewById(R.id.A5D);





        return rootView;
    }


}
