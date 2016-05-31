package tien.dinh.navigationview.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import tien.dinh.navigationview.R;

/**
 * Created by DinhTien on 29-05-2016.
 */
public class Nhap_Thong_Tin_Fragment extends Fragment {

    TextView editHoTen, editSDT, editCMND, editGhiChu;
    Button btnDatVe;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nhap_thong_tin_khach,container,false);

        editCMND = (TextView)view.findViewById(R.id.editNhapCMND);
        editGhiChu = (TextView)view.findViewById(R.id.editNhapGhichu);
        editSDT = (TextView)view.findViewById(R.id.editNhapSDT);
        editHoTen = (TextView)view.findViewById(R.id.editNhapHoTen);
        btnDatVe = (Button)view.findViewById(R.id.btnDatVe);

        btnDatVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String HoTen = editHoTen.getText().toString();
                String CMND = editCMND.getText().toString();
                String SDT = editSDT.getText().toString();
                String GhiChu = editGhiChu.getText().toString();

                Bundle data = getArguments();
                String MaChuyen = data.getString("MaChuyen");
                String MaTai = data.getString("MaTai");
                List<String> soGhe = Datve_First_Floor_Fragment.listGheDaChonTang1;
                int soLuong = soGhe.size();
                Log.d("JSON_NHAP_THONG_TIN_VE", MaChuyen + "++" + MaTai);
                Log.d("LIST GHE", soGhe.toString());
                Log.d("SO LUONG",String.valueOf(soLuong));
            }
        });
        return view;
    }

    //========== Interface ==========//
    public interface DatVe{
        void clickDatVe(String MaTai,String MaChuyen, String HoTen,
                        String CMND, String SDT, String GhiChu, String SoGhe, String SoLuong);
    }
}
