package tien.dinh.navigationview.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import tien.dinh.navigationview.R;
import tien.dinh.navigationview.dialog.DialogNhaXeDetail;

/**
 * Created by Trinh Dinh Tien on 7/10/2016.
 */
public class AdapterDanhSachNhaXe extends RecyclerView.Adapter<AdapterDanhSachNhaXe.ViewHolderNhaXe>{

    private Context context;
    private ArrayList arrayList;
    NhaXeDetail nhaXeDetail;

    public AdapterDanhSachNhaXe(Context context, ArrayList arrayList){
        this.context = context;
        this.arrayList = arrayList;
        //nhaXeDetail = (NhaXeDetail) context;
    }


    @Override
    public ViewHolderNhaXe onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_fragment_danh_sach_nha_xe, parent, false);
        ViewHolderNhaXe viewHolderNhaXe = new ViewHolderNhaXe(view);
        return viewHolderNhaXe;
    }

    @Override
    public void onBindViewHolder(ViewHolderNhaXe holder, int position) {

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ViewHolderNhaXe extends RecyclerView.ViewHolder{

        public ImageView imgNhaXe;

        public ViewHolderNhaXe(View itemView) {
            super(itemView);
            imgNhaXe = (ImageView)itemView.findViewById(R.id.imgNhaXe);
            imgNhaXe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    nhaXeDetail.goDetail();
                    Intent intent = new Intent(context, DialogNhaXeDetail.class);
                    context.startActivity(intent);
                }
            });
        }

    }

    public interface NhaXeDetail{
        void goDetail();
    }
}
