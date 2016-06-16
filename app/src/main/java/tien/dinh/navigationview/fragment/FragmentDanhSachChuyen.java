package tien.dinh.navigationview.fragment;

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

import tien.dinh.navigationview.dao.Chuyen;
import tien.dinh.navigationview.R;
import tien.dinh.navigationview.adapter.AdapterDanhSachChuyen;

/**
 * Created by VuVanThang on 4/1/2016.
 */
public class FragmentDanhSachChuyen extends Fragment {

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
    AdapterDanhSachChuyen customApdaterOneTrip;
    public static String ChuyenDi;
    public static String NgayDi;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_danh_sach_chuyen, container, false);
        listView = (ListView) rootView.findViewById(R.id.DanhSachChuyenDi);
        chuyendi = (TextView) rootView.findViewById(R.id.txtChuyenDi);
        ngaydi = (TextView) rootView.findViewById(R.id.txtNgayDi);
        lotrinh = (TextView) rootView.findViewById(R.id.txtLoTrinh);

        Bundle data = getArguments();
        chuyendi.setText(data.getString("ChuyenDi"));
        ngaydi.setText(data.getString("NgayDi"));
        Json_DanhSach_Chuyen = data.getString("JsonChuyen");
        ChuyenDi = data.getString("ChuyenDi");
        NgayDi = data.getString("NgayDi");
        //Show list trip
        gson =new Gson();
        Type listType = new TypeToken<List<Chuyen>>(){}.getType();
        final List<Chuyen> list  =  gson.fromJson(Json_DanhSach_Chuyen,listType);
        customApdaterOneTrip = new AdapterDanhSachChuyen(getActivity(),list);
        listView.setAdapter(customApdaterOneTrip);
        //get distance
        lotrinh.setText(list.get(0).getLoTrinh());

        return rootView;

    }


}
