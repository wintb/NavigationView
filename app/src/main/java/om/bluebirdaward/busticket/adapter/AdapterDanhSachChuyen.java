package om.bluebirdaward.busticket.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import om.bluebirdaward.busticket.R;
import om.bluebirdaward.busticket.dao.danhsachchuyen.DanhSachChuyen;

/**
 * Created by VuVanThang on 5/11/2016.
 */
public class AdapterDanhSachChuyen extends RecyclerView.Adapter<AdapterDanhSachChuyen.DanhSachChuyenViewHolder> {

    private Context context;
    private ArrayList<DanhSachChuyen> danhSachChuyens;
    private SoDoGhe soDoGhe;
    public AdapterDanhSachChuyen(Context context, ArrayList<DanhSachChuyen> danhSachChuyens){
        this.context = context;
        this.danhSachChuyens = danhSachChuyens;
        soDoGhe = (SoDoGhe) context;
    }

    public void addDanhSachChuyen(ArrayList<DanhSachChuyen> danhSachChuyens){
        this.danhSachChuyens = danhSachChuyens;
        notifyDataSetChanged();
    }

    @Override
    public DanhSachChuyenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_fragment_danh_sach_chuyen, parent, false);
        return new DanhSachChuyenViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DanhSachChuyenViewHolder holder, int position) {
        final DanhSachChuyen danhSachChuyen = danhSachChuyens.get(position);
        final DanhSachChuyenViewHolder danhSachChuyenViewHolder = (DanhSachChuyenViewHolder) holder;

        danhSachChuyenViewHolder.txtCarmaker.setText(danhSachChuyen.carmaker);
        danhSachChuyenViewHolder.txtTai.setText(danhSachChuyen.code_driver);
        danhSachChuyenViewHolder.txtGioDi.setText(danhSachChuyen.start);
        danhSachChuyenViewHolder.txtThoiGian.setText(danhSachChuyen.time);
        danhSachChuyenViewHolder.txtGia.setText(danhSachChuyen.price);
        danhSachChuyenViewHolder.txtDienThoai.setText(danhSachChuyen.phone);

        danhSachChuyenViewHolder.btnChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soDoGhe.setSoDoGhe(danhSachChuyen.route, danhSachChuyen.start, danhSachChuyen.date, danhSachChuyen.code_trip, danhSachChuyen.id_tripdate, danhSachChuyen.code_driver);
            }
        });

    }

    @Override
    public int getItemCount() {
        return danhSachChuyens.size();
    }

    public interface SoDoGhe{
        void setSoDoGhe(String TenChuyen, String GioDi, String NgayDi,String code_trip, String id_tripdate, String code_driver);
    }



    //-------------------------------------View Holder----------------------------------------------
    public static class DanhSachChuyenViewHolder extends RecyclerView.ViewHolder{
        private Context context;
        protected View rootLayout;
        protected ImageView avatar;
        protected TextView txtCarmaker;
        protected ImageView imgTai;
        protected TextView txtTai;
        protected TextView txtGioDi;
        protected TextView txtThoiGian;
        protected TextView txtGia;
        protected TextView txtDienThoai;
        protected ImageView imgDienThoai;
        protected Button btnChon;

        public DanhSachChuyenViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            rootLayout = itemView.findViewById(R.id.layout_custom_danhsachchuyen);
            avatar = (ImageView) itemView.findViewById(R.id.danhsachchuyen_AvatarCarMaker);
            txtCarmaker = (TextView) itemView.findViewById(R.id.danhsachchuyen_txtCarmaker);
            imgTai = (ImageView) itemView.findViewById(R.id.danhsachchuyen_imgTai);
            txtTai = (TextView) itemView.findViewById(R.id.danhsachchuyen_txtTai);
            txtGioDi = (TextView) itemView.findViewById(R.id.danhsachchuyen_giodi);
            txtThoiGian = (TextView) itemView.findViewById(R.id.danhsachchuyen_thoigian);
            txtGia = (TextView) itemView.findViewById(R.id.danhsachchuyen_gia);
            txtDienThoai = (TextView) itemView.findViewById(R.id.danhsachchuyen_txtDienThoai);
            imgDienThoai = (ImageView) itemView.findViewById(R.id.danhsachchuyen_imgDienThoai);
            btnChon = (Button) itemView.findViewById(R.id.danhsachchuyen_btnChon);
        }
    }

}
