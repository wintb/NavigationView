package om.bluebirdaward.busticket.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import butterknife.Bind;
import butterknife.ButterKnife;
import om.bluebirdaward.busticket.R;
import om.bluebirdaward.busticket.activity.MainActivity;
import om.bluebirdaward.busticket.dao.ThongTinVe;
import om.bluebirdaward.busticket.dao.customer.Ticket;
import om.bluebirdaward.busticket.interfaces.Response;
import om.bluebirdaward.busticket.json.JsonHuyVe;
import om.bluebirdaward.busticket.json.JsonSoDoghe;
import om.bluebirdaward.busticket.mics.Constant;
import om.bluebirdaward.busticket.request.DeleteCustomerRequest;
import om.bluebirdaward.busticket.request.EditCustomerRequest;
import om.bluebirdaward.busticket.utils.CheckInternet;
import om.bluebirdaward.busticket.utils.ShowDialog;

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
    @Bind(R.id.txtVeTenChuyenDi)
    TextView txtTenChuyen;
    @Bind(R.id.txtVeNgayDi)
    TextView txtTextNgayDi;
    @Bind(R.id.txtVeGioDi)
    TextView txtGioDi;
    @Bind(R.id.txtNgayDatVe)
    TextView txtNgayDatVe;
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

    private ArrayList<Ticket> arrTicket = new ArrayList<>();
    private String phone;
    private String identity_number;
    private String fullname;
    private String note;
    private int quantity;
    private String date;
    private String start;
    private String code_driver;
    private String route;
    private String create_date;
    private String soghe = "";
    private FragmentActivity myContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thong_tin_ve_da_dat, container, false);
        ButterKnife.bind(this, view);
        setTypeFace();

        Bundle data = this.getArguments();

        phone = data.getString("phone");
        identity_number = data.getString("identity_number");
        fullname = data.getString("fullname");
        note = data.getString("note");
        quantity = data.getInt("quantity");
        date = data.getString("date");
        start = data.getString("start");
        code_driver = data.getString("code_driver");
        route = data.getString("route");
        create_date = data.getString("create_date");

        txtHoTen.setText(fullname);
        txtSDT.setText(phone);
        txtCMND.setText(identity_number);
        txtGhiChu.setText(note);
        txtSoGhe.setText(String.valueOf(quantity));
        txtTextNgayDi.setText(date);
        txtGioDi.setText(start);
        for (int i = 0; i < data.getInt("quantity"); i++) {
            arrTicket.add((Ticket) data.getSerializable("ticket_" + (i + 1)));
        }
        for (int i = 0; i < arrTicket.size(); i++) {
            soghe += arrTicket.get(i).seat;
            if (i < arrTicket.size() - 1)
                soghe += " - ";

        }
        txtMaVe.setText(soghe);
        txtMaTai.setText(code_driver);
        txtTenChuyen.setText(route);
        txtNgayDatVe.setText(create_date);

        deleteTicket();
        editTicket();
        changeTicket();


        return view;
    }

    public void deleteTicket(){
        btnHuyve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (CheckInternet.isConnected(getActivity())) {

                    final Map<String, String> data = new HashMap<>();
                    data.put("identity_number", identity_number);
                    data.put("phone", phone);

                    // hien thong bao hoi nguoi dung co chac chan huy ve hay khong
                    new AlertDialog.Builder(getActivity()).setTitle("Hủy vé").setMessage("Bạn có chắc chắc muốn hủy vé ?")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DeleteCustomerRequest.deleteCustomer(data, new Response() {
                                        @Override
                                        public void onStart() {
                                        }

                                        @Override
                                        public void onSuccess(int code, String message, Object obj) {
                                        }

                                        @Override
                                        public void onFailure() {
                                        }
                                    });
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).show();
                } else {

                    String title = "Warning";
                    String message = "Vui lòng kiểm tra kết nối Internet.";
                    ShowDialog.show(getActivity(), title, message);
                }
            }
        });
    }

    public void editTicket(){

        btnSuaVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (CheckInternet.isConnected(getActivity())) {

                    FragmentNhapThongTinKhach editInfo = new FragmentNhapThongTinKhach();
                    myContext.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentholder, editInfo)
                            .commit();
                } else {

                    String title = "Warning";
                    String message = "Vui lòng kiểm tra kết nối Internet.";
                    ShowDialog.show(getActivity(), title, message);
                }

            }
        });
    }

    public void changeTicket(){
        btnDoiVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (CheckInternet.isConnected(getActivity())){

                }else{

                    String title = "Warning";
                    String message = "Vui lòng kiểm tra kết nối Internet.";
                    ShowDialog.show(getActivity(), title, message);
                }

            }
        });
    }

    private void setTypeFace() {
        Typeface face1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Regular.ttf");
        Typeface face2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Bold.ttf");
        ttxVeDadatTitle.setTypeface(face1);
        ttxVeDadatThongTinKhach.setTypeface(face1);
        ttxVeDadatThongTinVe.setTypeface(face1);
        btnHuyve.setTypeface(face1);
        btnDoiVe.setTypeface(face1);
        btnSuaVe.setTypeface(face1);
    }

    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    public interface DoiGhe {
        void setDoiGhe(String MaVe, String MaChuyen, String TenChuyen, String GioDi, String NgayDi, String SDTKhach);
    }

    public interface SuaVe {
        void setSuaVe(String HoTen, String SDT, String CMND, String NoiXuong, String MaChuyen, String MaVe);
    }
}
