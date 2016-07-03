package tien.dinh.navigationview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import tien.dinh.navigationview.R;
import tien.dinh.navigationview.fragment.FragmentAbout;

/**
 * Created by DinhTien on 02-07-2016.
 */
public class AdapterAbout extends RecyclerView.Adapter<AdapterAbout.ViewHolderAbout>{

    private Context context;
    private ArrayList<FragmentAbout.Items> listItemses;
    private boolean check = true;

    public AdapterAbout(Context context, ArrayList arr){
        this.context = context;
        this.listItemses = arr;
    }

    @Override
    public ViewHolderAbout onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_fragment_about,parent,false);
        ViewHolderAbout viewHolderAbout = new ViewHolderAbout(view);
        return viewHolderAbout;
    }

    @Override
    public void onBindViewHolder(final ViewHolderAbout holder, int position) {

        holder.txtTitle.setText(listItemses.get(position).getTitle());
        holder.txtContent.setText(listItemses.get(position).getContent());

        holder.txtTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check) {
                    holder.layoutContent.setVisibility(View.VISIBLE);
                    holder.imgRight_Arrow.setVisibility(View.INVISIBLE);
                    holder.imgDown_Arrow.setVisibility(View.VISIBLE);
                    check = false;
                } else {
                    holder.layoutContent.setVisibility(View.GONE);
                    holder.imgRight_Arrow.setVisibility(View.VISIBLE);
                    holder.imgDown_Arrow.setVisibility(View.INVISIBLE);
                    check = true;
                }
            }
        });

        holder.txtRutGon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check == false){
                    holder.layoutContent.setVisibility(View.GONE);
                    holder.imgRight_Arrow.setVisibility(View.VISIBLE);
                    holder.imgDown_Arrow.setVisibility(View.INVISIBLE);
                    check = true;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItemses.size();
    }

    class ViewHolderAbout extends RecyclerView.ViewHolder {
        public TextView txtTitle,txtContent, txtRutGon;
        public LinearLayout layoutContent;
        ImageView imgRight_Arrow, imgDown_Arrow;
        public ViewHolderAbout(View itemView) {
            super(itemView);
            txtTitle = (TextView)itemView.findViewById(R.id.txtTitle);
            txtContent = (TextView)itemView.findViewById(R.id.txtContent);
            txtRutGon = (TextView)itemView.findViewById(R.id.txtRutGon);
            layoutContent = (LinearLayout)itemView.findViewById(R.id.layoutContent);
            imgDown_Arrow = (ImageView)itemView.findViewById(R.id.imgDown_Arrow);
            imgRight_Arrow = (ImageView)itemView.findViewById(R.id.imgRight_Arrow);
        }
    }
}
