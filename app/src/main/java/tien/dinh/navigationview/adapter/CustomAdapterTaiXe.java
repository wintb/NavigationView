package tien.dinh.navigationview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import tien.dinh.navigationview.Object.Object_TaiXe;
import tien.dinh.navigationview.R;

/**
 * Created by VuVanThang on 5/15/2016.
 */
public class CustomAdapterTaiXe extends BaseAdapter{

    private List<Object_TaiXe> object_taiXe;
    private Context context;
    private LayoutInflater layoutInflater;

    public CustomAdapterTaiXe(Context context, List<Object_TaiXe> object_taiXe){
        this.context = context;
        this.object_taiXe = object_taiXe;
    }

    @Override
    public int getCount() {
        return object_taiXe.size();
    }

    @Override
    public Object getItem(int position) {
        return object_taiXe.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.tabhost_oneway_listtrip_taixe,parent,false);

        Object_TaiXe object_taiXe = (Object_TaiXe) getItem(position);
        TextView txtTaiXe = (TextView) view.findViewById(R.id.txtTaiXe);
        TextView txtPhuXe = (TextView) view.findViewById(R.id.txtPhuXe);
        TextView txtBienSo = (TextView) view.findViewById(R.id.txtBienSoXe);
        TextView txtSDTTai = (TextView) view.findViewById(R.id.txtSoDienthoai);

        txtTaiXe.setText(object_taiXe.getTaiXe());
        txtPhuXe.setText(object_taiXe.getPhuXe());
        txtBienSo.setText(object_taiXe.getBienSo());
        txtSDTTai.setText(object_taiXe.getSDTTai());

        return view;
    }
}
