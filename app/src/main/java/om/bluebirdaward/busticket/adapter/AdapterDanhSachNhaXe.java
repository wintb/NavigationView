package om.bluebirdaward.busticket.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import om.bluebirdaward.busticket.R;
import om.bluebirdaward.busticket.dao.NhaXe.ListNhaXe;
import om.bluebirdaward.busticket.dialog.DialogNhaXeDetail;

/**
 * Created by Trinh Dinh Tien on 7/10/2016.
 */
public class AdapterDanhSachNhaXe extends RecyclerView.Adapter<AdapterDanhSachNhaXe.ViewHolderNhaXe> {

    private Context context;
    private ArrayList<ListNhaXe> listNhaXe = new ArrayList<>();

    public AdapterDanhSachNhaXe(Context context) {
        this.context = context;
    }

    public void setArrayListNhaXe(ArrayList<ListNhaXe> arr) {
        this.listNhaXe = arr;
        notifyDataSetChanged();
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

        final ListNhaXe nhaXe = listNhaXe.get(position);
        Picasso.with(context)
                .load(nhaXe.image_thumb)
                .into(holder.imgNhaXe);

        holder.imgNhaXe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("carmaker_id",nhaXe.id);
                Intent intent = new Intent(context, DialogNhaXeDetail.class);
                intent.putExtra("my_id",bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listNhaXe.size();
    }

    class ViewHolderNhaXe extends RecyclerView.ViewHolder {

        public ImageView imgNhaXe;

        public ViewHolderNhaXe(View itemView) {
            super(itemView);
            imgNhaXe = (ImageView) itemView.findViewById(R.id.imgNhaXe);

        }

    }

}