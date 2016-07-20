package om.bluebirdaward.busticket.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import om.bluebirdaward.busticket.R;
import om.bluebirdaward.busticket.adapter.AdapterDanhSachNhaXe;

/**
 * Created by Trinh Dinh Tien on 7/10/2016.
 */
public class FragmentDanhSachNhaXe extends android.support.v4.app.Fragment{

    private Context context;
    private RecyclerView recyclerNhaXe;
    private AdapterDanhSachNhaXe adapterDanhSachNhaXe;
    private ArrayList arrayList;

    public FragmentDanhSachNhaXe(){}
    public FragmentDanhSachNhaXe(Context mContext){
        this.context = mContext;
        arrayList = new ArrayList();
        arrayList.add(4);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_danh_sach_nha_xe,container,false);
        recyclerNhaXe = (RecyclerView)view.findViewById(R.id.recyclerNhaXe);
        arrayList.add(4);
        SetRecyclerNhaXe();
        return view;
    }

    public void SetRecyclerNhaXe(){

        adapterDanhSachNhaXe = new AdapterDanhSachNhaXe(context,arrayList);
        recyclerNhaXe.setLayoutManager(new GridLayoutManager(context,2));
        recyclerNhaXe.setAdapter(adapterDanhSachNhaXe);
    }
}
