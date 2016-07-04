package tien.dinh.navigationview.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.Bind;
import butterknife.ButterKnife;
import tien.dinh.navigationview.R;
import tien.dinh.navigationview.dao.ThongTinVe;
import tien.dinh.navigationview.json.JsonHuyVe;
import tien.dinh.navigationview.json.JsonSoDoghe;
import tien.dinh.navigationview.mics.Constant;
import tien.dinh.navigationview.utils.CheckInternet;
import tien.dinh.navigationview.utils.ShowDialog;

/**
 * Created by VuVanThang on 5/25/2016.
 */
public class FragmentThongTinVeDaDat extends Fragment {

    @Bind(R.id.txtVeHoTen)
    TextView txtHoTen;
    @Bind(R.id.txtVeSDT)
    TextView txtSDT;
    @Bind(R.id.txtVeCMND)
    TextView txtCMND;
    @Bind(R.id.txtVeGhiChu)
    TextView txtGhiChu;
    @Bind(R.id.txtVeMaVe)
    TextView txtMaVe;
    @Bind(R.id.txtVeSoGhe)
    TextView txtSoGhe;
    @Bind(R.id.txtVeMaTai)
    TextView txtMaTai;
    @Bind(R.id.txtVeBienSoXe)
    TextView txtBienSo;
    @Bind(R.id.txtVeTenChuyenDi)
    TextView txtTenChuyen;
    @Bind(R.id.txtVeNgayDi)
    TextView txtTextNgayDi;
    @Bind(R.id.txtVeGioDi)
    TextView txtGioDi;
    @Bind(R.id.btnHuyVe)
    Button btnHuyve;
    @Bind(R.id.btnDoiVe)
    Button btnDoiVe;
    @Bind(R.id.btnSuaVe)
    Button btnSuaVe;
    @Bind(R.id.fragment_vedadat_title)
    TextView ttxVeDadatTitle;
    @Bind(R.id.fragment_vedadat_thongtinkhach)
    TextView ttxVeDadatThongTinKhach;
    @Bind(R.id.fragment_vedadat_thongtinve)
    TextView ttxVeDadatThongTinVe;

    DoiGhe doighe;
    private JsonSoDoghe jsonSoDoghe;
    public static String sodogheDoiVe;
    private JsonHuyVe jsonHuyVe;
    private SuaVe suaVe;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thong_tin_ve_da_dat,container,false);
        ButterKnife.bind(this,view);
        setTypeFace();

        doighe = (DoiGhe) getActivity();
        suaVe = (SuaVe) getActivity();

        //Lấy dữ liệu từ fragment FragmentXemVe
        Bundle data = getArguments();
        String ThongTinVeKhach = data.getString("JsonThongTinVe");
        Type listType = new TypeToken<List<ThongTinVe>>(){}.getType();
        final List<ThongTinVe> thongTinVe  =  new Gson().fromJson(ThongTinVeKhach, listType);

        txtHoTen.setText(thongTinVe.get(0).getHoTen());
        txtSDT.setText(thongTinVe.get(0).getSDTKhach());
        txtCMND.setText(thongTinVe.get(0).getCMND());
        txtGhiChu.setText(thongTinVe.get(0).getGhiChu());
        txtMaVe.setText(thongTinVe.get(0).getMaVe());
        txtSoGhe.setText(thongTinVe.get(0).getSoGhe());
        txtMaTai.setText(thongTinVe.get(0).getMaTai());
        txtBienSo.setText(thongTinVe.get(0).getBienSo());
        txtTenChuyen.setText(thongTinVe.get(0).getTenChuyen());
        txtTextNgayDi.setText(thongTinVe.get(0).getNgayDi());
        txtGioDi.setText(thongTinVe.get(0).getGioDi());

        btnHuyve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (CheckInternet.isConnected(getActivity())){

                    // hien thong bao hoi nguoi dung co chac chan huy ve hay khong
                    new AlertDialog.Builder(getActivity()).setTitle("Hủy vé").setMessage("Bạn có chắc chắc muốn hủy vé ?")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    jsonHuyVe = new JsonHuyVe(thongTinVe.get(0).getMaChuyen(), thongTinVe.get(0).getMaVe());
                                    String result = null;
                                    try {
                                        result = new GoiWebServiceHuyVe().execute(Constant.URL_HUY_VE).get();
                                        // thong bao huy ve co thanh cong hay khong
                                        new AlertDialog.Builder(getActivity()).setTitle("Hủy vé").setMessage(result)
                                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {

                                                    }
                                                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                            }
                                        }).show();

                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    } catch (ExecutionException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();
                }else{

                    String title = "Warning";
                    String message = "Vui lòng kiểm tra kết nối Internet.";
                    ShowDialog.show(getActivity(), title, message);
                }
            }
        });

        btnDoiVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (CheckInternet.isConnected(getActivity())){

                    Constant.KEY_CHECK_FRAGMENT = 0;
                    //lấy chuỗi json các ghế đã chọn để đưa vào list
                    try {
                        jsonSoDoghe = new JsonSoDoghe(thongTinVe.get(0).getMaChuyen());
                        sodogheDoiVe = new GoiWbServiceSoDoGhe().execute(Constant.URL_SODOGHE).get();
                        Log.d("JSON_SODOGHE_DOIVE:", sodogheDoiVe);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    doighe.setDoiGhe(thongTinVe.get(0).getMaVe(),
                            thongTinVe.get(0).getMaChuyen(),
                            thongTinVe.get(0).getTenChuyen(),
                            thongTinVe.get(0).getGioDi(),
                            thongTinVe.get(0).getNgayDi(),
                            thongTinVe.get(0).getSDTKhach());
                }else{

                    String title = "Warning";
                    String message = "Vui lòng kiểm tra kết nối Internet.";
                    ShowDialog.show(getActivity(), title, message);
                }

            }
        });

        btnSuaVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (CheckInternet.isConnected(getActivity())){
                    suaVe.setSuaVe(thongTinVe.get(0).getHoTen(),
                            thongTinVe.get(0).getSDTKhach(),
                            thongTinVe.get(0).getCMND(),
                            thongTinVe.get(0).getGhiChu(),
                            thongTinVe.get(0).getMaChuyen(),
                            thongTinVe.get(0).getMaVe());
                }else{

                    String title = "Warning";
                    String message = "Vui lòng kiểm tra kết nối Internet.";
                    ShowDialog.show(getActivity(), title, message);
                }

            }
        });


        return view;
    }

    private void setTypeFace(){
        Typeface face1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Regular.ttf");
        Typeface face2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Bold.ttf");
        ttxVeDadatTitle.setTypeface(face1);
        ttxVeDadatThongTinKhach.setTypeface(face1);
        ttxVeDadatThongTinVe.setTypeface(face1);
        btnHuyve.setTypeface(face1);
        btnDoiVe.setTypeface(face1);
        btnSuaVe.setTypeface(face1);
    }






    //-------------------------SELECT SOGHE TỪ SERVER ĐỂ KIỂM TRA GHẾ ĐÃ CHỌN-----------------------

    private class GoiWbServiceSoDoGhe extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            return jsonSoDoghe.makePostRequestSoDoGhe(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

    //-----------------------POST DATA LÊN SERVER ĐỂ HUY VE-----------------------------------------

    private class GoiWebServiceHuyVe extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {
            return jsonHuyVe.makePostRequest_HuyVe(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

    public interface DoiGhe{
        void setDoiGhe(String MaVe, String MaChuyen, String TenChuyen, String GioDi, String NgayDi, String SDTKhach);
    }

    public interface SuaVe{
        void setSuaVe(String HoTen, String SDT, String CMND, String NoiXuong, String MaChuyen, String MaVe);
    }
}
