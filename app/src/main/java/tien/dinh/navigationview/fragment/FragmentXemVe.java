package tien.dinh.navigationview.fragment;

import android.app.Activity;
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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import tien.dinh.navigationview.R;
import tien.dinh.navigationview.json.ReadJson;

/**
 * Created by DinhTien on 18-05-2016.
 */
public class FragmentXemVe extends Fragment{

    EditText editCMND;
    EditText editSDT;
    TextView txtThongBao;
    TextView txtTitleXemVe;
    Button btnXemVe;
    ReadJson ReadJsonThongTinVeKhach;
    String jsonThongTinVeKhach;
    OnNameSetListener onNameSetListener;
    private final String URL_THONGTINVE = "http://10.0.3.2:8080/xekhach/jsonxemve.php";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_xem_ve,container,false);
        editCMND = (EditText) view.findViewById(R.id.editCMND);
        editSDT = (EditText) view.findViewById(R.id.editSDT);
        txtThongBao = (TextView) view.findViewById(R.id.txtThongBao);
        txtTitleXemVe = (TextView) view.findViewById(R.id.fragment_xeve_title);
        btnXemVe = (Button) view.findViewById(R.id.btnXemVe);
        setTypeFace();

        btnXemVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReadJsonThongTinVeKhach = new ReadJson("",editCMND.getText().toString(),editSDT.getText().toString());
                //Kiểm tra đã nhập đủ thông tin các ô hay chưa
                if (editSDT.getText().toString().equalsIgnoreCase("") || editCMND.getText().toString().equalsIgnoreCase("") ){
                    txtThongBao.setVisibility(View.VISIBLE);
                }else {
                    try {
                        //gọi webservice
                       jsonThongTinVeKhach = new GoiWebService().execute(URL_THONGTINVE).get();
                        if (jsonThongTinVeKhach.equalsIgnoreCase("[]")){
                            Toast.makeText(getActivity(),"Thông tin nhập vào không chính xác!",Toast.LENGTH_LONG).show();
                        }else {
                            onNameSetListener.setThongTinVe(jsonThongTinVeKhach);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        

        return view;
    }

    public interface OnNameSetListener{
        public void setThongTinVe(String json);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            onNameSetListener = (OnNameSetListener)activity;
        } catch (Exception e){throw new ClassCastException(activity.toString() + " must implement OnNameSetListener");}

    }

    private void setTypeFace(){
        Typeface face1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Regular.ttf");
        Typeface face2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Bold.ttf");
        editCMND.setTypeface(face1);
        editSDT.setTypeface(face1);
        txtThongBao.setTypeface(face1);
        txtTitleXemVe .setTypeface(face1);
        btnXemVe.setTypeface(face1);
    }

    //--------------------------POST DATA TO SERVER AND GET DATA FROM SERVER------------------------------

    private class GoiWebService extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            return ReadJsonThongTinVeKhach.makePostRequestThongTinVe(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            //Json_DanhSach_Chuyen = s;
            Log.d("JSON THONG TIN VE: ", s);
            super.onPostExecute(s);
        }
    }

}
