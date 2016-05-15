package tien.dinh.navigationview.EventForTabHost.Fragment_OneWay;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import tien.dinh.navigationview.Object.Object_Chuyen;
import tien.dinh.navigationview.R;
import tien.dinh.navigationview.adapter.CustomAdapterOneTrip;

/**
 * Created by VuVanThang on 4/1/2016.
 */
public class OnWay_ListTrip extends Fragment {

    TextView chuyendi;
    TextView ngaydi;
    TextView lotrinh;
    TextView matai;
    TextView giodi;
    TextView gioden;
    TextView giave;
    TextView chon;
    String Json_DanhSach_Chuyen ;
    ListView listView;
    Gson gson;
    CustomAdapterOneTrip customApdaterOneTrip;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tabhost_oneway_listtrip, container, false);
        listView = (ListView) rootView.findViewById(R.id.DanhSachChuyenDi);
        chuyendi = (TextView) rootView.findViewById(R.id.txtChuyenDi);
        ngaydi = (TextView) rootView.findViewById(R.id.txtNgayDi);
        lotrinh = (TextView) rootView.findViewById(R.id.txtLoTrinh);

        Bundle data = getArguments();
        chuyendi.setText(data.getString("ChuyenDi"));
        ngaydi.setText(data.getString("NgayDi"));
        Json_DanhSach_Chuyen = data.getString("JsonChuyen");

        //Show list trip
        gson =new Gson();
        Type listType = new TypeToken<List<Object_Chuyen>>(){}.getType();
        List<Object_Chuyen> list  =  gson.fromJson(Json_DanhSach_Chuyen,listType);
        customApdaterOneTrip = new CustomAdapterOneTrip(getActivity(),list);
        listView.setAdapter(customApdaterOneTrip);
        //get distance
        lotrinh.setText(list.get(0).getLoTrinh());
        return rootView;

    }


}
