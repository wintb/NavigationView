package tien.dinh.navigationview.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import tien.dinh.navigationview.Object.Object_ThongTinVe;
import tien.dinh.navigationview.R;
import tien.dinh.navigationview.Utils.AppConfig;
import tien.dinh.navigationview.json.JsonSoDoghe;

/**
 * Created by VuVanThang on 5/25/2016.
 */
public class XemVe extends Fragment {

    TextView txtHoTen;
    TextView txtSDT;
    TextView txtCMND;
    TextView txtGhiChu;
    TextView txtMaVe;
    TextView txtSoGhe;
    TextView txtMaTai;
    TextView txtBienSo;
    TextView txtTenChuyen;
    TextView txtTextNgayDi;
    TextView txtGioDi;
    Button btnHuyve;
    Button btnDoiVe;
    Button btnSuaVe;

    DoiGhe doighe;
    private JsonSoDoghe jsonSoDoghe;
    public static String sodogheDoiVe;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongtinve,container,false);

        txtHoTen = (TextView) view.findViewById(R.id.txtVeHoTen);
        txtSDT = (TextView) view.findViewById(R.id.txtVeSDT);
        txtCMND = (TextView) view.findViewById(R.id.txtVeCMND);
        txtGhiChu = (TextView) view.findViewById(R.id.txtVeGhiChu);
        txtMaVe = (TextView) view.findViewById(R.id.txtVeMaVe);
        txtSoGhe = (TextView) view.findViewById(R.id.txtVeSoGhe);
        txtMaTai = (TextView) view.findViewById(R.id.txtVeMaTai);
        txtBienSo = (TextView) view.findViewById(R.id.txtVeBienSoXe);
        txtTenChuyen = (TextView) view.findViewById(R.id.txtVeTenChuyenDi);
        txtTextNgayDi = (TextView) view.findViewById(R.id.txtVeNgayDi);
        txtGioDi = (TextView) view.findViewById(R.id.txtVeGioDi);
        btnHuyve = (Button) view.findViewById(R.id.btnHuyVe);
        btnDoiVe = (Button) view.findViewById(R.id.btnDoiVe);
        btnSuaVe = (Button) view.findViewById(R.id.btnSuaVe);

        doighe = (DoiGhe) getActivity();
        //Lấy dữ liệu từ fragment MyFragment2
        Bundle data = getArguments();
        String ThongTinVeKhach = data.getString("JsonThongTinVe");
        Type listType = new TypeToken<List<Object_ThongTinVe>>(){}.getType();
        final List<Object_ThongTinVe> thongTinVe  =  new Gson().fromJson(ThongTinVeKhach, listType);

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
                new AlertDialog.Builder(getActivity()).setTitle("Hủy vé").setMessage("Bạn có chắc chắc muốn hủy vé ?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
            }
        });

        btnDoiVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppConfig.KEY_CHECK_FRAGMENT = 0;
                //lấy chuỗi json các ghế đã chọn để đưa vào list
                try {
                    jsonSoDoghe = new JsonSoDoghe(thongTinVe.get(0).getMaChuyen());
                    sodogheDoiVe = new GoiWbServiceSoDoGhe().execute(AppConfig.URL_SODOGHE).get();
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
                        thongTinVe.get(0).getNgayDi());
            }
        });

        return view;
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

    public interface DoiGhe{
        void setDoiGhe(String MaVe, String MaChuyen, String TenChuyen, String GioDi, String NgayDi);
    }
}
