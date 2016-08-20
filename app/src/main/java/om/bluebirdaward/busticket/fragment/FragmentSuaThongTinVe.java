package om.bluebirdaward.busticket.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import butterknife.Bind;
import butterknife.ButterKnife;
import om.bluebirdaward.busticket.R;
import om.bluebirdaward.busticket.activity.MainActivity;
import om.bluebirdaward.busticket.interfaces.Response;
import om.bluebirdaward.busticket.json.JsonSuaVe;
import om.bluebirdaward.busticket.mics.Constant;
import om.bluebirdaward.busticket.request.EditCustomerRequest;
import om.bluebirdaward.busticket.utils.CheckInternet;
import om.bluebirdaward.busticket.utils.ShowDialog;

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
    AppCompatButton btnSuaVe;
    @Bind(R.id.layout_fragment_suathongtinve)
    LinearLayout layout_suathongtinve;

    private String phone;
    private String identity_number;
    private String fullname;
    private String note;
    private int id;
    private FragmentActivity myContext;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sua_thong_tin_ve, container, false);
        ButterKnife.bind(this, view);
        //setupUI(layout_suathongtinve);

        Bundle data = this.getArguments();

        phone = data.getString("phone");
        identity_number = data.getString("identity_number");
        fullname = data.getString("fullname");
        note = data.getString("note");
        id = data.getInt("id");

        editHoTen.setText(fullname);
        editSDT.setText(phone);
        editCMND.setText(identity_number);
        editNoiXuong.setText(note);


        btnSuaVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (CheckInternet.isConnected(getActivity())) {

                    final Map<String, String> data = new HashMap<>();
                    data.put("fullname", editHoTen.getText().toString());
                    data.put("phone", editSDT.getText().toString());
                    data.put("identity_number", editCMND.getText().toString());
                    data.put("note", editNoiXuong.getText().toString());
                    data.put("id", String.valueOf(id));

                    EditCustomerRequest.editCustomer(data, new Response() {
                        @Override
                        public void onStart() {
                        }

                        @Override
                        public void onSuccess(int code, String message, Object obj) {

                            if (code == 0)
                                ShowDialog.alertDialogResult(myContext,"Sửa vé thành công", "nhấn OK để tiếp tục",code);
                        }

                        @Override
                        public void onFailure() {
                            ShowDialog.alertDialogResult(myContext, "Sửa thông tin không thành công", "Vui lòng thử lại.", -1);
                        }
                    });

                } else {

                    String message = "Vui lòng kiểm tra kết nối Internet.";
                    ShowDialog.alertDialog(getActivity(), message);
                }
            }
        });

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        myContext = (FragmentActivity) activity;
        super.onAttach(activity);
    }

    public void setupUI(final View view) {

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

                setupUI(innerView);
            }
        }
    }

}
