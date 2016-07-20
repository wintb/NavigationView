package om.bluebirdaward.busticket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import om.bluebirdaward.busticket.dao.TaiXe;
import om.bluebirdaward.busticket.R;

/**
 * Created by VuVanThang on 5/15/2016.
 */
public class AdapterThongTinTaiXe extends BaseAdapter{

    private List<TaiXe> _taiXe;
    private Context context;
    private LayoutInflater layoutInflater;

    public AdapterThongTinTaiXe(Context context, List<TaiXe> _taiXe){
        this.context = context;
        this._taiXe = _taiXe;
    }

    @Override
    public int getCount() {
        return _taiXe.size();
    }

    @Override
    public Object getItem(int position) {
        return _taiXe.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.dialog_thong_tin_tai_xe,parent,false);

        TaiXe _taiXe = (TaiXe) getItem(position);
        TextView txtTaiXe = (TextView) view.findViewById(R.id.txtTaiXe);
        TextView txtPhuXe = (TextView) view.findViewById(R.id.txtPhuXe);
        TextView txtBienSo = (TextView) view.findViewById(R.id.txtBienSoXe);
        TextView txtSDTTai = (TextView) view.findViewById(R.id.txtSoDienthoai);

        txtTaiXe.setText(_taiXe.getTaiXe());
        txtPhuXe.setText(_taiXe.getPhuXe());
        txtBienSo.setText(_taiXe.getBienSo());
        txtSDTTai.setText(_taiXe.getSDTTai());

        return view;
    }
}
