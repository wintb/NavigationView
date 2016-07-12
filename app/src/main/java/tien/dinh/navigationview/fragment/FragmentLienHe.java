package tien.dinh.navigationview.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import tien.dinh.navigationview.R;

/**
 * Created by DinhTien on 19-06-2016.
 */
public class FragmentLienHe extends Fragment {

    private TextView txtLienHe;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lien_he,container,false);
        getActivity().setTitle("Liên hệ");
        txtLienHe = (TextView)view.findViewById(R.id.txtLienHe);
        return view;
    }
}
