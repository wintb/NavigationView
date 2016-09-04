package om.bluebirdaward.busticket.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import om.bluebirdaward.busticket.R;
import om.bluebirdaward.busticket.adapter.AdapterDanhSachChuyen;
import om.bluebirdaward.busticket.dao.DanhSachChuyen.DanhSachChuyen;
import om.bluebirdaward.busticket.interfaces.Response;
import om.bluebirdaward.busticket.mics.Constant;
import om.bluebirdaward.busticket.request.DanhSachChuyen2paramsRequest;
import om.bluebirdaward.busticket.request.DanhSachChuyenRequest;
import om.bluebirdaward.busticket.utils.CheckInternet;
import om.bluebirdaward.busticket.utils.ShowDialog;

/**
 * Created by VuVanThang on 4/1/2016.
 */
public class FragmentDanhSachChuyen extends Fragment {

    @Bind(R.id.danhsachchuyen_NoiDi)
    TextView NoiDi;
    @Bind(R.id.danhsachchuyen_NoiDen)
    TextView NoiDen;
    @Bind(R.id.danhsachchuyen_khoangcach)
    TextView khoangcach;
    @Bind(R.id.danhsachchuyen_ngaydi)
    TextView ngaydi;
    @Bind(R.id.layout_error)
    LinearLayout layout_error;
    @Bind(R.id.btnTryAgain)
    Button btnTryAgain;

    @Bind(R.id.recyclerDanhSachChuyen)
    RecyclerView recyclerDanhSachChuyen;
    String idHangXe ;

    AdapterDanhSachChuyen customApdaterOneTrip;
    public String ChuyenDi;
    public String NgayDi;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_danh_sach_chuyen, container, false);
        ButterKnife.bind(this, rootView);
        //setTypeFace();
        Constant.KEY_CHECK_FRAGMENT = 1;
        Bundle data = getArguments();

        ngaydi.setText(data.getString("NgayDi"));
        idHangXe = data.getString("idHangXe");
        ChuyenDi = data.getString("ChuyenDi");
        NgayDi = data.getString("NgayDi");

        splitString(ChuyenDi);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerDanhSachChuyen.setLayoutManager(mLayoutManager);

        if (FragmentDatVeMotChieu.checkParams == 2){
            getDanhSachChuyen2Parrams(ChuyenDi, NgayDi);
        }
        if (FragmentDatVeMotChieu.checkParams == 3){
            getDanhSachChuyen(idHangXe, ChuyenDi, NgayDi);
        }

        setOnClickTryAgain();


        return rootView;

    }

    private void setTypeFace(){
        Typeface face1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Regular.ttf");
        Typeface face2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Bold.ttf");
        ngaydi.setTypeface(face1);
    }

    //tách chuỗi
    private void splitString(String name){
        List<String> list= new ArrayList<>();
        String[] temp = name.split(" -> ");
        for (int i = 0 ; i < temp.length; i++) {
            list.add(temp[i]);
        }
        NoiDi.setText(list.get(0));
        NoiDen.setText(list.get(1));
    }

    //=======================================API ===================================================

    private void getDanhSachChuyen(String id_carmaker, String route, String date){
        ShowDialog.showLoading(getActivity());

        if (CheckInternet.isConnected(getActivity())){
            layout_error.setVisibility(View.GONE);
        }else{
            layout_error.setVisibility(View.VISIBLE);
        }

        DanhSachChuyenRequest.getDanhSachChuyen(id_carmaker, route, date, new Response() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(int code, String message, Object obj) {
                ShowDialog.dimissLoading();
                layout_error.setVisibility(View.GONE);
                if (code == 0) {
                    ArrayList<DanhSachChuyen> list = (ArrayList<DanhSachChuyen>) obj;
                    khoangcach.setText(list.get(0).far+" Km");
                    customApdaterOneTrip = new AdapterDanhSachChuyen(getActivity(), list);
                    recyclerDanhSachChuyen.setAdapter(customApdaterOneTrip);
                }else{
                    ArrayList<DanhSachChuyen> list = new ArrayList<DanhSachChuyen>();
                    customApdaterOneTrip = new AdapterDanhSachChuyen(getActivity(), list);
                    recyclerDanhSachChuyen.setAdapter(customApdaterOneTrip);
                }
            }

            @Override
            public void onFailure() {
                ShowDialog.dimissLoading();
                ArrayList<DanhSachChuyen> list = new ArrayList<DanhSachChuyen>();
                customApdaterOneTrip = new AdapterDanhSachChuyen(getActivity(), list);
                recyclerDanhSachChuyen.setAdapter(customApdaterOneTrip);
                layout_error.setVisibility(View.VISIBLE);
                Log.d("NOTE", "kiem tra lai ket noi");
            }
        });
    }

    private void setOnClickTryAgain(){
        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (FragmentDatVeMotChieu.checkParams == 2){
                    getDanhSachChuyen2Parrams(ChuyenDi, NgayDi);
                }
                if (FragmentDatVeMotChieu.checkParams == 3){
                    getDanhSachChuyen(idHangXe, ChuyenDi, NgayDi);
                }
            }
        });
    }


    private void getDanhSachChuyen2Parrams(String route, String date){
        ShowDialog.showLoading(getActivity());
        if (CheckInternet.isConnected(getActivity())){
            layout_error.setVisibility(View.GONE);
        }else{
            layout_error.setVisibility(View.VISIBLE);
        }
        DanhSachChuyen2paramsRequest.getDanhSachChuyen2Params(route, date, new Response() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(int code, String message, Object obj) {
                ShowDialog.dimissLoading();
                layout_error.setVisibility(View.GONE);
                if (code == 0) {
                    ArrayList<DanhSachChuyen> list = (ArrayList<DanhSachChuyen>) obj;
                    khoangcach.setText(list.get(0).far + " Km");
                    customApdaterOneTrip = new AdapterDanhSachChuyen(getActivity(), list);
                    recyclerDanhSachChuyen.setAdapter(customApdaterOneTrip);
                } else {
                    ArrayList<DanhSachChuyen> list = new ArrayList<DanhSachChuyen>();
                    customApdaterOneTrip = new AdapterDanhSachChuyen(getActivity(), list);
                    recyclerDanhSachChuyen.setAdapter(customApdaterOneTrip);
                }
            }

            @Override
            public void onFailure() {
                ShowDialog.dimissLoading();
                ArrayList<DanhSachChuyen> list = new ArrayList<DanhSachChuyen>();
                customApdaterOneTrip = new AdapterDanhSachChuyen(getActivity(), list);
                recyclerDanhSachChuyen.setAdapter(customApdaterOneTrip);
                layout_error.setVisibility(View.VISIBLE);
                Log.d("NOTE", "kiem tra lai ket noi");
            }
        });
    }

}
