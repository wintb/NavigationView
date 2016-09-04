package om.bluebirdaward.busticket.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

import om.bluebirdaward.busticket.R;
import om.bluebirdaward.busticket.adapter.AdapterDanhSachNhaXe;
import om.bluebirdaward.busticket.dao.NhaXe.ListNhaXe;
import om.bluebirdaward.busticket.interfaces.Response;
import om.bluebirdaward.busticket.request.ListNhaXeRequest;
import om.bluebirdaward.busticket.utils.CheckInternet;
import om.bluebirdaward.busticket.utils.ShowDialog;

/**
 * Created by Trinh Dinh Tien on 7/10/2016.
 */
public class FragmentDanhSachNhaXe extends android.support.v4.app.Fragment {

    //private Context context;
    private RecyclerView recyclerNhaXe;
    private AdapterDanhSachNhaXe adapterDanhSachNhaXe;
    private ArrayList<ListNhaXe> listNhaXes;
    private LinearLayout layout_error;
    private Button btnTryAgain;

    public FragmentDanhSachNhaXe() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_danh_sach_nha_xe, container, false);
        recyclerNhaXe = (RecyclerView) view.findViewById(R.id.recyclerNhaXe);
        layout_error = (LinearLayout) view.findViewById(R.id.layout_error);
        btnTryAgain = (Button) view.findViewById(R.id.btnTryAgain);
        getActivity().setTitle("Nhà xe");
        SetRecyclerNhaXe();
        getListNhaXe();
        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getListNhaXe();
            }
        });

        return view;
    }

    public void SetRecyclerNhaXe() {

        adapterDanhSachNhaXe = new AdapterDanhSachNhaXe(getActivity());
        recyclerNhaXe.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerNhaXe.setAdapter(adapterDanhSachNhaXe);
    }


    public void getListNhaXe() {

        ListNhaXeRequest.getListNhaXe(new Response() {
            @Override
            public void onStart() {
                ShowDialog.showLoading(getActivity());
                if (CheckInternet.isConnected(getActivity())){
                    layout_error.setVisibility(View.GONE);
                }else{
                    layout_error.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onSuccess(int code, String message, Object obj) {
                layout_error.setVisibility(View.GONE);
                if (code == 0) {
                    listNhaXes = (ArrayList<ListNhaXe>) obj;
                    adapterDanhSachNhaXe.setArrayListNhaXe(listNhaXes);
                    ShowDialog.dimissLoading();
                }
            }

            @Override
            public void onFailure() {
                ShowDialog.dimissLoading();
                layout_error.setVisibility(View.VISIBLE);
            }
        });
    }
}