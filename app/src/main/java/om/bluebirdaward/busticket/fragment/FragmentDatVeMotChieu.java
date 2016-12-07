package om.bluebirdaward.busticket.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import om.bluebirdaward.busticket.R;
import om.bluebirdaward.busticket.dao.ChuyenDi.ChuyenDi2Params;
import om.bluebirdaward.busticket.dao.NhaXe.ChuyenDi;
import om.bluebirdaward.busticket.dao.NhaXe.HangXe;
import om.bluebirdaward.busticket.interfaces.Response;
import om.bluebirdaward.busticket.mics.datetime.CompareDateTime;
import om.bluebirdaward.busticket.mics.datetime.DatetimeFormater;
import om.bluebirdaward.busticket.request.ChuyenDi2ParamsRequest;
import om.bluebirdaward.busticket.request.ListChuyenDiRequest;
import om.bluebirdaward.busticket.request.ListHangXeRequest;
import om.bluebirdaward.busticket.utils.CheckInternet;
import om.bluebirdaward.busticket.utils.ShowDialog;

/**
 * Created by VuVanThang on 3/29/2016.
 */
public class FragmentDatVeMotChieu extends Fragment{

//    @Bind(R.id.fragment_motchieu_title)
//    TextView txtTitle;
    @Bind(R.id.txtDate)
    TextView txtDate;
    @Bind(R.id.txtChuyenDi)
    TextView txtChuyenDi;
    @Bind(R.id.btnTimChuyenTab1)
    LinearLayout btnTimChuyen;
    @Bind(R.id.txtHangXe)
    TextView txtHangXe;
    @Bind(R.id.layout_hangxe)
    LinearLayout layout_hangxe;
    @Bind(R.id.layout_chuyendi)
    LinearLayout layout_chuyendi;
    @Bind(R.id.layout_ngaydi)
    LinearLayout layout_ngaydi;

    Context context;
    String date,months,years;
    DatetimeFormater datetimeFormater;
    CompareDateTime compareDateTime;
    ListView listView;
    OnNameSetListener onNameSetListener;

    private final Handler handler = new Handler();
    public static int checkParams = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dat_ve_mot_chieu_v1,container,false);
        ButterKnife.bind(this,rootView);
        getActivity().setTitle("Tìm chuyến");
        setTypeFace();

        //Lấy ngày hiện tại lên textview
        datetimeFormater = new DatetimeFormater();
        datetimeFormater.datetimecurrent(txtDate);
        // So sánh ngày hiện tại với ngày đã chọn
        compareDateTime = new CompareDateTime(getActivity());

        //Chọn hãng xe
        chonHangXe();
        //chọn chuyến đi
        chonChuyen();
        //chon ngay thang di
        ChonNgay();

        //Sự kiệm click vào button Tìm chuyến sẽ hiển thị ra danh sách các chuyến
        btnTimChuyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (CheckInternet.isConnected(getActivity())){

                    if ((txtHangXe.getText().toString().equalsIgnoreCase("")
                            && txtChuyenDi.getText().toString().equalsIgnoreCase(""))){
                        Toast.makeText(getActivity(), "Bạn chưa nhập đủ thông tin", Toast.LENGTH_LONG).show();
                    }
                    else if ((!txtHangXe.getText().toString().equalsIgnoreCase("") && txtChuyenDi.getText().toString().equalsIgnoreCase(""))){
                        Toast.makeText(getActivity(), "Bạn chưa nhập đủ thông tin", Toast.LENGTH_LONG).show();
                    }else if(txtHangXe.getText().toString().equalsIgnoreCase("")
                            && !txtChuyenDi.getText().toString().equalsIgnoreCase("")){
                        checkParams = 2;
                        onNameSetListener.setChuyenDi_NgayDi(IdHangXe, txtChuyenDi.getText().toString(), txtDate.getText().toString());
                    }else {
                        checkParams = 3;
                        onNameSetListener.setChuyenDi_NgayDi(IdHangXe, txtChuyenDi.getText().toString(), txtDate.getText().toString());
                    }

                }else{
                    String t = "Warning";
                    String m = "Vui lòng kiểm tra kết nối Internet.";
                    ShowDialog.show(getActivity(), t, m);
                }

            }
        });

        return rootView;
    }

    private void setTypeFace(){
        Typeface face1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Regular.ttf");
        Typeface face2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Bold.ttf");
        txtDate.setTypeface(face1);
        txtChuyenDi.setTypeface(face1);
        //txtTitle.setTypeface(face1);
    }

    //------------------------------------INTERFACE FOR SEND DATA TO FRAGMENT LIST TRIP -------------------------------

    public interface OnNameSetListener{
         public void setChuyenDi_NgayDi(String idHangXe, String ChuyenDi, String NgayDi);
    }


   @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            onNameSetListener = (OnNameSetListener)activity;
        } catch (Exception e){throw new ClassCastException(activity.toString() + " must implement OnNameSetListener");}

    }

    private void chonHangXe(){
        layout_hangxe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater1 = (LayoutInflater) getContext().getSystemService(context.LAYOUT_INFLATER_SERVICE);
                LinearLayout linearLayout = (LinearLayout) inflater1.inflate(R.layout.dialog_ten_chuyen, null, false);
                listView = (ListView) linearLayout.findViewById(R.id.list);
                getLisHangXe();

                new AlertDialog.Builder(getActivity()).setTitle("Chọn hãng xe")
                        .setView(linearLayout)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                txtHangXe.setText("");
                            }
                        }).show();
            }
        });
    }

    //--------------------------------------------CHOOSE TRIP-------------------------------------------------------------

    private void chonChuyen(){
        layout_chuyendi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater1 = (LayoutInflater) getContext().getSystemService(context.LAYOUT_INFLATER_SERVICE);
                LinearLayout linearLayout = (LinearLayout) inflater1.inflate(R.layout.dialog_ten_chuyen, null, false);
                listView = (ListView) linearLayout.findViewById(R.id.list);
                getListChuyenDi(IdHangXe);

                new AlertDialog.Builder(getActivity()).setTitle("Chọn chuyến")
                        .setView(linearLayout)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                txtChuyenDi.setText("");
                            }
                        }).show();
            }
        });
    }

    //---------------------------------------CHOOSE DATE TIME FOR TRIP-----------------------------------------------------

    public void ChonNgay(){
        layout_ngaydi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(context.LAYOUT_INFLATER_SERVICE);
                LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.dialog_calendar, null, false);
                CalendarView cv = (CalendarView) ll.getChildAt(0);
                cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                        date = Integer.toString(dayOfMonth);
                        months = Integer.toString(month + 1);
                        years = Integer.toString(year);
                        compareDateTime.comparedatetime(dayOfMonth, month + 1, year);
                    }

                });
                new AlertDialog.Builder(getActivity()).setTitle("Calendar").setMessage("Chọn ngày đi")
                        .setView(ll)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                String date_month_year = date + "/" + months + "/" + years;
                                datetimeFormater.FormatDateTime(date_month_year, txtDate);
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();
            }
        });
    }


    //=================================API==========================================================
    private String IdHangXe;
    public void getLisHangXe() {

        handler.post(new Runnable() {
            @Override
            public void run() {
                ShowDialog.showLoading(getActivity());
            }
        });

        ListHangXeRequest.getListHangXe(new Response() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(int code, String message, Object obj) {
                ShowDialog.dimissLoading();
                if (code == 0) {
                    final ArrayList<String> name = new ArrayList<>();
                    final ArrayList<HangXe> list = (ArrayList<HangXe>) obj;
                    for (int i = 0; i< list.size(); i++){
                        name.add(list.get(i).carmaker);
                    }
                    ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), R.layout.custom_dialog_ten_chuyen, name);
                    listView.setAdapter(arrayAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            txtHangXe.setText(name.get(position).toString());
                            IdHangXe = list.get(position).id;
                        }
                    });
                }
            }

            @Override
            public void onFailure() {
                Log.d("NOTE", "kiem tra lai ket noi");
            }
        });
    }

    private void getListChuyenDi(String id){

        if (!txtHangXe.getText().toString().equalsIgnoreCase("")){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    ShowDialog.showLoading(getActivity());
                }
            });

            ListChuyenDiRequest.getListChuyenDi(Integer.parseInt(id), new Response() {
                @Override
                public void onStart() {
                }

                @Override
                public void onSuccess(int code, String message, Object obj) {
                    ShowDialog.dimissLoading();
                    if (code == 0) {
                        final ArrayList<String> name = new ArrayList<>();

                        final ArrayList<ChuyenDi> list = (ArrayList<ChuyenDi>) obj;

                        for (int i = 0; i < list.size(); i++) {
                            name.add(list.get(i).route);
                        }
                        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), R.layout.custom_dialog_ten_chuyen, name);
                        listView.setAdapter(arrayAdapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                txtChuyenDi.setText(name.get(position).toString());
                            }
                        });
                    }
                    if (code == 1) {
                        setListEmpty();
                    }
                }

                @Override
                public void onFailure() {
                    setListEmpty();
                }
            });
        }else {
            //setListEmpty();
            getListRoute2Params();
        }
    }


    private void setListEmpty(){
        ArrayList<String> name = new ArrayList<>();
        name.add("Không có chuyến");
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), R.layout.custom_dialog_ten_chuyen, name);
        listView.setAdapter(arrayAdapter);
    }


    private void getListRoute2Params(){
        handler.post(new Runnable() {
            @Override
            public void run() {
                ShowDialog.showLoading(getActivity());
            }
        });

        ChuyenDi2ParamsRequest.getTenChuyenDi2Params(new Response() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(int code, String message, Object obj) {
                ShowDialog.dimissLoading();
                if (code == 0) {
                    final ArrayList<String> name = new ArrayList<>();

                    final ArrayList<ChuyenDi2Params> list = (ArrayList<ChuyenDi2Params>) obj;

                    for (int i = 0; i < list.size(); i++) {
                        name.add(list.get(i).route);
                    }
                    ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), R.layout.custom_dialog_ten_chuyen, name);
                    listView.setAdapter(arrayAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            txtChuyenDi.setText(name.get(position).toString());
                        }
                    });
                }
                if (code == 1) {
                    setListEmpty();
                }
            }

            @Override
            public void onFailure() {
                setListEmpty();
            }
        });
    }


}
