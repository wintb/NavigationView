package om.bluebirdaward.busticket.fragment;

import android.graphics.Typeface;
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

import butterknife.Bind;
import butterknife.ButterKnife;
import om.bluebirdaward.busticket.R;
import om.bluebirdaward.busticket.adapter.AdapterDanhSachChuyen;
import om.bluebirdaward.busticket.dao.Chuyen;

/**
 * Created by VuVanThang on 4/1/2016.
 */
public class FragmentDanhSachChuyen extends Fragment {

    @Bind(R.id.Fragment_DanhSachchuyen_txtChuyenDi)
    TextView chuyendi;
    @Bind(R.id.Fragment_DanhSachchuyen_txtNgayDi)
    TextView ngaydi;
    @Bind(R.id.Fragment_DanhSachchuyen_txtLoTrinh)
    TextView lotrinh;
    @Bind(R.id.Fragment_DanhSachchuyen_txtTitleDanhSachChuyen)
    TextView txtTitleDanhSachChuyen;
    @Bind(R.id.Fragment_DanhSachchuyen_txtTai)
    TextView txtTai;
    @Bind(R.id.Fragment_DanhSachchuyen_txtGioDi)
    TextView txtGioDi;
    @Bind(R.id.Fragment_DanhSachchuyen_txtGioDen)
    TextView txtGioDen;
    @Bind(R.id.Fragment_DanhSachchuyen_txtGiaVe)
    TextView txtGiaVe;
    @Bind(R.id.Fragment_DanhSachchuyen_txtChon)
    TextView txtChon;

    @Bind(R.id.DanhSachChuyenDi)
    ListView listView;
    String Json_DanhSach_Chuyen ;
    Gson gson;
    AdapterDanhSachChuyen customApdaterOneTrip;
    public static String ChuyenDi;
    public static String NgayDi;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_danh_sach_chuyen, container, false);
        ButterKnife.bind(this, rootView);
        setTypeFace();
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

    private void setTypeFace(){
        Typeface face1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Regular.ttf");
        Typeface face2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Bold.ttf");
        chuyendi.setTypeface(face1);
        ngaydi.setTypeface(face1);
        lotrinh.setTypeface(face1);
        txtTitleDanhSachChuyen.setTypeface(face1);
        txtTai.setTypeface(face1);
        txtGioDi.setTypeface(face1);
        txtGioDen.setTypeface(face1);
        txtGiaVe.setTypeface(face1);
        txtChon.setTypeface(face1);
    }



}
