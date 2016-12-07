package om.bluebirdaward.busticket.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import om.bluebirdaward.busticket.R;
import om.bluebirdaward.busticket.dao.DanhSachChuyen.DanhSachChuyen;
import om.bluebirdaward.busticket.interfaces.OnLoadMoreListener;
import om.bluebirdaward.busticket.utils.BitmapLoader;

/**
 * Created by VuVanThang on 5/11/2016.
 */
public class AdapterDanhSachChuyen extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<DanhSachChuyen> danhSachChuyens;
    private SoDoGhe soDoGhe;

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOAD = 1;
    private OnLoadMoreListener onLoadMoreListener;
    private boolean isLoading;
    private int visibleTheshold = 3;
    private int totalItemCount, lastVisibleItem;

    public AdapterDanhSachChuyen(Context context, ArrayList<DanhSachChuyen> danhSachChuyens, RecyclerView recyclerView){
        this.context = context;
        this.danhSachChuyens = danhSachChuyens;
        soDoGhe = (SoDoGhe) context;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleTheshold)){
                    if (onLoadMoreListener != null){
                        onLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener){
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public void addDanhSachChuyen(ArrayList<DanhSachChuyen> danhSachChuyens){
        this.danhSachChuyens = danhSachChuyens;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_fragment_danh_sach_chuyen, parent, false);
            return new DanhSachChuyenViewHolder(itemView);
        }else if(viewType == VIEW_TYPE_LOAD){
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_load_more,parent, false);
            return new LoadMoreViewHolder(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof DanhSachChuyenViewHolder) {
            final DanhSachChuyen danhSachChuyen = danhSachChuyens.get(position);
            final DanhSachChuyenViewHolder danhSachChuyenViewHolder = (DanhSachChuyenViewHolder) holder;

            danhSachChuyenViewHolder.txtCarmaker.setText(danhSachChuyen.carmaker);
            danhSachChuyenViewHolder.txtTai.setText(danhSachChuyen.code_driver);
            danhSachChuyenViewHolder.txtGioDi.setText(danhSachChuyen.start);
            danhSachChuyenViewHolder.txtThoiGian.setText(danhSachChuyen.time);
            danhSachChuyenViewHolder.txtGia.setText(danhSachChuyen.price);
            danhSachChuyenViewHolder.txtDienThoai.setText(danhSachChuyen.phone);
            BitmapLoader.LoadImageNotScale(context, danhSachChuyenViewHolder.avatar, danhSachChuyen.image);

            danhSachChuyenViewHolder.btnChon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    soDoGhe.setSoDoGhe(danhSachChuyen.route, danhSachChuyen.start, danhSachChuyen.date, danhSachChuyen.code_trip, danhSachChuyen.id_tripdate, danhSachChuyen.code_driver);
                }
            });
        }else if(holder instanceof LoadMoreViewHolder){
            LoadMoreViewHolder loadMoreViewHolder = (LoadMoreViewHolder) holder;
            loadMoreViewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return danhSachChuyens.get(position) == null ? VIEW_TYPE_LOAD : VIEW_TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return danhSachChuyens == null ? 0 : danhSachChuyens.size();
    }

    public interface SoDoGhe{
        void setSoDoGhe(String TenChuyen, String GioDi, String NgayDi,String code_trip, String id_tripdate, String code_driver);
    }

    public void  setLoaded(){
        isLoading = false;
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

    //------------------------------Load more holder ---------------------------------------------
    public static class LoadMoreViewHolder extends RecyclerView.ViewHolder{
        protected ProgressBar progressBar;

        public LoadMoreViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.loadmore_progressbar);
        }
    }

}
