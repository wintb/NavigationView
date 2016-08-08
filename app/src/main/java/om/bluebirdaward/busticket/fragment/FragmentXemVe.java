package om.bluebirdaward.busticket.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import om.bluebirdaward.busticket.R;
import om.bluebirdaward.busticket.abstracts.AbstractResponse;
import om.bluebirdaward.busticket.dao.customer.InfoCustomer;
import om.bluebirdaward.busticket.dao.customer.ResponseInfoCustomer;
import om.bluebirdaward.busticket.dao.customer.Ticket;
import om.bluebirdaward.busticket.interfaces.Response;
import om.bluebirdaward.busticket.json.ReadJson;
import om.bluebirdaward.busticket.mics.Constant;
import om.bluebirdaward.busticket.request.InfoCustomerRequest;
import om.bluebirdaward.busticket.utils.CheckInternet;
import om.bluebirdaward.busticket.utils.ShowDialog;

/**
 * Created by DinhTien on 18-05-2016.
 */
public class FragmentXemVe extends Fragment{

    EditText editCMND;
    EditText editSDT;
    TextView txtThongBao;
    TextView txtTitleXemVe;
    Button btnXemVe;
    LinearLayout layout_xemve;
    private FragmentActivity myContext;
    private InfoCustomer infoCustomer;
    private Ticket ticket;
    private ArrayList<Ticket> arrTicket;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_xem_ve,container,false);
        getActivity().setTitle("Xem vé");
        editCMND = (EditText) view.findViewById(R.id.editCMND);
        editSDT = (EditText) view.findViewById(R.id.editSDT);
        txtThongBao = (TextView) view.findViewById(R.id.txtThongBao);
        txtTitleXemVe = (TextView) view.findViewById(R.id.fragment_xeve_title);
        btnXemVe = (Button) view.findViewById(R.id.btnXemVe);
        layout_xemve = (LinearLayout) view.findViewById(R.id.layout_fragment_xemve);
        setupUI(layout_xemve);
        setTypeFace();

        btnXemVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (CheckInternet.isConnected(getActivity())) {
                    if (editCMND.getText().toString().equalsIgnoreCase("")
                            || editSDT.getText().toString().equalsIgnoreCase("")){
                        txtThongBao.setVisibility(View.VISIBLE);
                        txtThongBao.setText("Bạn cần phải nhập đầy đủ thông tin");
                    }else
                        viewInfoCustomer();

                } else {

                    String message = "Vui lòng kiểm tra kết nối Internet.";
                    ShowDialog.alertDialog(getActivity(), message);
                }

            }
        });
        

        return view;
    }

    public void viewInfoCustomer(){

        Map<String,String> data = new HashMap<>();
        data.put("identity_number",editCMND.getText().toString());
        data.put("phone", editSDT.getText().toString());
        data.put("type", String.valueOf(1));

        InfoCustomerRequest.getInfoCustomer(data, new Response() {

            @Override
            public void onStart() {
                ShowDialog.showLoading(getActivity());
            }

            @Override
            public void onSuccess(int code, String message, Object obj) {

                if (code == 0) {
                    ArrayList<InfoCustomer> infoCustomerArrayList = (ArrayList<InfoCustomer>) obj;
                    infoCustomer = infoCustomerArrayList.get(0);
                    setData();
                    ShowDialog.dimissLoading();
                }
            }

            @Override
            public void onFailure() {
                ShowDialog.dimissLoading();
                String message = "Vui lòng kiểm tra và thử lại.";
                ShowDialog.alertDialog(getActivity(), message);
            }
        });
    }


    public void setData(){
        int i=0;
        arrTicket = infoCustomer.ticket;
        Bundle data = new Bundle();
        for (Ticket t:arrTicket){
            i++;
            data.putSerializable("ticket_"+i, t);
        }
        data.putInt("quantity", infoCustomer.quantity);
        data.putInt("id", infoCustomer.id);
        data.putString("id_tripdate", infoCustomer.id_tripdate);
        data.putString("identity_number", infoCustomer.identity_number);
        data.putString("note", infoCustomer.note);
        data.putString("start", infoCustomer.start);
        data.putString("date", infoCustomer.date);
        data.putString("phone", infoCustomer.phone);
        data.putString("fullname",infoCustomer.fullname);
        data.putString("route", infoCustomer.route);
        data.putString("code_driver",infoCustomer.code_driver);
        data.putString("create_date",infoCustomer.create_date);

        FragmentThongTinVeDaDat xemVe = new FragmentThongTinVeDaDat();
        xemVe.setArguments(data);
        myContext.getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentholder, xemVe)
                .commit();
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

    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
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

}
