package om.bluebirdaward.busticket.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import om.bluebirdaward.busticket.R;
import om.bluebirdaward.busticket.adapter.AdapterDanhSachNhaXe;
import om.bluebirdaward.busticket.dao.NhaXe.ListNhaXe;
import om.bluebirdaward.busticket.interfaces.Response;
import om.bluebirdaward.busticket.request.ListNhaXeRequest;

/**
 * Created by Trinh Dinh Tien on 7/10/2016.
 */
public class FragmentDanhSachNhaXe extends android.support.v4.app.Fragment {

    private Context context;
    private RecyclerView recyclerNhaXe;
    private AdapterDanhSachNhaXe adapterDanhSachNhaXe;
    private ArrayList<ListNhaXe> listNhaXes;

    public FragmentDanhSachNhaXe() {
    }

    public FragmentDanhSachNhaXe(Context mContext) {
        this.context = mContext;
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
        SetRecyclerNhaXe();
        getListNhaXe();
        return view;
    }

    public void SetRecyclerNhaXe() {

        adapterDanhSachNhaXe = new AdapterDanhSachNhaXe(context);
        recyclerNhaXe.setLayoutManager(new GridLayoutManager(context, 2));
        recyclerNhaXe.setAdapter(adapterDanhSachNhaXe);
    }


    //==================================API ========================================================
    public void getListNhaXe() {

        ListNhaXeRequest.getListNhaXe(new Response() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(int code, String message, Object obj) {

                if (code == 0) {
                    listNhaXes = (ArrayList<ListNhaXe>) obj;
                    adapterDanhSachNhaXe.setArrayListNhaXe(listNhaXes);
                }
            }

            @Override
            public void onFailure() {
                Log.d("NOTE","kiem tra lai ket noi");
            }
        });
    }
}