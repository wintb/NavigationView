package tien.dinh.navigationview.fragment;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import tien.dinh.navigationview.R;
import tien.dinh.navigationview.utils.CheckInternet;
import tien.dinh.navigationview.utils.CheckNumber;
import tien.dinh.navigationview.utils.ShowDialog;

/**
 * Created by DinhTien on 29-05-2016.
 */
public class FragmentNhapThongTinKhach extends Fragment {

    TextView editHoTen, editSDT, editCMND, editGhiChu, txtThongBao, txtTitle;
    Button btnDatVe;
    DatVe interfaceDatVe;
    LinearLayout layout_NhapThongTinKhach;

    private String HoTen = "";
    private String CMND = "";
    private String SDT  = "";
    private String GhiChu = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nhap_thong_tin_khach,container,false);

        editCMND = (TextView)view.findViewById(R.id.editNhapCMND);
        editGhiChu = (TextView)view.findViewById(R.id.editNhapGhichu);
        editSDT = (TextView)view.findViewById(R.id.editNhapSDT);
        editHoTen = (TextView)view.findViewById(R.id.editNhapHoTen);
        btnDatVe = (Button)view.findViewById(R.id.btnDatVe);
        txtThongBao = (TextView)view.findViewById(R.id.txtThongBao);
        txtTitle = (TextView) view.findViewById(R.id.fragment_nhapthongtinve_title);
        layout_NhapThongTinKhach = (LinearLayout) view.findViewById(R.id.layout_fragment_nhapthongtin);
        setupUI(layout_NhapThongTinKhach);
        setTypeFace();

        interfaceDatVe = (DatVe) getActivity();


        btnDatVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (CheckInternet.isConnected(getActivity())){

                    HoTen = editHoTen.getText().toString();
                    CMND = editCMND.getText().toString();
                    SDT = editSDT.getText().toString();
                    GhiChu = editGhiChu.getText().toString();

                    if (HoTen.equalsIgnoreCase("")
                            || CMND.equalsIgnoreCase("")
                            || SDT.equalsIgnoreCase("")
                            || GhiChu.equalsIgnoreCase("")) {

                        txtThongBao.setVisibility(View.VISIBLE);
                        txtThongBao.setText("Bạn cần phải nhập đầy đủ thông tin");
                        return;
                    }

                    if (CMND.length() != 9 || !CheckNumber.checkNumber(CMND)){
                        txtThongBao.setVisibility(View.VISIBLE);
                        txtThongBao.setText("Số cmnd không phù hợp");
                        return;
                    }

                    if ((SDT.length() < 10 || SDT.length() > 11) || !CheckNumber.checkNumber(SDT)){
                        txtThongBao.setVisibility(View.VISIBLE);
                        txtThongBao.setText("Số điện thoại không phù hợp");
                        return;
                    }

                    Bundle data = getArguments();
                    String MaChuyen = data.getString("MaChuyen");
                    String MaTai = data.getString("MaTai");
                    String TenChuyen = data.getString("TenChuyen");
                    String GioDi = data.getString("GioDi");
                    String NgayDi = data.getString("NgayDi");

                    List<String> listSoGhe = FragmentSoDoGheTang1.listGheDaChonTang1;
                    int soLuong = listSoGhe.size();
                    String SoGhe = listSoGhe.get(0);

                    String SDT_3SoCuoi = SDT.substring(SDT.length() - 3);
                    String MaVe = SoGhe + SDT_3SoCuoi;


                    Log.d("JSON_NHAP_THONG_TIN_VE", MaChuyen + "++" + MaTai);
                    Log.d("LIST GHE", listSoGhe.toString());
                    Log.d("SO LUONG", String.valueOf(soLuong));

                    interfaceDatVe.clickDatVe(TenChuyen, GioDi, NgayDi, MaTai, MaChuyen, HoTen, CMND, SDT, GhiChu, listSoGhe, soLuong, MaVe);
                }else{
                    String t = "Warning";
                    String m = "Vui lòng kiểm tra kết nối Internet.";
                    ShowDialog.show(getActivity(), t, m);
                }

            }
        });
        return view;
    }

    public void setupUI( final View view) {

        //Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    return false;
                }

            });
        }
        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {

            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

                View innerView = ((ViewGroup) view).getChildAt(i);

                setupUI( innerView);
            }
        }
    }


    private void setTypeFace(){
        Typeface face1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Regular.ttf");
        Typeface face2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Bold.ttf");
        editHoTen.setTypeface(face1);
        editSDT.setTypeface(face1);
        txtTitle.setTypeface(face1);
        editCMND.setTypeface(face1);
        editGhiChu.setTypeface(face1);
        txtThongBao.setTypeface(face1);
        btnDatVe.setTypeface(face1);
    }

    //========== Interface ==========//
    public interface DatVe{
        void clickDatVe(String TenChuyen, String GioDi, String NgayDi, String MaTai,String MaChuyen, String HoTen,
                        String CMND, String SDT, String GhiChu, List<String> listSoGhe, int SoLuong, String MaVe);
    }
}
