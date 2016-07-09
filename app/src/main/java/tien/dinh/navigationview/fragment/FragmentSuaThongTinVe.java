package tien.dinh.navigationview.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.concurrent.ExecutionException;

import butterknife.Bind;
import butterknife.ButterKnife;
import tien.dinh.navigationview.R;
import tien.dinh.navigationview.activity.MainActivity;
import tien.dinh.navigationview.json.JsonSuaVe;
import tien.dinh.navigationview.mics.Constant;
import tien.dinh.navigationview.utils.CheckInternet;
import tien.dinh.navigationview.utils.ShowDialog;

/**
 * Created by VuVanThang on 6/18/2016.
 */
public class FragmentSuaThongTinVe extends Fragment {
    @Bind(R.id.editNhapCMND)
    EditText editCMND;
    @Bind(R.id.editNhapGhichu)
    EditText editNoiXuong;
    @Bind(R.id.editNhapSDT)
    EditText editSDT;
    @Bind(R.id.editNhapHoTen)
    EditText editHoTen;
    @Bind(R.id.btnSuaVe)
    Button btnSuaVe;
    @Bind(R.id.layout_fragment_suathongtinve)
    LinearLayout layout_suathongtinve;

    JsonSuaVe jsonSuaVe;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sua_thong_tin_ve, container, false);
        ButterKnife.bind(this,view);
        setupUI(layout_suathongtinve);
        Bundle data= getArguments();
        final String Hoten = data.getString("HoTen");
        final String SDT = data.getString("SDT");
        final String CMND = data.getString("CMND");
        final String NoiXuong = data.getString("NoiXuong");
        final String MaChuyen = data.getString("MaChuyen");
        final String MaVe = data.getString("MaVe");

        editHoTen.setText(Hoten);
        editSDT.setText(SDT);
        editCMND.setText(CMND);
        editNoiXuong.setText(NoiXuong);


        btnSuaVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (CheckInternet.isConnected(getActivity())){

                    jsonSuaVe = new JsonSuaVe(editHoTen.getText().toString(),
                            editSDT.getText().toString(),
                            editCMND.getText().toString(),
                            editNoiXuong.getText().toString(),
                            MaChuyen,
                            MaVe);
                    try {
                        String result = new GoiWbServiceSuaVe().execute(Constant.URL_SUA_VE).get();

                        new AlertDialog.Builder(getActivity()).setTitle("Sửa vé").setMessage(result)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(getActivity(), MainActivity.class);
                                        startActivity(intent);
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
                }else{

                    String title = "Warning";
                    String message = "Vui lòng kiểm tra kết nối Internet.";
                    ShowDialog.show(getActivity(), title, message);
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

    //-------------------------POST DATA LEN SERVER DE SUA VE-----------------------

    private class GoiWbServiceSuaVe extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            return jsonSuaVe.makePostRequest_SuaVe(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

}
