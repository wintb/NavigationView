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
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import tien.dinh.navigationview.R;
import tien.dinh.navigationview.mics.Constant;

/**
 * Created by DinhTien on 31-05-2016.
 */
public class FragmentThongTinVeVuaDat extends Fragment {

    private TextView txtHoTenVuaDat;
    private TextView txtSDTVuaDat;
    private TextView txtCMNDVuaDat;
    private TextView txtGhiChuVuaDat;
    private TextView txtMaVeVuaDat;
    private TextView txtSoGheVuaDat;
    private TextView txtMaTaiVuaDat;
    private TextView txtTenChuyenDiVuaDat;
    private TextView txtNgayDiVuaDat;
    private TextView txtGioDiVuaDat;
    private TextView txtSLVeVuaDat ;
    private TextView txtXacNhan;
    private TextView txtSua;

    private String SDTKhach;
    private String HoTen;
    private String CMND;
    private String GhiChu;
    private String MaVe;
    private String MaChuyen;
    private int SoLuong;
    private String MaTai;
    private String SoGhe = "";

    backDatve backDatveFragment;
    String ThongBao;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_thong_tin_ve_vua_dat,container,false);

        txtCMNDVuaDat = (TextView)view.findViewById(R.id.txtCMNDVuaDat);
        txtHoTenVuaDat = (TextView)view.findViewById(R.id.txtHoTenVuaDat);
        txtSDTVuaDat = (TextView)view.findViewById(R.id.txtSDTVuaDat);
        txtGhiChuVuaDat = (TextView)view.findViewById(R.id.txtGhiChuVuaDat);
        txtSoGheVuaDat = (TextView)view.findViewById(R.id.txtSoGheVuaDat);
        txtMaTaiVuaDat = (TextView)view.findViewById(R.id.txtMaTaiVuaDat);
        txtTenChuyenDiVuaDat = (TextView)view.findViewById(R.id.txtTenChuyenDiVuaDat);
        txtNgayDiVuaDat = (TextView)view.findViewById(R.id.txtNgayDiVuaDat);
        txtGioDiVuaDat = (TextView)view.findViewById(R.id.txtGioDiVuaDat);
        txtMaVeVuaDat = (TextView)view.findViewById(R.id.txtMaVeVuaDat);
        txtSLVeVuaDat = (TextView)view.findViewById(R.id.txtSLVeVuaDat);
        txtXacNhan = (TextView)view.findViewById(R.id.txtXacNhan);
        txtSua = (TextView)view.findViewById(R.id.txtSua);
        backDatveFragment = (backDatve)getActivity();

        Bundle data = getArguments();

        SDTKhach = data.getString("SDT");
        HoTen = data.getString("HoTen");
        CMND = data.getString("CMND");
        GhiChu = data.getString("GhiChu");
        MaTai = data.getString("MaTai");
        MaVe = data.getString("MaVe");
        //SoGhe = data.getString("SoGhe");
        SoLuong = data.getInt("SoLuong");
        MaChuyen = data.getString("MaChuyen");

        for (int i = 0; i < FragmentSoDoGheTang1.listGheDaChonTang1.size(); i++){

            SoGhe += data.getString("SoGhe" + (i+1));
            if (i < FragmentSoDoGheTang1.listGheDaChonTang1.size() - 1)
                SoGhe += " - ";
        }

        txtCMNDVuaDat.setText(CMND);
        txtHoTenVuaDat.setText(HoTen);
        txtSDTVuaDat.setText(SDTKhach);
        txtGhiChuVuaDat.setText(GhiChu);
        txtMaTaiVuaDat.setText(MaTai);
        txtMaVeVuaDat.setText(MaVe);
        txtSoGheVuaDat.setText(SoGhe);
        txtSLVeVuaDat.setText(String.valueOf(SoLuong));


        txtXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new goiWebservice().execute(Constant.INSERT_URL);

                new AlertDialog.Builder(getActivity())
                        .setTitle("Đặt vé thành công")
                        .setMessage("\n\n" + "Quay trở lại menu chính để xem chi tiết vé đã đặt")
                        .setIcon(R.drawable.success)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                backDatveFragment.setBackDatVe();
                            }
                        }).show();
                Log.d("CLICK CLICK CLICK", "AAAAAAAAAAAAAa");
            }
        });

        txtSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        return view;
    }

    public interface backDatve{
        void setBackDatVe();
    }


    //============== AsyncTask class =================//

    private class goiWebservice extends AsyncTask<String, Integer, String>{

        @Override
        protected String doInBackground(String... params) {
            return makePostRequest(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }




    //================== Post request ===============//

    public String makePostRequest(String url){

        String result = "";
        HttpClient httpClient = new DefaultHttpClient();

        // url server
        HttpPost httpPost = new HttpPost(url);

        // cac tham so truyen vao
        List nameValuePair = new ArrayList(10);
        nameValuePair.add(new BasicNameValuePair("action","insert"));
        nameValuePair.add(new BasicNameValuePair("CMND",CMND));
        nameValuePair.add(new BasicNameValuePair("SDTKhach",SDTKhach));
        nameValuePair.add(new BasicNameValuePair("HoTen",HoTen));
        nameValuePair.add(new BasicNameValuePair("GhiChu",GhiChu));
        nameValuePair.add(new BasicNameValuePair("MaChuyen",MaChuyen));
        nameValuePair.add(new BasicNameValuePair("MaTai",MaTai));
        nameValuePair.add(new BasicNameValuePair("MaVe",MaVe));
        nameValuePair.add(new BasicNameValuePair("SoGhe",SoGhe));
        nameValuePair.add(new BasicNameValuePair("SoLuong",String.valueOf(SoLuong)));

        // encode post data
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity);
            ThongBao = result;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
