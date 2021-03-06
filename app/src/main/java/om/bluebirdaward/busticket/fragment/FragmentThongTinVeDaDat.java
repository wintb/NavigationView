package om.bluebirdaward.busticket.fragment;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import om.bluebirdaward.busticket.R;
import om.bluebirdaward.busticket.dao.customer.Ticket;
import om.bluebirdaward.busticket.interfaces.Response;
import om.bluebirdaward.busticket.mics.Constant;
import om.bluebirdaward.busticket.request.DeleteCustomerRequest;
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
    private int id;
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
    private String id_tripdate;
    private String soghe = "";
    private FragmentActivity myContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thong_tin_ve_da_dat, container, false);
        ButterKnife.bind(this, view);
        getActivity().setTitle("Vé đã đặt");
        setTypeFace();
        //gftgrfgf

        Bundle data = this.getArguments();

        id = data.getInt("id");
        id_tripdate = data.getString("id_tripdate");
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

        if (arrTicket.size()==0) {
            arrTicket = new ArrayList<>();
            for (int i = 0; i < data.getInt("quantity"); i++) {
                arrTicket.add((Ticket) data.getSerializable("ticket_" + (i + 1)));
            }
            for (int i = 0; i < arrTicket.size(); i++) {
                soghe += arrTicket.get(i).seat;
                if (i < arrTicket.size() - 1)
                    soghe += " - ";

            }
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

    public void deleteTicket() {
        btnHuyve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialog.alertDialogConfirm(myContext, "Bạn có chắc chắc muốn hủy vé?");
                if (Constant.CHECK_CONFIRM == 1) {
                    if (Constant.CHECK_CONFIRM != 0) {
                        okDelete();
                    }
                }
            }
        });
    }

    public void okDelete() {

        if (CheckInternet.isConnected(getActivity())) {
            delete();
        } else {
            String message = "Vui lòng kiểm tra kết nối Internet.";
            ShowDialog.alertDialog(getActivity(), message);
        }
    }

    public void editTicket() {

        btnSuaVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (CheckInternet.isConnected(getActivity())) {
                    setData();
                } else {

                    String message = "Vui lòng kiểm tra kết nối Internet.";
                    ShowDialog.alertDialog(getActivity(), message);
                }

            }
        });
    }

    public void changeTicket() {
        btnDoiVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (CheckInternet.isConnected(getActivity())) {
                    Constant.KEY_CHECK_FRAGMENT = 0;
                    changeSeat();
                } else {
                    String message = "Vui lòng kiểm tra kết nối Internet.";
                    ShowDialog.alertDialog(getActivity(), message);
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
        myContext = (FragmentActivity) activity;
        super.onAttach(activity);
    }

    public void setData() {

        Bundle data = new Bundle();
        data.putInt("id", id);
        data.putString("identity_number", identity_number);
        data.putString("note", note);
        data.putString("phone", phone);
        data.putString("fullname", fullname);

        FragmentSuaThongTinVe editInfo = new FragmentSuaThongTinVe();
        editInfo.setArguments(data);
        myContext.getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentholder, editInfo)
                .addToBackStack("")
                .commit();
    }

    public void changeSeat() {
        Bundle data = new Bundle();
        data.putInt("id", id);
        data.putString("code_driver", code_driver);
        data.putString("id_tripdate", id_tripdate);
        data.putString("ChuyenDi", route);
        data.putString("GioDi", start);
        data.putString("NgayDi", date);

        FragmentTabhostSoDoGhe datVe_fragment = new FragmentTabhostSoDoGhe();
        datVe_fragment.setArguments(data);
        myContext.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentholder, datVe_fragment)
                .addToBackStack("")
                .commit();
    }

    public void delete() {

        final Map<String, String> data = new HashMap<>();
        data.put("identity_number", identity_number);
        data.put("phone", phone);

        DeleteCustomerRequest.deleteCustomer(data, new Response() {

            @Override
            public void onStart() {
                ShowDialog.showLoading(getActivity());
            }

            @Override
            public void onSuccess(int code, String message, Object obj) {
                if (code == 0) {
                    ShowDialog.dimissLoading();
                    String mMessage = "Hủy vé thành công";
                    ShowDialog.alertDialog(getActivity(), mMessage);
                }
            }

            @Override
            public void onFailure() {
                ShowDialog.dimissLoading();
                String mMessage = "Hủy vé không thành công, xin kiểm tra lại.";
                ShowDialog.alertDialog(getActivity(), mMessage);
            }
        });
    }
}
